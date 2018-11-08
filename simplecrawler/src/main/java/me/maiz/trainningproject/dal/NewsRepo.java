package me.maiz.trainningproject.dal;


import java.io.IOException;
import java.util.List;

public interface NewsRepo {

    void save(List newsList,String configName) throws IOException;

}
