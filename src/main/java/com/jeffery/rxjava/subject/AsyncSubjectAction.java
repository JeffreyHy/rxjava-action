package com.jeffery.rxjava.subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.subjects.AsyncSubject;

/**
 * Subject 使用实战，Subject充当了Observer和Observable的角色。
 * 它是一个Observer，它可以订阅一个或多个Observable；又因为它是一个Observable，它可以转发它收到(Observe)的数据，也可以发射新的数据。
 * <p>
 * 如果你把Subject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），
 * 这可能导致同时（非顺序）调用，这会违反Observable协议，给Subject的结果增加了不确定性。
 * <p>
 * AsyncSubject只有在原始Observable完成之后（onCompleted被调用），发射来自原始Observable的最后一个值。
 * 它会把这最后一个值发射给任何后续的观察者。（如果原始Observable没有发射任何值，AsyncObject也不发射任何值）
 * <p>
 * 如果原始的Observable因为发生了错误而终止，AsyncSubject将不会发射任何数据，只是简单的向前传递这个错误通知。
 * <p>
 * 注意：AsyncSubject自己也是Observable，其本身也可作为发射源
 *
 * @author huangyongbo
 * @date Created by  2018/1/28 18:13
 */
public final class AsyncSubjectAction {
    private final static Logger logger = LoggerFactory.getLogger(AsyncSubjectAction.class);

    private AsyncSubjectAction() {
    }

    /**
     * 自己作为发射源
     */
    public static void asyncSubjectAction_1() {
        AsyncSubject<String> subject = AsyncSubject.create();
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
        subject.subscribe(observer1);
        subject.subscribe(observer2);
        logger.info("emit start");
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("...");
        subject.onNext("over");
        logger.info("emit finish");
        subject.onCompleted();
        subject.subscribe(observer3);
    }

    /**
     * 转发来自Observable的数据
     */
    public static void asyncSubjectAction_2() {
        Observable observable = Observable.just("1", "2", "3");
        AsyncSubject<String> subject = AsyncSubject.create();
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
        subject.subscribe(observer1);
        subject.subscribe(observer2);
        observable.subscribe(subject);
        subject.subscribe(observer3);
    }


}
