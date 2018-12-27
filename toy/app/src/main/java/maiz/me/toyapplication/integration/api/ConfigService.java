package maiz.me.toyapplication.integration.api;

import java.util.List;

import maiz.me.toyapplication.integration.api.dto.CrawlConfig;
import maiz.me.toyapplication.integration.api.dto.PageResult;
import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConfigService {

    @POST("addConfig")
    Call<Result> addConfig(
                            @Query("userId")int userId,
                            @Query("configName") String configName,
                           @Query("titleSelector")String titleSelector,
                           @Query("seedsUrl")String seedsUrl
                           );

    @GET("queryConfig")
    Call<PageResult<CrawlConfig>> queryConfig(@Query("userId")int userId, @Query("index")int index, @Query("pageSize")int pageSize);
}
