package maiz.me.toyapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import maiz.me.toyapplication.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(()->{
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        },2900);

    }
}
