package com.jeffery.rxjava.subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Subject 使用实战，Subject充当了Observer和Observable的角色。
 * 它是一个Observer，它可以订阅一个或多个Observable；又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据。
 * <p>
 * 如果你把Subject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），
 * 这可能导致同时（非顺序）调用，这会违反Observable协议，给Subject的结果增加了不确定性。
 * <p>
 * BehaviorSubject开始发射来自原始Observable的数据前，首先发射的最后一个数据（如果此时还没有收到任何数据，它会发射一个默认值）
 * 然后继续发射任何来自原始Observable的数据。
 * <p>
 * 原始Observable完成之后，后续订阅者不会收到数据
 *
 * @author huangyongbo
 * @date Created by  2018/1/28 18:47
 */
public final class BehaviorSubjectAction {
    private final static Logger logger = LoggerFactory.getLogger(BehaviorSubjectAction.class);

    private BehaviorSubjectAction() {
    }

    /**
     * BehaviorSubject自己充当发射源
     */
    public static void behaviorSubjectAction_1() {
        BehaviorSubject<String> subject = BehaviorSubject.create("no data");
        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer1 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer1 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer1:{}", o);
            }
        };
        subject.subscribe(observer1);
        subject.onNext("1");
        subject.onNext("2");
        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer2 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer2 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer2:{}", o);
            }
        };
        subject.subscribe(observer2);
        subject.onNext("3");
        subject.onNext("4");
        subject.onCompleted();
        Observer<String> observer3 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer3 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer3 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer3:{}", o);
            }
        };
        subject.subscribe(observer3);
    }

    /**
     * 转发来自Observable的数据
     */
    public static void behaviorSubjectAction_2() throws InterruptedException {
        Observable<String> observable = Observable.create(subscriber -> {
            logger.info("currentThread:{}", Thread.currentThread());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("1");
            subscriber.onNext("2");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("3");
            subscriber.onNext("4");
            subscriber.onCompleted();
        });
        BehaviorSubject<String> subject = BehaviorSubject.create("no data");
        observable.subscribeOn(Schedulers.newThread()).subscribe(subject);
        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer1 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer1 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer1:{}", o);
            }
        };
        subject.subscribe(observer1);
        logger.info("observer1 currentThread:{}", Thread.currentThread());
        Thread.sleep(20);
        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer2 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer2 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer2:{}", o);
            }
        };
        subject.subscribe(observer2);
        Thread.sleep(20);
        Observer<String> observer3 = new Observer<String>() {
            @Override
            public void onCompleted() {
                logger.info("observer3 Completed");
            }

            @Override
            public void onError(Throwable e) {
                logger.info("observer3 Error");
            }

            @Override
            public void onNext(String o) {
                logger.info("observer3:{}", o);
            }
        };
        subject.subscribe(observer3);
    }
}
