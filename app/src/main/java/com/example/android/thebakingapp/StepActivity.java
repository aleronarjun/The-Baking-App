package com.example.android.thebakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepActivity extends AppCompatActivity {
    String LOG_TAG;
    @BindView(R.id.step_video)
    VideoView videoView;

    @BindView(R.id.step_thumb)
    ImageView stepThumb;

    @BindView(R.id.step_desc_long)
    TextView step_desc;

    ArrayList<Integer> allIDs;
    ArrayList<String> allDesc;
    ArrayList<String> allVid;
    ArrayList<String> allThumb;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        try {
            current = extras.getInt("THIS_ID");
            allIDs = extras.getIntegerArrayList("ALL_IDS");
            allDesc = extras.getStringArrayList("ALL_DESC");
            allVid = extras.getStringArrayList("ALL_VID");
            allThumb = extras.getStringArrayList("ALL_THUMB");

            step_desc.setText(allDesc.get(current));

            if(!allVid.get(current).equals("")){
                videoView.setVideoPath(allVid.get(current));
                videoView.start();
                stepThumb.setVisibility(View.GONE);
            }

            else if(!allThumb.get(current).equals("")){
                Picasso.with(this).load(allThumb.get(current)).into(stepThumb);
                videoView.setVisibility(View.GONE);
            }

            else{
                videoView.setVisibility(View.GONE);

            }
        }
        catch(NullPointerException e)
        {
            Log.e(LOG_TAG,"Null pointer in intent");
            e.printStackTrace();
        }


    }
}
