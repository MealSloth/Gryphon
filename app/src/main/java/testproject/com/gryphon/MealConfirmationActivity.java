package testproject.com.gryphon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MealConfirmationActivity extends Activity {

    ImageButton mNavButtonBecomeAChef;
    ImageButton mNavButtonFavorites;
    ImageButton mNavButtonHome;
    ImageButton mNavButtonProfile;
    ImageButton mNavButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_confirmation);

        mNavButtonBecomeAChef = (ImageButton)findViewById(R.id.navButtonBecomeAChef);
        mNavButtonFavorites = (ImageButton)findViewById(R.id.navButtonFavorites);
        mNavButtonHome = (ImageButton)findViewById(R.id.navButtonHome);
        mNavButtonProfile = (ImageButton)findViewById(R.id.navButtonProfile);
        mNavButtonSettings = (ImageButton)findViewById(R.id.navButtonSettings);

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
                Intent i = new Intent(MealConfirmationActivity.this, FavoritesMealsActivity.class);
                startActivity(i);
            }
        });

        mNavButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MealConfirmationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        mNavButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MealConfirmationActivity.this, ProfileActivity.class);
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

}