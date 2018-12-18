package maiz.me.toyapplication.integration;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CommonCallBack<T> implements Callback<T> {

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.i("CommonCallBack","访问外部错误",t);
    }
}
