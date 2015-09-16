package testproject.com.gryphon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ImageButton mNavButtonBecomeAChef;
    ImageButton mNavButtonFavorites;
    ImageButton mNavButtonHome;
    ImageButton mNavButtonProfile;
    ImageButton mNavButtonSettings;

    ImageView mImageViewMeal1;
    ImageView mImageViewMeal2;
    ImageView mImageViewMeal3;
    ImageView mImageViewMeal4;
    ImageView mImageViewMeal5;
    ImageView mImageViewMeal6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavButtonBecomeAChef = (ImageButton)findViewById(R.id.navButtonBecomeAChef);
        mNavButtonFavorites = (ImageButton)findViewById(R.id.navButtonFavorites);
        mNavButtonHome = (ImageButton)findViewById(R.id.navButtonHome);
        mNavButtonProfile = (ImageButton)findViewById(R.id.navButtonProfile);
        mNavButtonSettings = (ImageButton)findViewById(R.id.navButtonSettings);

        mImageViewMeal1 = (ImageView)findViewById(R.id.imageViewMeal1);
        mImageViewMeal2 = (ImageView)findViewById(R.id.imageViewMeal2);
        mImageViewMeal3 = (ImageView)findViewById(R.id.imageViewMeal3);
        mImageViewMeal4 = (ImageView)findViewById(R.id.imageViewMeal4);
        mImageViewMeal5 = (ImageView)findViewById(R.id.imageViewMeal5);
        mImageViewMeal6 = (ImageView)findViewById(R.id.imageViewMeal6);

        mImageViewMeal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmationActivity.class);
                startActivity(i);
            }
        });

        mImageViewMeal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmation2Activity.class);
                startActivity(i);
            }
        });

        mImageViewMeal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmation3Activity.class);
                startActivity(i);
            }
        });

        mImageViewMeal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmationActivity.class);
                startActivity(i);
            }
        });

        mImageViewMeal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmation2Activity.class);
                startActivity(i);
            }
        });

        mImageViewMeal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, MealConfirmation3Activity.class);
                startActivity(i);
            }
        });

        mNavButtonBecomeAChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testToast(R.string.test_become_a_chef, Toast.LENGTH_SHORT);
            }
        });

        mNavButtonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, FavoritesMealsActivity.class);
                startActivity(i);
            }
        });

        mNavButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        mNavButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testToast(R.string.test_settings, Toast.LENGTH_SHORT);
            }
        });

    }

    private void testToast(int resId, int duration)
    {
        Toast.makeText(this, resId, duration).show();
    }

    private void testCommit()
    {
        System.out.println("Commit attempt");
    }

}
