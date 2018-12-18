package maiz.me.toyapplication.integration.api;

import java.util.List;

import maiz.me.toyapplication.integration.api.dto.News;
import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CrawlService {

    @POST("crawling/start")
    Call<Result> crawl(@Query("configId") int configId);

    @GET("crawling/result")
    Call<Result<List<News>>> getNewsResult(@Query("userId")int userId, @Query("index")int index, @Query("pageSize")int pageSize);

}
