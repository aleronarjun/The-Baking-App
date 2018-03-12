package com.example.android.thebakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int id = extras.getInt("RECIPE_ID");

        Bundle bundle=new Bundle();
        bundle.putInt("RECIPE_ID", id);

        RecipeFragment recipeFragment = new RecipeFragment();
        recipeFragment.setArguments(bundle);

        if(savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().add(R.id.ing_steps_container, recipeFragment).commit();

        }
    }
}
