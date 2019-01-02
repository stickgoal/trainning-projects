package me.maiz.project.mblog.dal;

import me.maiz.project.mblog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog,Integer> {
}
