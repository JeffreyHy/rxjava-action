package com.jeffery.rxjava.helloworld;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.util.Arrays;

/**
 * @author huangyongbo
 * @date Created by  2018/1/27 16:16
 */
public final class RxJavaHelloWorld {
    private final static Logger logger = LoggerFactory.getLogger(RxJavaHelloWorld.class);

    private RxJavaHelloWorld() {
    }

    /**
     * create接收Observable.OnSubscribe<String>创建Observable
     */
    public static void helloWorld_1() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("hello world");
            subscriber.onNext("Jeffery");
            subscriber.onCompleted();
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.error("cause:{}", e.getCause());
            }

            @Override
            public void onNext(String s) {
                logger.info(s);
            }
        });
    }

    /**
     * from接收可迭代数据序列自动创建Observable
     */
    public static void helloWorld_2() {
        Observable.from(Arrays.asList("hello world", "Jeffrey")).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logger.info("completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.error("cause:{}", e.getCause());
            }

            @Override
            public void onNext(String s) {
                logger.info(s);
            }
        });
    }

    /**
     * Subscriber接收onStart()事件
     */
    public static void helloWorld_3() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("hello world");
            subscriber.onNext("Jeffery");
            subscriber.onCompleted();
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logger.info("completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.error("cause:{}", e.getCause());
            }

            @Override
            public void onNext(String s) {
                logger.info(s);
            }

            @Override
            public void onStart() {
                logger.info("start");
            }
        });
    }

    /**
     * just接收传入的参数创建Observable
     */
    public static void helloWorld_4() {
        Observable.just("hello world", "Jeffery").subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.error("cause:{}", e.getCause());
            }

            @Override
            public void onNext(String s) {
                logger.info(s);
            }
        });
    }

    /**
     * 接收ActionX形式的函数自动创建Observer
     */
    public static void helloWorld_5() {
        Observable.just("hello world", "Jeffery").subscribe(s -> logger.info(s));
    }
}
