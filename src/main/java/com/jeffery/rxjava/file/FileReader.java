package com.jeffery.rxjava.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.File;

/**
 * @author JefferyHy
 * @date Created by  2018/1/26 18:02
 */
public final class FileReader {
    private final static Logger logger = LoggerFactory.getLogger(FileReader.class);

    private FileReader() {
    }

    /**
     * 读取指定目录下的所有ppt文件（不包含子目录）
     *
     * @param folders
     */
    public static void readCurrentDirFileName(File folders) {
        Observable.just(folders)
                .filter(file -> file != null && file.isDirectory())
                .flatMap((Func1<File, Observable<File>>) file -> Observable.from(file.listFiles()))
                .filter(file -> file.getName().endsWith(".ppt"))
                .subscribeOn(Schedulers.io())
                .subscribe(file -> logger.info(file.getName()));
    }

    /**
     * 读取指定目录下的所有png文件（包含子目录）
     *
     * @param folders
     */
    public static void readCurrentDirAndChildDirFileName(File folders) {
        if (folders == null || !folders.isDirectory()) {
            return;
        }
        flatAllFile(folders)
                .filter(file -> file.getName().endsWith(".png"))
                .subscribe(file -> logger.info(file.getName()));
    }

    /**
     * 递归读取文件目录
     *
     * @param folders 文件目录
     * @return
     */
    private static Observable<File> flatAllFile(File folders) {
        return Observable.from(folders.listFiles())
                .filter(file -> file != null)
                .flatMap((Func1<File, Observable<File>>) file -> {
                    if (file.isDirectory()) {
                        return flatAllFile(file);
                    } else {
                        return Observable.just(file);
                    }
                });
    }
}
