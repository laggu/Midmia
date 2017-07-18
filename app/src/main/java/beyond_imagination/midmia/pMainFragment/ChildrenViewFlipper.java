package beyond_imagination.midmia.pMainFragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import beyond_imagination.midmia.Child;
import beyond_imagination.midmia.MainActivity;
import beyond_imagination.midmia.R;

/**
 * Created by laggu on 2017-07-17.
 */

public class ChildrenViewFlipper extends ViewFlipper {
    MainActivity activity;
    private ArrayList<Child> children;
    Animation slide_in_left, slide_out_right, slide_in_right, slide_out_left;
    float downX;
    int currentIndex;

    public ChildrenViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (MainActivity)context;

        init();
        setSlide();
    }

    private void init(){
        children = activity.getChildren();

        for(int i = 0 ; i < children.size(); ++i){
            ChildImageView image = new ChildImageView(getContext(),children.get(i).getPhoto());
            addView(image);
        }
    }

    private void setSlide(){
        slide_in_left = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right);
        slide_in_right = AnimationUtils.loadAnimation(activity, R.anim.slide_in_right);
        slide_out_left = AnimationUtils.loadAnimation(activity, R.anim.slide_out_left);

        setOnTouchListener(MyTouchListener);
    }

    private void MoveNextView()
    {
        setInAnimation(slide_in_right);
        setOutAnimation(slide_out_left);
        showNext();
    }

    private void MovewPreviousView()
    {
        setInAnimation(slide_in_left);
        setOutAnimation(slide_out_right);
        showPrevious();
    }

    View.OnTouchListener MyTouchListener = new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    downX = (int)event.getX();
                }

                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    int upX = (int)event.getX();

                    if (upX < downX)
                    {
                        MoveNextView();
                    }
                    else if (upX > downX)
                    {
                        MovewPreviousView();
                    }

                    downX = upX;
                }

                return true;
            }
        };
}
