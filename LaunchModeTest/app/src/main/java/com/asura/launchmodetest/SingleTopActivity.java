package com.asura.launchmodetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asura on 2017/7/11 9:34.
 * <p>singleTop</p>
 * <p>2、每调用一次 startActivity ,如果任务栈顶没有实例，就会实例化一个新的 activity 对象；
 * 如果有，则不创建，并回调 onNewIntent(Intent intent) 方法</p>
 */
public class SingleTopActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_main, btn_self;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asura", "SingleTopActivity onCreate " + hashCode() + "  taskId== " + getTaskId());
        setContentView(R.layout.activity_standard);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("SingleTopActivity  " + hashCode());
        }

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_self = (Button) findViewById(R.id.btn_self);

        btn_main.setOnClickListener(this);
        btn_self.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_main:
                intent.setClass(SingleTopActivity.this, MainActivity.class);
                break;
            case R.id.btn_self:
                intent.setClass(SingleTopActivity.this, SingleTopActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("asura", "SingleTopActivity onNewIntent " + hashCode() + "  taskId== " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("asura", "SingleTopActivity onDestroy " + hashCode() + "  taskId== " + getTaskId());
    }
}
