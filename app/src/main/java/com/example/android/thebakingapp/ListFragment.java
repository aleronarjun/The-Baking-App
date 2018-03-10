package com.example.android.thebakingapp;

import android.content.Context;
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

public class ListFragment extends android.support.v4.app.Fragment implements ListAdapter.ListAdapterOnClickHandler {
    String LOG_TAG;
    RecyclerView recyclerView;
    ListAdapter adapter;

    public ListFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(rootView.getContext(), ListFragment.this );
        String[] URL = {"https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json"};
        new RecipeAyncTask().execute(URL);
        return rootView;

    }

    private class RecipeAyncTask extends AsyncTask<String, Void, ArrayList<RecipeName>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<RecipeName> doInBackground(String... strings) {
            if(strings.length==0){
                return null;
            }

            String URL = strings[0];

            try{
                return NetworkUtils.networkReqForNames(URL);
            }
            catch(Exception e){
                Log.e(LOG_TAG, "Error with AyncTask retrieval!");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeName> recipeNames) {
           if (recipeNames != null){
               adapter.setRecipeNames(recipeNames);
               recyclerView.setAdapter(adapter);
           }
           else return;
        }

    }

    @Override
    public void onClick(RecipeName thisRecipe) {

    }
}
