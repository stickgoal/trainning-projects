package me.maiz.ittrainning.simplecrawlerboot.service;

import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConfigService {

    /**
     * 添加配置
     * @param config
     * @return
     */
    boolean addConfig(Config config);

    Page<Config> queryConfig(int userId, int pageIdx, int pageSize);

}
