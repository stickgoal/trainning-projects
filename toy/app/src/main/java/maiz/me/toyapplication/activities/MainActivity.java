package maiz.me.toyapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.integration.api.LoginService;
import maiz.me.toyapplication.integration.api.dto.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //构建Retrofit客户端
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //得到服务
        LoginService loginService = retrofit.create(LoginService.class);
        Call<Result> call = loginService.login("lucas", "123456");

        Log.i(TAG,"执行查询...");

        //异步调用
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //通信成功，处理调用结果
                Log.i(TAG,response.raw().toString());

                if(response.isSuccessful()){
                    Result result = response.body();
                    Log.i(TAG,result.toString());
                    Toast.makeText(MainActivity.this,"请求成功："+result.toString(),Toast.LENGTH_SHORT).show();

                }else{
                    Log.w(TAG,"请求出错，"+response.raw());
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //通信失败，处理失败结果
                Log.w(TAG,t.getMessage());
                t.printStackTrace();
            }
        });


    }
}
