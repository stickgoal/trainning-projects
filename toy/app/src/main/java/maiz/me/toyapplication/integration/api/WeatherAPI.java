package maiz.me.toyapplication.integration.api;

import maiz.me.toyapplication.integration.api.dto.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherAPI {

    @GET("/api/weather/city/{cityCode}")
    Call<WeatherInfo> queryWeather(@Path("cityCode") String cityCode);

}
