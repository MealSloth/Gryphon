package testproject.com.gryphon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesChefsActivity extends Activity {

    ImageButton mNavButtonBecomeAChef;
    ImageButton mNavButtonFavorites;
    ImageButton mNavButtonHome;
    ImageButton mNavButtonProfile;
    ImageButton mNavButtonSettings;

    TextView mTextViewFavoritesMeals;
    TextView mTextViewFavoritesChefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_chefs);

        mNavButtonBecomeAChef = (ImageButton)findViewById(R.id.navButtonBecomeAChef);
        mNavButtonFavorites = (ImageButton)findViewById(R.id.navButtonFavorites);
        mNavButtonHome = (ImageButton)findViewById(R.id.navButtonHome);
        mNavButtonProfile = (ImageButton)findViewById(R.id.navButtonProfile);
        mNavButtonSettings = (ImageButton)findViewById(R.id.navButtonSettings);

        mTextViewFavoritesMeals = (TextView)findViewById(R.id.textViewFavoritesMeals);
        mTextViewFavoritesChefs = (TextView)findViewById(R.id.textViewFavoritesChefs);

        mNavButtonBecomeAChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testToast(R.string.test_become_a_chef, Toast.LENGTH_SHORT);
            }
        });

        mNavButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(FavoritesChefsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        mNavButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(FavoritesChefsActivity.this, ProfileActivity.class);
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

        mTextViewFavoritesMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(FavoritesChefsActivity.this, FavoritesMealsActivity.class);
                startActivity(i);
            }
        });

    }

    private void testToast(int resId, int duration)
    {
        Toast.makeText(this, resId, duration).show();
    }

}
