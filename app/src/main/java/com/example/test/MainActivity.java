package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.alibaba.android.arouter.launcher.ARouter;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ScopeProvider;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author Zhi Qiang
 * @Email 1745984453@qq.com
 * @Date ${DATE} ${TIME}
 * @Version 1.0
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView rec;
    Button viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Test);
//        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec = findViewById(R.id.rec);
        viewById = findViewById(R.id.btn);
        rec.setLayoutManager(new LinearLayoutManager(this));

        String product = null;
        try {
            product = (String) getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.get("Product");
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewById.setText(product);

        //获取数据
        getData();

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/test/activity","aa").navigation();
//                ARouter.getInstance().build("/aa/MainActivity2")
//                        .withString("key1","a")
//                        .navigation();
            }
        });
    }

    private void getData() {
        new Retrofit.Builder().baseUrl("https://api.isoyu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .connectTimeout(10,TimeUnit.SECONDS)
                        .build())
                .build()
                .create(Api.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Observer<MyFood>() {
                    @Override
                    public void onSubscribe( Disposable d) {

                    }

                    @Override
                    public void onNext(MyFood foods) {
                        Log.d("123", "onNext: "  + foods.toString());
                        MyAdapter myAdapter = new MyAdapter(foods.getData());
                        rec.setAdapter(myAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("123", "onNext: "  + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}