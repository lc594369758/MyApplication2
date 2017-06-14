package com.example.a59436.myapplication;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //被观察者
       Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>(){
           @Override
           public void call(Subscriber<? super String> subscriber) {
               subscriber.onNext("33");
               //onCompleted方法执行后，不会执行也不能执行onNext方法，表示队列事件完结
               //在一个正确运行的事件序列中, onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。
               //需要注意的是，onCompleted() 和 onError() 二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。
               subscriber.onCompleted();
           }
       });

        //使用from传递数组或 Iterable 拆分成具体对象后，依次发送出来。
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable1=Observable.from(words);

        //快速创建传递事件
//       Observable observable1=Observable.just("111","22");

        //观察者
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("test","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("test",e.toString());
            }

            @Override
            public void onNext(String s) {
               Log.i("test","onCompleted"+"onNext"+s);
            }
        };

        //在 RxJava 的 subscribe 过程中，Observer 也总是会先被转换成一个 Subscriber 再使用。
        // 所以如果你只想使用基本功能，选择 Observer 和 Subscriber 是完全一样的。
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("test","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("test","onCompleted"+"onNext"+s);
            }

            @Override
            public void onStart() {
                super.onStart();
                //这个是subscriber新增的方法
            }
        };








        //订阅事件
//        observable1.subscribe(subscriber);
//        observable.subscribe(observer);

        //这里其实就是将上面的被观察者里面的数据进行展示
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d("test", s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d("test", "completed");
            }
        };


        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);


    }
}
