package me.maiz.ittrainning.simplecrawlerboot.dal;

import me.maiz.ittrainning.simplecrawlerboot.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Integer> {
}
