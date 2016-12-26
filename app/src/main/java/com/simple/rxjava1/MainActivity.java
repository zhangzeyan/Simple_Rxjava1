package com.simple.rxjava1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    private TextView testTv1,testTv2,testTv3,testTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testTv1 = (TextView) findViewById(R.id.testTv1);
        testTv2 = (TextView) findViewById(R.id.testTv2);
        testTv3 = (TextView) findViewById(R.id.testTv3);
        testTv4 = (TextView) findViewById(R.id.testTv4);


        //第一种用法
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Rxjava栗子1");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                testTv1.setText(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("");
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        myObservable.subscribe(mySubscriber);


        //第二种用法
        Observable<String> myObservable1 =
                Observable.just("Rxjava栗子2");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                testTv2.setText(s);
            }
        };
        myObservable1.subscribe(onNextAction);


        //第三种用法
        Action1<String> onNextAction2 = new Action1<String>() {
            @Override
            public void call(String s) {
                testTv3.setText(s);
            }
        };
        Observable.just("Rxjava栗子3")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "_simple";
                    }
                })
                .subscribe(onNextAction2);



        //第四种用法 使用Lambda
        Observable.just("Rxjava栗子4")
                .subscribe(s -> testTv4.setText(s));


    }

}
