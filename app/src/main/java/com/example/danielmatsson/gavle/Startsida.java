package com.example.danielmatsson.gavle;

import android.os.Bundle;
import android.widget.ImageButton;

/**
 * Created by danielmatsson on 2016-09-27.
 */

public class Startsida {

    ImageButton inställingar2ImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startsida_layout);

        inställingar2ImageButton = (ImageButton) findViewById(R.id.image_button_inställningar2);
    }
}
