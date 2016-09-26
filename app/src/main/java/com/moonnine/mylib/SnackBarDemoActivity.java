package com.moonnine.mylib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by PenDragon on 2016/8/20.
 */
public class SnackBarDemoActivity extends AppCompatActivity {
    @InjectView(R.id.ll)
    LinearLayout mLl;
    @InjectView(R.id.snackbar_button)
    Button mSnackbarButton;

    private View.OnClickListener mOnClickListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.snackbar_button)
    public void onClick() {
        showSnackbar();
    }

    private void showSnackbar() {
        if (mOnClickListener == null) {
            mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SnackBarDemoActivity.this, "Action被点击了", Toast.LENGTH_SHORT).show();
                }
            };
        }

        // 这是snackbar的容器
        View view = mLl;
        // setaction可以设置右边出现一个点击按钮互动
        // callback取消以后的callback
        Snackbar.Callback callback = new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Toast.makeText(SnackBarDemoActivity.this, "Actionbar出现了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Toast.makeText(SnackBarDemoActivity.this, "Actionbar消失了", Toast.LENGTH_SHORT).show();
            }
        };
        Snackbar.make(view, "弹出来了一个Snackbar", Snackbar.LENGTH_LONG).setAction("Action", mOnClickListener).setCallback
                (callback).show();
    }
}
