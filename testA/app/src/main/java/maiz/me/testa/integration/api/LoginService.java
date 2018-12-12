package maiz.me.testa.integration.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {

    @GET("user/login")
    Call<Result> login(@Query("username")String username, @Query("password")String password);

}
