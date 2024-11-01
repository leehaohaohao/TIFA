package com.tifa.business.controller;

import cn.hutool.core.util.StrUtil;
import com.tifa.common.config.SysConfig;
import com.tifa.common.config.jwt.JwtProperty;
import com.tifa.common.config.jwt.JwtUtil;
import com.tifa.common.entity.co.log.CodeCo;
import com.tifa.common.entity.co.log.LoginCo;
import com.tifa.common.entity.co.log.RegisterCo;
import com.tifa.common.constants.ExceptionConstants;
import com.tifa.common.base.ResponsePack;
import com.tifa.common.entity.dto.UserInfoDto;
import com.tifa.common.base.BusinessException;
import com.tifa.common.base.UserContext;
import com.tifa.common.entity.vo.CodeVo;
import com.tifa.common.mapper.UserInfoMapper;
import com.tifa.common.utils.IdUtils;
import com.tifa.common.utils.NumUtils;
import com.wf.captcha.SpecCaptcha;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
/**
 * 登陆、注册控制器
 * TODO 限流
 * @author lihao
 * &#064;date  2024/10/29--19:00
 * @since 1.0
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class LogController {
    private final UserInfoMapper userInfoMapper;
    private final JwtProperty jwtProperty;
    private final SysConfig sysConfig;
    private final ConcurrentHashMap<String,CodeCo> codeCoMap = new ConcurrentHashMap<>();
    /**
     * 登陆
     * @param request
     * @param loginCo
     * @return
     * @throws BusinessException
     */
    @PostMapping("/login")
    public ResponsePack login(HttpServletRequest request, @RequestBody LoginCo loginCo) throws BusinessException {
        //验证验证码
        if( StrUtil.hasBlank(loginCo.getCodeId()) ||
                codeCoMap.get(loginCo.getCodeId()) == null ||
                !codeCoMap.get(loginCo.getCodeId()).getCode().toLowerCase().equals(loginCo.getCode())
        ){
            throw new BusinessException(ExceptionConstants.CODE_ERROR);
        }
        codeCoMap.remove(loginCo.getCodeId());
        // 登陆参数校验
        if(StrUtil.hasBlank(loginCo.getEmail(),loginCo.getPassword(),loginCo.getCode())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        // 查询用户
        UserInfoDto logInfoDto = userInfoMapper.selectUserByEmail(loginCo.getEmail());
        Optional.ofNullable(logInfoDto)
                .orElseThrow(()->new BusinessException(ExceptionConstants.LOG_ERROR));
        if(!DigestUtils.md5DigestAsHex(loginCo.getPassword().getBytes()).equals(logInfoDto.getPassword())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        //TODO 登陆尝试次数限制
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
        //验证验证码
        if( StrUtil.hasBlank(registerCo.getCodeId()) ||
        codeCoMap.get(registerCo.getCodeId()) == null ||
                !codeCoMap.get(registerCo.getCodeId()).getCode().toLowerCase().equals(registerCo.getCode())
        ){
            throw new BusinessException(ExceptionConstants.CODE_ERROR);
        }
        codeCoMap.remove(registerCo.getCodeId());
        // 注册参数校验
        if(StrUtil.hasBlank(registerCo.getEmail(),registerCo.getPassword())){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        //检查用户是否存在
        if(userInfoMapper.selectUserByEmail(registerCo.getEmail())!=null){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        //校验邮箱和密码
        if(!checkEmail(registerCo.getEmail()) || !checkPassword(registerCo.getPassword())){
            throw new BusinessException(ExceptionConstants.INVALID_REGISTER_INFO);
        }
        //TODO 验证邮箱验证码
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(registerCo.getEmail());
        userInfoDto.setPassword(DigestUtils.md5DigestAsHex(registerCo.getPassword().getBytes()));
        userInfoDto.setId(IdUtils.generateUserId());
        userInfoDto.setUsername(sysConfig.getDefaultUsername()+ NumUtils.getRandomNum(8));
        userInfoDto.setCreateDate(System.currentTimeMillis());
        if(!userInfoMapper.insertUser(userInfoDto).equals(1)){
            throw new BusinessException(ExceptionConstants.SERVER_ERROR);
        }
        return ResponsePack.success(null);
    }

    /**
     * 获取验证码图片
     * @param
     * @return
     * @throws BusinessException
     */
    @PostMapping("/code")
    public ResponsePack getCode() throws BusinessException {
        //创建一个验证码对象
        SpecCaptcha captcha = new SpecCaptcha(130,48,5);
        //设置模式位字母数字混合
        captcha.setCharType(SpecCaptcha.TYPE_DEFAULT);
        String code = captcha.text().toLowerCase();
        log.info("验证码:{}",code);
        CodeCo codeCo = new CodeCo();
        codeCo.setCode(code);
        codeCo.setCreateDate(System.currentTimeMillis());
        String id = String.valueOf(UUID.randomUUID());
        codeCo.setId(id);
        codeCoMap.put(id,codeCo);
        CodeVo codeVo = new CodeVo();
        codeVo.setId(id);
        codeVo.setBase64(captcha.toBase64());
        //返回验证码图片
        return ResponsePack.success(codeVo);
    }

    /**
     * 定时清理验证码缓存
     */
    @Scheduled(fixedRate = 1000*10*60)
    private void clearCode(){
        log.debug("正在清理验证码缓存");
        int initSize = codeCoMap.size();
        codeCoMap.entrySet().removeIf(entry->entry.getValue().getCreateDate()+60*1000<System.currentTimeMillis());
        int endSize = codeCoMap.size();
        if(initSize!=endSize){
            log.debug("清理验证码缓存成功,清理前数量:{},清理后数量:{},共清理{}",initSize,endSize,initSize-endSize);
        }
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
