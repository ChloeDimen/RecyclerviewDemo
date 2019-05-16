package com.jetway.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jetway.recyclerviewdemo.commonAdapter.MulitiActivity;
import com.jetway.recyclerviewdemo.wrap.HeaderFooterActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void btn_base(View view) {
        startActivity(new Intent(this,BaseRecyclerActivity.class));
    }

    public void btn_base_adapter(View view) {
        startActivity(new Intent(this,MulitiActivity.class));
    }

    public void btn_base_strag(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void btn_header_footer(View view) {
        startActivity(new Intent(this,HeaderFooterActivity.class));
    }

    public void btn_refresh(View view) {
        startActivity(new Intent(this,RefreshActivity.class));
    }
}
