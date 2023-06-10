package com.hoffmann.lotecaatualizada.imageViewScrolling;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.utilitario.Constantes;
import com.hoffmann.lotecaatualizada.utilitario.Utils;

public class ImageViewScrolling extends FrameLayout {

    private static int ANIMATION_DUR = 150;
    ImageView current_image, next_image;
    int last_result = 0, old_result = 0;

    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this);
        current_image = (ImageView)getRootView().findViewById(R.id.current_image);
        next_image = (ImageView)getRootView().findViewById(R.id.next_image);

        next_image.setTranslationY(getHeight());
    }

    public void setValueRandom(int image, int rotate_count){
        current_image.animate().translationY(-getHeight()).setDuration(ANIMATION_DUR).start();
        next_image.setTranslationY(next_image.getHeight());
        next_image.animate().translationY(0)
                .setDuration(ANIMATION_DUR)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        setImage(current_image, old_result%6);
                        current_image.setTranslationY(0);
                        if(old_result != rotate_count){
                            setValueRandom(image, rotate_count);
                            old_result++;
                        } else {
                            last_result = 0;
                            old_result = 0;
                            setImage(next_image, image);
                            eventEnd.eventEnd(image%6, rotate_count);
                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
    }

    private void setImage(ImageView current_image, int value) {
        if (value == Constantes.BAR) {
            current_image.setImageResource(R.drawable.bar_done);
        }
        if (value == Constantes.SEVEN) {
            current_image.setImageResource(R.drawable.sevent_done);
        }
        if (value == Constantes.ORANGE) {
            current_image.setImageResource(R.drawable.orange_done);
        }
        if (value == Constantes.LEMON) {
            current_image.setImageResource(R.drawable.lemon_done);
        }
        if (value == Constantes.TRIPLE) {
            current_image.setImageResource(R.drawable.triple_done);
        }
        if (value == Constantes.WATERMELON) {
            current_image.setImageResource(R.drawable.waternelon_done);
        }
        current_image.setTag(value);
        old_result = value;
    }

    public int getValue(){
        return Integer.parseInt(next_image.getTag().toString());
    }
}
