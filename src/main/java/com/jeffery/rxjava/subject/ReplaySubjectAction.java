package com.jeffery.rxjava.subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

/**
 * Subject 使用实战，Subject充当了Observer和Observable的角色。
 * 它是一个Observer，它可以订阅一个或多个Observable；又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据。
 * <p>
 * 如果你把Subject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），
 * 这可能导致同时（非顺序）调用，这会违反Observable协议，给Subject的结果增加了不确定性。
 * <p>
 * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
 * 也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据（原始Observable发射的）。
 * <p>
 *
 * @author huangyongbo
 * @date Created by  2018/1/29 14:30
 */
public final class ReplaySubjectAction {
    private final static Logger logger = LoggerFactory.getLogger(ReplaySubjectAction.class);

    private ReplaySubjectAction() {
    }

    /**
     * ReplaySubject作为发射源
     */
    public static void replaySubjectAction_1() {
        ReplaySubject<String> subject = ReplaySubject.create();
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
    public static void replaySubjectAction_2() throws InterruptedException {
        Observable<String> observable = Observable.create(subscriber -> {
            logger.info("currentThread:{}", Thread.currentThread());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("5");
            subscriber.onNext("6");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("7");
            subscriber.onNext("8");
            subscriber.onCompleted();
        });
        ReplaySubject<String> subject = ReplaySubject.create();
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
