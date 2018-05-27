package com.spiderappdev.inductions18.rubikscubetimer;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
//import android.os.Looper;
import android.os.SystemClock;
//import android.renderscript.Sampler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Boolean hasInspectionStarted=false, hasSolvingStarted= false;
    int resetTapCounter=0;

    Boolean b3=true,b2=true,b1=true,bf=true;

    ConstraintLayout mainLayout;
    TextView inspectionTimer,solveTimer;


    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handlerI, handlerS;
    int Seconds, Minutes, MilliSeconds, MilliSecondsT, MilliSecondsR, Sec ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        Toast.makeText(getBaseContext(), "Tap screen to start Inspection time", Toast.LENGTH_SHORT).show();

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasInspectionStarted) {
                    //this means Inspection time didn't start
                    //So start it:

                    hasInspectionStarted = true;

                    StartTime = SystemClock.uptimeMillis();
                    handlerI.postDelayed(runnableI, 0);

                    //Log.d("debug.data", "Inspection Started");

                } else if (!hasSolvingStarted) {
                    //this means Inspection time already on but solve time is not
                    //So now start solveTime :

                    TimeBuff += MillisecondTime;
                    inspectionTimer_ender();


                } else {
                    handlerS.removeCallbacks(runnableS);
                    if(resetTapCounter==0){
                        resetTapCounter++;
                        Toast.makeText(getBaseContext(), "Tap again to reset", Toast.LENGTH_LONG).show();
                    }
                    else if(resetTapCounter==1){
                        resetAll();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Error???", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });  //END OF onCLickListener



    }


    public void initialise(){

        mainLayout = (ConstraintLayout)findViewById(R.id.mainLayout);
        inspectionTimer = (TextView)findViewById(R.id.inspectionTimer);
        solveTimer = (TextView)findViewById(R.id.solveTimer);

        handlerI = new Handler();
        handlerS = new Handler();

        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Sec = 0;
        Minutes = 0 ;
        MilliSeconds = 0 ;
        MilliSecondsR = 0;
        MilliSecondsT = 0;


    }

    public void inspectionTimer_start(){

        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;



    }

    public void inspectionTimer_ender(){

        handlerI.removeCallbacks(runnableI);


        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;

        //start solving time
        hasSolvingStarted = true;
        StartTime = SystemClock.uptimeMillis();
        handlerS.postDelayed(runnableS, 0);

        //Log.d("debug.data", "Solving Started");

    }

    public void resetAll(){
        resetTapCounter=0;

        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Sec = 0;
        Minutes = 0 ;
        MilliSeconds = 0 ;
        MilliSecondsR = 0;
        MilliSecondsT = 0;

        hasInspectionStarted=false;
        hasSolvingStarted=false;

        b3=true;
        b2=true;
        b1=true;
        bf=true;

        inspectionTimer.setText("15:00");
        solveTimer.setText("00:00:00");

    }

    public void beep(){

        try{
            final ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
            toneGen1.startTone(ToneGenerator.TONE_PROP_BEEP,500);
            //Log.d("debug.data", "Beep");



            //_____________________________

        } catch (RuntimeException ignored){
            Log.d("debug.data", "toneGen1 catch_1 !!!");
        }
    }

    public void beepBeep(){
        try{
            final ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
            toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,500);
            //Log.d("debug.data", "Beep Beep");

            Handler handler11 = new Handler();
            handler11.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toneGen1.release();
                    // toneGen1.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,500);

                }
            }, 550);

        } catch (RuntimeException ignored){
            Log.d("debug.data", "toneGen1 catch_2 !!!");
        }
    }

    public Runnable runnableI = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Seconds = Seconds % 60;

            Sec = 14- Seconds;

            MilliSecondsT = (int) (UpdateTime % 1000);

            MilliSecondsR = MilliSecondsT/10;

            if(MilliSecondsR>0) {
                MilliSeconds = 100 - MilliSecondsR;
            }
            else {
                MilliSeconds =00;

            }

            if((Sec==2&&b3||Sec==1&&b2||Sec==0&&b1)){
                //Log.d("debug.data", "Beep true");
                switch (Sec){
                    case 0:
                        b1=false;
                        break;
                    case 1:
                        b2=false;
                        break;
                    case 2:
                        b3=false;
                        break;
                    default:
                            Log.d("debug.data", "Default Called");

                }
                beep();

            }else if((Seconds==15)&&(bf)){
                bf=false;
                //Log.d("debug.data", "Beep Beep true");
                beepBeep();
            }

            if(Sec<0) {
                inspectionTimer_ender();
                handlerI.removeCallbacks(runnableI);
                inspectionTimer.setText("00:00");
            }
            else {
                inspectionTimer.setText("" + String.format("%02d", Sec) + ":" + String.format("%02d", MilliSeconds));
                handlerI.postDelayed(this, 0);
            }



        }

    };

    public Runnable runnableS = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            //MilliSeconds = (int) (UpdateTime % 1000);

            MilliSecondsT = (int) (UpdateTime % 1000);

            MilliSecondsR = MilliSecondsT/10;

            if(Minutes<100){
                solveTimer.setText("" + String.format("%02d",Minutes) + ":" + String.format("%02d", Seconds) + ":" + String.format("%02d", MilliSecondsR));
            }
            else {
                solveTimer.setText("" + Minutes + ":" + String.format("%02d", Seconds) + ":" + String.format("%02d", MilliSecondsR));
            }

            handlerS.postDelayed(this, 0);
        }

    };





}

