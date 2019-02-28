package me.maiz.project.mblog.dal;

import me.maiz.project.mblog.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo,Integer> {

}
