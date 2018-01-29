package com.jeffery.test.rxjava;

import com.jeffery.rxjava.subject.AsyncSubjectAction;
import com.jeffery.rxjava.subject.BehaviorSubjectAction;
import com.jeffery.rxjava.subject.PublishSubjectAction;
import com.jeffery.rxjava.subject.ReplaySubjectAction;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author huangyongbo
 * @date Created by  2018/1/28 18:23
 */
public class RxJavaSubjectActionTest extends TestCase {
    @Test
    public void testAsyncSubjectAction_1() {
        AsyncSubjectAction.asyncSubjectAction_1();
    }

    @Test
    public void testAsyncSubjectAction_2() {
        AsyncSubjectAction.asyncSubjectAction_2();
    }

    @Test
    public void testBehaviorSubjectAction_1() {
        BehaviorSubjectAction.behaviorSubjectAction_1();
    }

    @Test
    public void testBehaviorSubjectAction_2() throws InterruptedException {
        BehaviorSubjectAction.behaviorSubjectAction_2();
    }

    @Test
    public void testPublishSubjectAction_1() {
        PublishSubjectAction.publishSubjectAction_1();
    }

    @Test
    public void testPublishSubjectAction_2() throws InterruptedException {
        PublishSubjectAction.publishSubjectAction_2();
    }
    @Test
    public void testReplaySubjectAction_1(){
        ReplaySubjectAction.replaySubjectAction_1();
    }

    @Test
    public void testReplaySubjectAction_2() throws InterruptedException {
        ReplaySubjectAction.replaySubjectAction_2();
    }

    @Test
    public void test() {
        System.out.print(BigDecimal.valueOf(7)
                .divide(BigDecimal.valueOf(145), 4, BigDecimal.ROUND_HALF_UP));
    }
}
