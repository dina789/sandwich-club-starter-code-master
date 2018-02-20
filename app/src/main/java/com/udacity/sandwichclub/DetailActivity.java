package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    Sandwich sandwich;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        TextView tv_aka = (TextView) findViewById(R.id.also_known_tv);
        TextView tv_description = (TextView) findViewById(R.id.description_tv);
        TextView tv_ingredients = (TextView) findViewById(R.id.ingredients_tv);
        TextView tv_origin = (TextView) findViewById(R.id.origin_tv);


        if (sandwich.getDescription().isEmpty()) {

            tv_description.setText(" not available !!");

        } else {
            tv_description.setText(sandwich.getDescription());
        }


        if (sandwich.getIngredients().isEmpty()) {
            tv_ingredients.setText(" not available !!");
        } else {

            tv_ingredients .setText(String.valueOf(sandwich.getIngredients()));

            }


            if (sandwich.getAlsoKnownAs().isEmpty()) {
                tv_aka.setText(" not available !!");
            } else {
                tv_aka.setText(String.valueOf(sandwich.getAlsoKnownAs()));

            }


            if (sandwich.getPlaceOfOrigin().isEmpty()) {
                tv_origin.setText(" not available !!");
            } else {
                tv_origin.setText(sandwich.getPlaceOfOrigin());
            }
        }


    }

