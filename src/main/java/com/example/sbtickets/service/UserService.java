package com.example.sbtickets.service;

import com.example.sbtickets.entity.User;
import com.example.sbtickets.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImplement {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<User> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean add(User user) {
        List<User> listUser = accountRepository.findAll();
        for (User userExist : listUser) {
            if (user.getId() == userExist.getId() || user.getUserName().equals(userExist.getUserName())) {
                return false;
            }
        }
        accountRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            accountRepository.deleteById(id);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkLogin(User user) {
        List<User> listUser = accountRepository.findAll();
        for (User userExist : listUser) {
            if (user.getUserName().equals(userExist.getUserName())
                    && user.getPassword().equals(userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User loadUserByUsername(String userName) {
        List<User> listUser = accountRepository.findAll();
        for (User user : listUser) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

}

