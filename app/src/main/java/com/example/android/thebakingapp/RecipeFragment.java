package com.example.android.thebakingapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Arjun Vidyarthi on 10-Mar-18.
 */

public class RecipeFragment extends android.support.v4.app.Fragment implements IngredientsAdapter.IngredientAdapterOnClickHandler {
    String LOG_TAG;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    Activity context;

    public RecipeFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.ing_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IngredientsAdapter(rootView.getContext(), RecipeFragment.this );

        int id = getArguments().getInt("RECIPE_ID");

        String[] args = {"https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json", String.valueOf(id)};
        new IngredientAsyncTask().execute(args);

        return rootView;
    }

    private class IngredientAsyncTask extends AsyncTask<String, Void, ArrayList<RecipeIngredients>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<RecipeIngredients> doInBackground(String... strings) {
            if(strings.length==0){
                return null;
            }

            String URL = strings[0];
            int id = Integer.parseInt(strings[1]);

            try{

                return NetworkUtils.networkReqForIngredients(URL, id);
            }
            catch(Exception e){
                Log.e(LOG_TAG, "Error with AyncTask retrieval!");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeIngredients> recipeIngredients) {
            if (recipeIngredients != null){
                adapter.setRecipeIngredients(recipeIngredients);
                recyclerView.setAdapter(adapter);
            }
            else return;
        }
    }

    @Override
    public void onClick(RecipeIngredients thisRecipeIngs) {

    }
}
