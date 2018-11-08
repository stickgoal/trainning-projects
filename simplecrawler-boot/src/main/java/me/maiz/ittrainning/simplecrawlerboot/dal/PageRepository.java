package me.maiz.ittrainning.simplecrawlerboot.dal;


import me.maiz.ittrainning.simplecrawlerboot.domain.Page;

/**
 * 仓储服务，存储到本地
 */
public interface PageRepository {

    /**
     * 持久化页面
     * @param page
     */
    void save(Page page);

}
