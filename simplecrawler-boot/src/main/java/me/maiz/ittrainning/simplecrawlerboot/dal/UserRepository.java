package me.maiz.ittrainning.simplecrawlerboot.dal;

import me.maiz.ittrainning.simplecrawlerboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
