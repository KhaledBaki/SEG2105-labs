package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        Team team = (Team) getIntent().getSerializableExtra("teamInfo");

        TextView teamName = (TextView) findViewById(R.id.teamNameTextViewId);
        teamName.setText(team.getName());

        TextView teamPostalCode = (TextView) findViewById(R.id.postalCodeTextViewId);
        teamPostalCode.setText(team.getPostalCode());

        ImageView logoImage = (ImageView) findViewById(R.id.teamLogoId);

        @SuppressLint("DiscouragedApi") int resID = getResources().getIdentifier(team.getDrawableName(), "drawable", getPackageName());
        logoImage.setImageResource(resID);
    }
}