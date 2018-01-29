package com.jeffery.rxjava.subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Subject 使用实战，Subject充当了Observer和Observable的角色。
 * 它是一个Observer，它可以订阅一个或多个Observable；又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据。
 * <p>
 * 如果你把Subject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），
 * 这可能导致同时（非顺序）调用，这会违反Observable协议，给Subject的结果增加了不确定性。
 * <p>
 * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
 * 需要注意的是，PublishSubject可能会一创建完成就立刻开始发射数据（除非你可以阻止它发生），
 * 因此这里有一个风险：在Subject被创建后到有观察者订阅它之前这个时间段内，一个或多个数据可能会丢失。
 * 如果要确保来自原始Observable的所有数据都被分发，你需要这样做：
 * 或者使用Create创建那个Observable以便手动给它引入”冷”Observable的行为（当所有观察者都已经订阅时才开始发射数据），
 * 或者改用ReplaySubject。
 *
 * @author huangyongbo
 * @date Created by  2018/1/28 19:34
 */
public final class PublishSubjectAction {
    private final static Logger logger = LoggerFactory.getLogger(BehaviorSubjectAction.class);

    private PublishSubjectAction() {
    }

    public static void publishSubjectAction_1() {
        PublishSubject<String> subject = PublishSubject.create();
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
     *
     * @throws InterruptedException
     */
    public static void publishSubjectAction_2() throws InterruptedException {
        Observable<String> observable = Observable.create(subscriber -> {
            logger.info("currentThread:{}", Thread.currentThread());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("3");
            subscriber.onNext("4");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("5");
            subscriber.onNext("6");
            subscriber.onCompleted();
        });
        PublishSubject<String> subject = PublishSubject.create();
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
