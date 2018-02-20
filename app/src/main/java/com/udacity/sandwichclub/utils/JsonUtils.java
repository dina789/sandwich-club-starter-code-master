package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    private static final String sand_AKA = "alsoKnownAs";
    private static final String sand_ORIGIN = "placeOfOrigin";
    private static final String sand_DESCRIPTION = "description";
    private static final String sand_INGREDIENTS = "ingredients";
    private static final String sand_MAIN_NAME = "mainName";
  private static final String sand_NAME = "name";
    private static final String sand_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json){

        Sandwich mySandwich = new Sandwich();

        try {


        JSONObject sandwichDetails = new JSONObject(json);

        JSONObject jsonObjectName = sandwichDetails.getJSONObject(sand_NAME);




        mySandwich.setImage(sandwichDetails.getString(sand_IMAGE));
        mySandwich.setDescription(sandwichDetails.getString(sand_DESCRIPTION));
        mySandwich.setMainName(jsonObjectName.getString(sand_MAIN_NAME ));
        mySandwich.setPlaceOfOrigin(sandwichDetails.getString(sand_ORIGIN ));


        JSONArray jsonArrayAka = jsonObjectName.getJSONArray(sand_AKA);
        List<String> aka = new ArrayList<>();
        for (int i=0; i<jsonArrayAka.length();i++) {
            aka.add((String) jsonArrayAka.get(i));
        }
        mySandwich.setAlsoKnownAs(aka);

            JSONArray jsonArrayIngredients = sandwichDetails.getJSONArray(sand_INGREDIENTS );
        List<String> ingredients = new ArrayList<>();
        for (int i=0; i<jsonArrayIngredients.length();i++) {
            ingredients.add((String) jsonArrayIngredients.get(i));
        }

            mySandwich.setIngredients(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return   mySandwich;



    }}

    //https://developer.android.com/samples/Quiz/Application/src/com.example.android.wearable.quiz/JsonUtils.html