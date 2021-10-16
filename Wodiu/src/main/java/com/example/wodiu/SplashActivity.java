package com.example.wodiu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout container;
    private boolean canJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.Theme_Test);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        container = (RelativeLayout) findViewById(R.id.container);
        //运行时权限处理
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,1);
        }else{
            requestAds();
        }
    }
    /*
     *
     * 请求开屏广告
     * */
    private void requestAds() {
        //SplashAD(Activity activity, String posId, SplashADListener adListener)
        new SplashAD(this, "4022332567942883", new SplashADListener() {

            @Override
            public void onADDismissed() {
                //广告显示完毕
                forward();
            }

            @Override
            public void onNoAD(AdError adError) {
                //广告加载失败
                forward();
            }

            @Override
            public void onADPresent() {
                //广告加载成功
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onADClicked() {
                //广告被点击
                Toast.makeText(SplashActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onADTick(long l) {

            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADLoaded(long l) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(canJump) {
            forward();
        }
        canJump = true;
    }

    private void forward() {
        if (canJump){
            //跳转到MainActivity
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            canJump = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for(int result:grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestAds();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
}