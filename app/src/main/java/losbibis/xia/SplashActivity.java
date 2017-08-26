package losbibis.xia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;


import android.os.Bundle;

import android.content.Intent;
import android.text.Layout;
import android.widget.LinearLayout;

/**
 * Created by 46519757 on 06/11/2016.
 */

public class SplashActivity extends Activity {

    LinearLayout layoutsplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

    Thread timerThread = new Thread(){
        public void run(){
            try{
                sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        layoutsplash = (LinearLayout) findViewById(R.id.splash);
                        ;
                        layoutsplash.setBackgroundResource(R.drawable.los_bibis_paysage);

                    }
                });
                sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            finally{




                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                }

    };
    timerThread.start();


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}