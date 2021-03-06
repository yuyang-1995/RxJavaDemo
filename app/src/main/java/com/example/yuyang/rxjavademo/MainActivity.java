package com.example.yuyang.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Rxjava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //分布方法实现RxJava
        m1();



    }

    private void m1() {

        // 步骤1：创建被观察者 Observable & 生产事件
        // 即 顾客入饭店 - 坐下餐桌 - 点菜

        //  1. 创建被观察者 Observable 对象
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            // 2. 在复写的subscribe（）里定义需要发送的事件
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                // 通过 ObservableEmitter类对象产生事件并通知观察者
//                // ObservableEmitter类介绍
//                // a. 定义：事件发射器
//                // b. 作用：定义需要发送的事件 & 向观察者发送事件
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        });

        //RxJava 提供了另一种方法用于创建被观察者对象。如下

        Observable<Integer> observable = Observable.just(1,2,3);
       // 将会依次调用：
        // onNext(1);
        // onNext(2);
        // onNext(3);
        // onCompleted();


        // 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 即 开厨房 - 确定对应菜式
        Observer<Integer> observer = new Observer<Integer>() {
            // 通过复写对应方法来 响应 被观察者
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };


        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        // 即 顾客找到服务员 - 点菜 - 服务员下单到厨房 - 厨房烹调
        observable.subscribe(observer);
    }
}
