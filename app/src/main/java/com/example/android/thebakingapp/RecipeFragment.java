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

import butterknife.BindView;

/**
 * Created by Arjun Vidyarthi on 10-Mar-18.
 */

public class RecipeFragment extends android.support.v4.app.Fragment implements IngredientsAdapter.IngredientAdapterOnClickHandler, StepAdapter.StepAdapterOnClickHandler {
    String LOG_TAG;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    Activity context;
    RecyclerView recyclerViewSteps;
    StepAdapter stepAdapter;
    ArrayList<RecipeSteps> all_steps;

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

        recyclerViewSteps = (RecyclerView) rootView.findViewById(R.id.steps_list);
        recyclerViewSteps.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(rootView.getContext());
        recyclerViewSteps.setLayoutManager(layoutManager1);
        stepAdapter = new StepAdapter(rootView.getContext(), RecipeFragment.this);

        int id = getArguments().getInt("RECIPE_ID");
        String[] args = {"https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json", String.valueOf(id)};
        new IngredientAsyncTask().execute(args);
        new StepsAsyncTask().execute(args);

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

    private class StepsAsyncTask  extends AsyncTask<String, Void, ArrayList<RecipeSteps>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<RecipeSteps> doInBackground(String... strings) {
            if(strings.length==0){
                return null;
            }

            String URL = strings[0];
            int id = Integer.parseInt(strings[1]);

            try{
                return NetworkUtils.networkReqForAllSteps(URL, id);
            }
            catch (Exception e){
                Log.e(LOG_TAG, "Error with AyncTask retrieval!");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeSteps> recipeSteps) {
            if (recipeSteps != null){
                stepAdapter.setRecipeSteps(recipeSteps);
                recyclerViewSteps.setAdapter(stepAdapter);
                all_steps = recipeSteps;
            }
            else return;
        }
    }

    @Override
    public void onClick(RecipeIngredients thisRecipeIngs) {

    }

    @Override
    public void onClick(RecipeSteps thisRecipeSteps) {
        Intent intent = new Intent(context, StepActivity.class);
        Bundle extras = new Bundle();

        extras.putInt("THIS_ID", thisRecipeSteps.getId());
        ArrayList<Integer> allIDs = new ArrayList<>();
        ArrayList<String> allDesc = new ArrayList<>();
        ArrayList<String> allVid = new ArrayList<>();
        ArrayList<String> allThumb = new ArrayList<>();

        for(int i=0;i<all_steps.size();i++){
            allIDs.add(i,all_steps.get(i).getId());
        }
        for(int i=0;i<all_steps.size();i++){
            allDesc.add(i,all_steps.get(i).getDescription());
        }
        for(int i=0;i<all_steps.size();i++){
            allVid.add(i,all_steps.get(i).getVideoURL());
        }
        for(int i=0;i<all_steps.size();i++){
            allThumb.add(i,all_steps.get(i).getThumbURL());
        }

        extras.putIntegerArrayList("ALL_IDS",  allIDs );
        extras.putStringArrayList("ALL_DESC", allDesc);
        extras.putStringArrayList("ALL_VID", allVid);
        extras.putStringArrayList("ALL_THUMB", allThumb);

        intent.putExtras(extras);
        startActivity(intent);
    }
}
