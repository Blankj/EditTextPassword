package blankj.edittextpassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;

public class MainActivity extends AppCompatActivity
implements EditTextPassword.DrawableRightListener{


    private EditTextPassword inputETP;
    private boolean mIsShow = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputETP = (EditTextPassword) findViewById(R.id.etp_input);
        //设置输入为密码模式
        inputETP.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        inputETP.setDrawableRightListener(this);
    }

    //同样地可以在这可以实现其他的效果，比如一键清空
    @Override
    public void onDrawableRightClick() {
        if (mIsShow) {
            inputETP.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_grey, 0) ;
            inputETP.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            inputETP.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_orange, 0) ;
            inputETP.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        mIsShow = !mIsShow ;
    }
}
