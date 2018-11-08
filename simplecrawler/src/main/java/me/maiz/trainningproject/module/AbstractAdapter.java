package me.maiz.trainningproject.module;

import me.maiz.trainningproject.core.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAdapter implements Adapter{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Parser parser = new Parser();



}
