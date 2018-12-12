package maiz.me.testa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import maiz.me.testa.R;
import maiz.me.testa.integration.api.LoginService;
import maiz.me.testa.integration.api.Result;
import maiz.me.testa.integration.api.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

public class LoginActivity2  extends AppCompatActivity {
private static final String TAG="LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LoginActivity","login...");
        setContentView(R.layout.activity_login);
        ToastUtils.init(true);

        AppCompatButton loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener((view)->{
            Toast.makeText(this, "登录按钮被点击...", Toast.LENGTH_SHORT).show();

            LoginService loginService = new RetrofitHelper<LoginService>().getService(LoginService.class);
            TextInputEditText userEditText = findViewById(R.id.username);
            String username = userEditText.getText().toString();
            TextInputEditText passwordEditText = findViewById(R.id.password);
            String password = passwordEditText.getText().toString();
            Log.i(TAG,"准备网络请求...");

            //retrofit+rxjava处理网络请求
            Call<Result> callResult = loginService.login(username,password);
            //
            callResult.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Log.i(TAG,response.raw().toString());
                    Result result = response.body();

                    if(result.isSuccess()){
                        Intent intent = new Intent(LoginActivity2.this,MainActivity.class);
                        LoginActivity2.this.startActivity(intent);
                        LoginActivity2.this.finish();
                    }else{
                        Toast.makeText(LoginActivity2.this, "登录失败【"+result.getCode()+"]", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.w(TAG,"失败");
                    t.printStackTrace();
                }
            });

        });

    }
}
