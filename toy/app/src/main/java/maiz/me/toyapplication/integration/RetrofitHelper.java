package maiz.me.toyapplication.integration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public <S> S getService(Class<S> s){
        //构建Retrofit客户端
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //得到服务
        return retrofit.create(s);
    }

}
