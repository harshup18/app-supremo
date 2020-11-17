package com.example.appsupremo.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appsupremo.R;
import com.example.appsupremo.entities.SuperHero;
import com.squareup.picasso.Picasso;

public class CharacterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_page);

        Intent intent = getIntent();
        SuperHero superHero =(SuperHero) intent.getSerializableExtra("Character");

        ImageView charImage = findViewById(R.id.char_image_cp);
        TextView name = findViewById(R.id.tv_name_cp);
        TextView desc = findViewById(R.id.tv_des_cp);
        TextView gender = findViewById(R.id.tv_gender_cp);
        TextView race = findViewById(R.id.tv_race_cp);
        TextView height = findViewById(R.id.tv_height_cp);
        TextView eyeColor = findViewById(R.id.tv_eye_cp);
        TextView hairColor = findViewById(R.id.tv_hair_cp);

        final String imageUri = superHero.getImage().getUrl();
        Picasso.with(this).load(imageUri).into(charImage);
        name.setText(superHero.getName());
        desc.setText(superHero.getDescription());
        gender.setText(String.format("Gender : %s", superHero.getAppearance().getGender()));
        race.setText(String.format("Race : %s", superHero.getAppearance().getRace()));
        height.setText(String.format("Height : %s", superHero.getAppearance().getHeight().get(0)));
        eyeColor.setText(String.format("Eye Color : %s", superHero.getAppearance().getEyeColor()));
        hairColor.setText(String.format("Hair Color : %s", superHero.getAppearance().getHairColor()));

    }
}
