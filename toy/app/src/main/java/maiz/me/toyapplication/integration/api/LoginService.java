package maiz.me.toyapplication.integration.api;

import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {

    @GET("/login")
    Call<Result> login(@Query("username") String username, @Query("password") String password);

}
