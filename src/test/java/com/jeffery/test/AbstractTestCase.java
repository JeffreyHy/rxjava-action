package com.jeffery.test;

import com.jeffery.context.Application;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huangyongbo
 * @date Created by  2018/1/26 16:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@SpringBootConfiguration
public class AbstractTestCase extends TestCase {
    protected static Logger logger = LoggerFactory.getLogger(AbstractTestCase.class);

}
