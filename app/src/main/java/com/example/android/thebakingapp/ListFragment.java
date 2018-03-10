package com.example.android.thebakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Arjun Vidyarthi on 10-Mar-18.
 */

public class ListFragment extends android.support.v4.app.Fragment implements ListAdapter.ListAdapterOnClickHandler {

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

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter adapter = new ListAdapter(rootView.getContext(), ListFragment.this );
        recyclerView.setAdapter(adapter);

        return rootView;

    }

    @Override
    public void onClick(RecipeName thisRecipe) {

    }
}
