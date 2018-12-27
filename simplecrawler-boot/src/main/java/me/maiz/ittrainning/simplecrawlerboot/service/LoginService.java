package me.maiz.ittrainning.simplecrawlerboot.service;

import me.maiz.ittrainning.simplecrawlerboot.domain.User;
import me.maiz.ittrainning.simplecrawlerboot.web.form.LoginForm;

public interface LoginService {

    User login(LoginForm form);

}
