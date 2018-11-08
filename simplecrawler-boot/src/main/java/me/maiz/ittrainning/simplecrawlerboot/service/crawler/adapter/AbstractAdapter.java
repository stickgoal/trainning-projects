package me.maiz.ittrainning.simplecrawlerboot.service.crawler.adapter;

import me.maiz.ittrainning.simplecrawlerboot.service.crawler.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAdapter implements Adapter{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Parser parser = new Parser();


}
