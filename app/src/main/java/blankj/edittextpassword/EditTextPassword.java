package blankj.edittextpassword;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by cmj on 2016/4/25.
 */
public class EditTextPassword extends EditText
        implements View.OnTouchListener {

    public interface DrawableRightListener {
        void onDrawableRightClick(View view);
    }

    private DrawableRightListener mRightListener;

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    private Drawable drawableRight;

    final int DRAWABLE_RIGHT = 2;

    public EditTextPassword(Context context) {
        super(context);
        init();
    }

    public EditTextPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
        this.setOnTouchListener(this);
    }

    //这里一定要清楚onTouch发生在onTouchEvent事件之前，并且return false才会去执行onTouchEvent的相关事件
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (drawableRight != null && mRightListener != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            //判断点击是否落在rightDrawable中
            if (x > getWidth() - getTotalPaddingRight() && x < getWidth() && y > 0 && y < getHeight()) {
                //获取点击之前光标的位置
                int pos = getSelectionStart();
                //设置回调
                mRightListener.onDrawableRightClick(this);
                //恢复点击之前光标的位置
                this.setSelection(pos);
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawableRight != null && mRightListener != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            //判断点击是否落在rightDrawable中
            if (x > getWidth() - getTotalPaddingRight() && x < getWidth() && y > 0 && y < getHeight()) {
                return false;
            }
        }
        return super.onTouchEvent(event);
    }
}
