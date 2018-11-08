package me.maiz.ittrainning.simplecrawlerboot.dal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.List;

public interface NewsRepo extends JpaRepository<> {

    void save(List newsList) throws IOException;

}
