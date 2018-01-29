package com.jeffery.rxjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;


/**
 * @author huangyongbo
 * @date Created by  2018/1/27 22:10
 */
public final class RxJavaLift {
    private final static Logger logger = LoggerFactory.getLogger(RxJavaLift.class);

    private RxJavaLift() {
    }

    public static void integerLiftString(Integer i) {
        Observable.just(i).lift((Observable.Operator<String, Integer>) subscriber -> new Subscriber<Integer>() {
            @Override
            public void onStart() {
                logger.info("lift Start");
                subscriber.onStart();
            }
            @Override
            public void onCompleted() {
                logger.info("lift Completed");
                subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                logger.info("lift Error:{}", e.getCause());
                subscriber.onError(e);
            }

            @Override
            public void onNext(Integer integer) {
                logger.info("lift type:{}", integer.getClass().getName());
                subscriber.onNext(String.valueOf(integer));
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("Error");
            }

            @Override
            public void onNext(String s) {
                logger.info("type:{},value:{}", s.getClass().getName(), s);
            }
        });
    }


}
