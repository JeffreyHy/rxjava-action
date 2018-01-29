package com.jeffery.test.rxjava;

import com.jeffery.rxjava.RxJavaLift;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author huangyongbo
 * @date Created by  2018/1/27 22:20
 */
public class RxJavaLiftTest extends TestCase {
    @Test
    public void testIntegerLiftString() {
        RxJavaLift.integerLiftString(111);
    }
}
