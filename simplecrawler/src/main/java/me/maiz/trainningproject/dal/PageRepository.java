package me.maiz.trainningproject.dal;

import me.maiz.trainningproject.model.Page;

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
