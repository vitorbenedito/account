package br.com.account.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class BaseActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetector;

    private Class nextActivity = MainActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }
    
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }
    
    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //TouchEvent dispatcher.
        if (gestureDetector != null && nextActivity != null) {

            startActivity(new Intent(this, nextActivity));
            return true;

        }
        return super.dispatchTouchEvent(ev);
    }

    protected void setNextActivity(Class nextActivity) {
        this.nextActivity = nextActivity;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    
}