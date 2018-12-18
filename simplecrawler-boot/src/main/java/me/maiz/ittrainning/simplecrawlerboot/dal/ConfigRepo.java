package me.maiz.ittrainning.simplecrawlerboot.dal;

import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepo extends JpaRepository<Config,Integer> {

    List<Config> findByUserId(int userId, Pageable pageable);

}
