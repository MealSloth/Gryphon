package testproject.com.gryphon;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class SplashActivity extends Activity {
        private static int SPLASH_DISPLAY_LENGTH = 2000;

            /*splash screen for 2 seconds*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Intent created to move to PageTwo --->Login Page...2 :: "+savedInstanceState + "   value");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Intent created to move to PageTwo --->Login Page */
                System.out.println("------------------------------------------------");
                System.out.println("Intent created to move to PageTwo --->Login Page..3");
//                   Intent i = new Intent(MainActivity.this, PageTwo.class);
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(i);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

