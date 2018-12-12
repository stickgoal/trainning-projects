package maiz.me.testa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;


import maiz.me.testa.R;

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(SplashActivity.class.getName()+"=======>","splash");
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Log.i(SplashActivity.class.getName()+"=======>","准备启动login");

                /* Create an Intent that will start the Main WordPress Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity2.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();

            }
        }, 2900); //2900 for release

    }
}
