package cn.alex.demosplit.serviceum.service;

import cn.alex.demosplit.serviceum.Exception.UserLoginException;
import cn.alex.demosplit.serviceum.dao.UserDao;
import cn.alex.demosplit.serviceum.dto.JWT;
import cn.alex.demosplit.serviceum.dto.User;
import cn.alex.demosplit.serviceum.dto.UserLoginDTO;
import cn.alex.demosplit.serviceum.sao.auth.AuthServiceClient;
import cn.alex.demosplit.serviceum.util.BPwdEncoderUtil;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserDao userRepository;

    public User insertUser(String username, String  password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }

    @Autowired
    private AuthServiceClient client;


    public UserLoginDTO login(String username, String password){
        log.info("try to login user["+username+"],pass["+password+"]");
        User user=userRepository.findByUsername(username);
        if (null == user) {
            log.info("user is null");
            throw new UserLoginException("error username");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            log.info("user is incorrect");
            throw new UserLoginException("error password");
        }
        log.info("user is correct");
        // 获取token
        JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        // 获得用户菜单
        if(jwt==null){
            log.info("JWT token is incorrect");
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;

    }

}