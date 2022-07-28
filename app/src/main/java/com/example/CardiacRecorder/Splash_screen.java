package com.example.CardiacRecorder;


import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * App opening splash screen.
 */

public class Splash_screen extends AppCompatActivity {
    // initialize variable
    ImageView ivTop,ivHeart,ivBeat,ivBottom;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay=200;
    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ivTop = findViewById(R.id.iv_top);
        ivHeart = findViewById(R.id.iv_heart);
        ivBeat = findViewById(R.id.iv_beat);
        ivBottom = findViewById(R.id.iv_bottom);
        textView = findViewById(R.id.text);

        //set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize top animation
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave);
        //start top animation
        ivTop.setAnimation(animation1);
        // initialize object anuimator

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivHeart,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );
        //set duration
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
        //setanimate text
        animatText("CardiacRecord");
        //initialize bottom animation
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        ivBottom.setAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Redirct the main activity
                startActivity(new Intent(Splash_screen.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        } ,4000);



    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            textView.setText(charSequence.subSequence(0,index++));
            if (index <= charSequence.length()){
                //when index is equal to text length
                //run hundler
                handler.postDelayed(runnable,delay);
            }
        }
    };
    //create animated text method
    public void animatText (CharSequence cs)
    {
        //set text
        charSequence=cs;
        //clear index,text
        index=0;
        textView.setText("");
        handler.removeCallbacks(runnable);
        //run handler
        handler.postDelayed(runnable,delay);

    }
}