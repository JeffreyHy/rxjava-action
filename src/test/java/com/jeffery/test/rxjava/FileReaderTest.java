package com.jeffery.test.rxjava;

import com.jeffery.rxjava.file.FileReader;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.File;

/**
 * @author JefferyHy
 * @date Created by  2018/1/26 16:03
 */
public class FileReaderTest extends TestCase {
    private final static Logger logger = LoggerFactory.getLogger(FileReaderTest.class);

    @Test
    public void testReadLocalFileName() {
        File folders = new File("E:/Training");
        FileReader.readCurrentDirFileName(folders);
    }

    @Test
    public void testReadAllLocalFileName() {
        File folders = new File("E:/Training");
        FileReader.readCurrentDirAndChildDirFileName(folders);
    }


}
