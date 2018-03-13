package com.example.android.thebakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Arjun Vidyarthi on 11-Mar-18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder>{
    private String LOG_TAG;
    private ArrayList<RecipeSteps> recipeSteps = null;
    private final StepAdapterOnClickHandler handler;
    private Context activity_context;

    public StepAdapter (Context context, StepAdapterOnClickHandler handler){
        this.handler = handler;
        activity_context = context;
    }

    public interface StepAdapterOnClickHandler {
        void onClick(RecipeSteps thisRecipeSteps);
    }

    public void setRecipeSteps (ArrayList<RecipeSteps> steps){
        recipeSteps = steps;
    }

    @Override
    public StepAdapter.StepAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(idForListItem, parent, shouldAttachToParentImmediately);
        return new StepAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepAdapter.StepAdapterViewHolder holder, int position) {
        if(recipeSteps == null){
            Log.e(LOG_TAG, "Recipe steps are null!");
            return;
        }


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public StepAdapterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
