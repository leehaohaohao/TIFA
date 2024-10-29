package com.tifa.business.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.tifa.common.config.SysConfig;
import com.tifa.common.config.jwt.JwtProperty;
import com.tifa.common.config.jwt.JwtUtil;
import com.tifa.common.entity.co.LoginCo;
import com.tifa.common.entity.co.RegisterCo;
import com.tifa.common.entity.constants.ExceptionConstants;
import com.tifa.common.entity.dto.ResponsePack;
import com.tifa.common.entity.dto.UserInfoDto;
import com.tifa.common.entity.po.BusinessException;
import com.tifa.common.entity.po.UserContext;
import com.tifa.common.mapper.UserInfoMapper;
import com.tifa.common.utils.NumUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 登陆、注册控制器
 *
 * @author lihao
 * &#064;date  2024/10/29--19:00
 * @since 1.0
 */
@RestController
@AllArgsConstructor
public class LogController {
    private final UserInfoMapper userInfoMapper;
    private final JwtProperty jwtProperty;
    private final SysConfig sysConfig;

    /**
     * 登陆
     * @param request
     * @param loginCo
     * @return
     * @throws BusinessException
     */
    @PostMapping("/login")
    public ResponsePack index(HttpServletRequest request, @RequestBody LoginCo loginCo) throws BusinessException {
        // 登陆参数校验
        if(StrUtil.hasBlank(loginCo.getEmail(),loginCo.getPassword())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        // 查询用户
        UserInfoDto logInfoDto = userInfoMapper.selectUserByEmail(loginCo.getEmail());
        Optional.ofNullable(logInfoDto)
                .orElseThrow(()->new BusinessException(ExceptionConstants.LOG_ERROR));
        if(!DigestUtils.md5DigestAsHex(loginCo.getPassword().getBytes()).equals(logInfoDto.getPassword())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        UserContext userContext = getUserContext(request);
        userContext.setId(logInfoDto.getId());
        // 生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userContext.getId());
        if (!StrUtil.hasBlank(userContext.getIpv4())) {
            claims.put("ipv4", userContext.getIpv4());
        }
        if (!StrUtil.hasBlank(userContext.getIpv6())) {
            claims.put("ipv6", userContext.getIpv6());
        }
        if (!StrUtil.hasBlank(userContext.getProxyIp())) {
            claims.put("proxyIp", userContext.getProxyIp());
        }
        String token = JwtUtil.createJwt(jwtProperty.getJWT_SECRET(), jwtProperty.getJWT_EXPIRE(), claims);
        return ResponsePack.success(token);
    }

    /**
     * 注册
     * @param registerCo
     * @return
     * @throws BusinessException
     */
    @PostMapping("/register")
    public ResponsePack register(@RequestBody RegisterCo registerCo) throws BusinessException {
        // 注册参数校验
        if(StrUtil.hasBlank(registerCo.getEmail(),registerCo.getPassword())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        if(userInfoMapper.selectUserByEmail(registerCo.getEmail())!=null){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        //校验邮箱和密码
        if(!checkEmail(registerCo.getEmail()) || !checkPassword(registerCo.getPassword())){
            throw new BusinessException(ExceptionConstants.INVALID_REGISTER_INFO);
        }
        //TODO 机器人验证
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(registerCo.getEmail());
        userInfoDto.setPassword(DigestUtils.md5DigestAsHex(registerCo.getPassword().getBytes()));
        userInfoDto.setId(IdUtil.getSnowflakeNextId());
        userInfoDto.setUsername(sysConfig.getDefaultUsername()+ NumUtils.getRandomNum(8));
        userInfoDto.setCreateDate(System.currentTimeMillis());
        if(!userInfoMapper.insertUser(userInfoDto).equals(1)){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        return ResponsePack.success(null);
    }
    /**
     * 构建userContext
     * @param request 请求
     * @return
     */
    private UserContext getUserContext(HttpServletRequest request) throws BusinessException {
        UserContext userContext = new UserContext();
        String ipv4 = request.getRemoteAddr();
        String ipv6 = request.getHeader("X-Forwarded-For");
        String proxyIp = request.getHeader("X-Proxy-IP");
        // 校验ip
        if(StrUtil.hasBlank(ipv6) && StrUtil.hasBlank(ipv4)){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        if(!StrUtil.hasBlank(ipv6) && !checkIpv6(ipv6)){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        userContext.setIpv4(ipv4);
        userContext.setIpv6(ipv6);
        userContext.setProxyIp(proxyIp);
        return userContext;
    }

    /**
     * 校验ipv6地址
     * @param ipv6
     * @return
     */
    private Boolean checkIpv6(String ipv6){
        if(StrUtil.hasBlank(ipv6)){
            return false;
        }
        try{
            InetAddress address = Inet6Address.getByName(ipv6);
            return address instanceof Inet6Address;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    /**
     * 检查邮箱
     * @param email
     * @return
     */
    private Boolean checkEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    /**
     * 检查密码
     * @param password
     * @return
     */
    private Boolean checkPassword(String password){
        if (password.length() < 8) {
            return false;
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}
