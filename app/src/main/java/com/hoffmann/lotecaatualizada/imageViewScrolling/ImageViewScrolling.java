package com.hoffmann.lotecaatualizada.imageViewScrolling;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.hoffmann.lotecaatualizada.R;
import com.hoffmann.lotecaatualizada.utilitario.Constantes;

public class ImageViewScrolling extends FrameLayout {

    ImageView currentImage, nextImage;
    int lastResult = 0, oldValue = 0;

    IEventEnd eventEnd;

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this);
        currentImage = findViewById(R.id.current_image);
        nextImage = findViewById(R.id.next_image);

        nextImage.setTranslationY(getHeight());
    }

    public void setValueRandom(int image, int rotateCount) {
        int animationDuration = 130;
        currentImage.animate().translationY(-getHeight()).setDuration(animationDuration).start();
        nextImage.setTranslationY(nextImage.getHeight());
        nextImage.animate().translationY(0)
                .setDuration(animationDuration)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        setImage(currentImage, oldValue % 6);
                        currentImage.setTranslationY(0);
                        if (oldValue != rotateCount) {
                            setValueRandom(image, rotateCount);
                            oldValue++;
                        } else {
                            lastResult = 0;
                            oldValue = 0;
                            setImage(nextImage, image);
                            eventEnd.eventEnd(image % 6, rotateCount);
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

    private void setImage(ImageView currentImage, int value) {
        if (value == Constantes.BAR)
            currentImage.setImageResource(R.drawable.bar_done);
        else if (value == Constantes.SEVEN)
            currentImage.setImageResource(R.drawable.sevent_done);
        else if (value == Constantes.ORANGE)
            currentImage.setImageResource(R.drawable.orange_done);
        else if (value == Constantes.LEMON)
            currentImage.setImageResource(R.drawable.lemon_done);
        else if (value == Constantes.TRIPLE)
            currentImage.setImageResource(R.drawable.triple_done);
        else
            currentImage.setImageResource(R.drawable.waternelon_done);

        currentImage.setTag(value);
        oldValue = value;
    }

    public int getValue() {
        return Integer.parseInt(nextImage.getTag().toString());
    }
}
