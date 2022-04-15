package com.example.dice_roll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    TextToSpeech textToSpeech;
    int[] Dice = {R.drawable.diceone, R.drawable.dicetwo, R.drawable.dicethree, R.drawable.dicefour, R.drawable.dicefive, R.drawable.dicesix};
    private Button button_btn;
    private TextView number1_txt, number2_txt, total_txt;
    private int res1;
    private ImageView img1;
    private RotateAnimation rotate;
    public static final Random RANDOM = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        blinding();

        button_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int n1 = random.nextInt(Dice.length);
                int n2 = random.nextInt(Dice.length);

                img.setImageResource(Dice[n1]);
                img1.setImageResource(Dice[n2]);

                rotate = new RotateAnimation(1, 1);
                rotate.setDuration(50);

                img.startAnimation(rotate);
                img1.startAnimation(rotate);

                number1_txt.setText("" + (n1 + 1));
                number2_txt.setText("" + (n2 + 1));
                int a = Integer.parseInt(String.valueOf(n1));
                int b = Integer.parseInt(String.valueOf(n2));
                Vibrator vibe = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                int x = (a + 1) + (b + 1);
                total_txt.setText("" + x);
                textToSpeech.speak(total_txt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value = randomDiceValue();
                        int res = getResources().getIdentifier("dice_" + value, "drawable", "com.ssaurel.dicer");

         /*   if (animation == anim1) {
                image1_btn.setImageResource(res);
            } else if (animation == anim2) {
                image2_btn.setImageResource(res);
            }*/
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                anim1.setAnimationListener(animationListener);
                anim2.setAnimationListener(animationListener);

                img.startAnimation(anim1);
                img1.startAnimation(anim2);
//                dice_sounds.play(sound_shake, 1.0f, 1.0f, 0, 0, 1.0f);


            }

        });
        textToSpeech = new

                TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    private void blinding() {
        img = findViewById(R.id.img);
        button_btn = findViewById(R.id.button_btn);
        number1_txt = findViewById(R.id.number1_txt);
        number2_txt = findViewById(R.id.number2_txt);
        total_txt = findViewById(R.id.total_txt);
        img1 = findViewById(R.id.img1);

    }


    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }

}