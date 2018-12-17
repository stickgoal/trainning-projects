package maiz.me.toyapplication.integration.api;

import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConfigService {

    @POST("addConfig")
    Call<Result> addConfig(@Query("configName") String configName,
                           @Query("titleSelector")String titleSelector,
                           @Query("seedsUrl")String seedsUrl
                           );

}
