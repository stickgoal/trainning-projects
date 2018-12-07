package me.maiz.ittrainning.simplecrawlerboot.service.impl;

import me.maiz.ittrainning.simplecrawlerboot.dal.UserRepository;
import me.maiz.ittrainning.simplecrawlerboot.domain.User;
import me.maiz.ittrainning.simplecrawlerboot.service.LoginService;
import me.maiz.ittrainning.simplecrawlerboot.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean login(LoginForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        Example<User> userExample = Example.of(user);
        Optional<User> exist = userRepo.findOne(userExample);
        return exist.isPresent();
    }
}
