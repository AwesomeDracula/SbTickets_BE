package com.example.sbtickets.controller;

import com.example.sbtickets.bean.AuthenticationBean;
import com.example.sbtickets.bean.UserBean;
import com.example.sbtickets.authentication.service.JwtService;
import com.example.sbtickets.bean.UserRegisterBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.User;
import com.example.sbtickets.service.CustomerService;
import com.example.sbtickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UserController{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = UrlConst.LOGIN_SBTICKETS, method = RequestMethod.POST)
    public ResponseEntity<AuthenticationBean> login(@RequestBody UserBean user) {
        AuthenticationBean result = new AuthenticationBean();
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(new User(user.getUsername(), user.getPassword()))) {
                result.setToken(jwtService.generateToken(new User(user.getUsername(), user.getPassword())));
                result.setStatus(HttpStatus.OK.value());
                if(user.getUsername().equals("Admin")){
                    result.setRole("ROLE_ADMIN");
                }
                else{
                    result.setRole("ROLE_USER");
                }
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

    @RequestMapping(value = UrlConst.USER_REGISTER, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> registerAccount(@RequestBody UserRegisterBean userbean) {
        WrapperResponse result = new WrapperResponse();
        try {
            if(userService.checkUserName(userbean.getUserName())){
                result.setMsg("UserName exits");
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                return  new ResponseEntity<WrapperResponse>(result, HttpStatus.FAILED_DEPENDENCY);
            }
            else{
                // update User vao db
                User user = new User(userbean.getUserName(), userbean.getPassWord());
                user.setRole("ROLE_USER");
                userService.add(user);
                //
                user = userService.loadUserByUsername(user.getUserName());
                if(customerService.addCustomer(new Customer(userbean.getUserName(), userbean.getCmt(), userbean.getAddress(), userbean.getBirthDay(), user))){
                    result.setMsg("Create Account SuccessFull");
                    result.setStatus(HttpStatus.OK.value());
                    return  new ResponseEntity<WrapperResponse>(result, HttpStatus.OK);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return  new ResponseEntity<WrapperResponse>(result, HttpStatus.FAILED_DEPENDENCY);
        }
        return  new ResponseEntity<WrapperResponse>(result, HttpStatus.FAILED_DEPENDENCY);
    }
}
