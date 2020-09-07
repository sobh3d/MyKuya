package com.sobhan.mykuya.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sobhan.mykuya.R;

public class ProgressButton {
    private ConstraintLayout layout;
    private CardView cardView;
    private TextView textView;
    private ProgressBar progressBar;
    Animation fad_in;

    public ProgressButton(Context context, View view) {
        cardView = view.findViewById(R.id.card);
        textView = view.findViewById(R.id.tv_confirm);
        progressBar = view.findViewById(R.id.pb_map);
        layout = view.findViewById(R.id.const_progress);
    }

    public void buttonActivated() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }

    public void buttonFinished() {
        progressBar.setVisibility(View.GONE);
        //  textView.setVisibility(View.VISIBLE);
    }
}
