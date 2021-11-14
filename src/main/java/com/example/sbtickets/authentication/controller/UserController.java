package com.example.sbtickets.authentication.controller;

import com.example.sbtickets.authentication.entity.User;
import com.example.sbtickets.bean.AuthenticationBean;
import com.example.sbtickets.bean.UserBean;
import com.example.sbtickets.authentication.service.JwtService;
import com.example.sbtickets.authentication.service.UserService;
import com.example.sbtickets.common.UrlConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = UrlConst.LOGIN_SBTICKETS, method = RequestMethod.POST)
    public ResponseEntity<AuthenticationBean> login(HttpServletRequest request, @RequestBody UserBean user) {
        AuthenticationBean result = new AuthenticationBean();
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(new User(user.getUsername(), user.getPassword()))) {
                result.setToken(jwtService.generateToken(new User(user.getUsername(), user.getPassword())));
                result.setStatus(HttpStatus.OK.value());
            } else {
                result.setMessage("Wrong userId and password");
                result.setStatus(HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<AuthenticationBean>(result, HttpStatus.OK);
    }
}
