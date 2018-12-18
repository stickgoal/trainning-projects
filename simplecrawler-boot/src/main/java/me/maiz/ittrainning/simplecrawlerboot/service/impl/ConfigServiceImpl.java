package me.maiz.ittrainning.simplecrawlerboot.service.impl;

import me.maiz.ittrainning.simplecrawlerboot.dal.ConfigRepo;
import me.maiz.ittrainning.simplecrawlerboot.domain.Config;
import me.maiz.ittrainning.simplecrawlerboot.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepo configRepo;

    @Override
    public boolean addConfig(Config config) {
        try {
            configRepo.save(config);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Config> queryConfig(int userId, int pageIdx, int pageSize) {

        Pageable pageable = PageRequest.of(pageIdx,pageSize);
       return configRepo.findByUserId(userId,pageable);
    }
}
