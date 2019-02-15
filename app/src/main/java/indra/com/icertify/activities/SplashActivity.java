package indra.com.icertify.activities;

import indra.com.icertify.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import indra.com.icertify.activities.login.LoginActivity;


public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent( SplashActivity.this, SophiaMainActivity.class);
                startActivity(i);
            }
        }, 5000);
    }
}
