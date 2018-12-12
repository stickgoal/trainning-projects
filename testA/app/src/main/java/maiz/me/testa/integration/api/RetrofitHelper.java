package maiz.me.testa.integration.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper<T> {
    public  T getService(Class<T> serviceClass){
       return  new Retrofit.Builder().baseUrl("http://10.0.2.2:8081")
               .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(serviceClass);
    }
}
