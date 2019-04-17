package com.onechat.cat;

import com.danikula.videocache.file.FileNameGenerator;

/**
 * 为缓存的文件提供名称
 */
public class MyFileNameGenerator implements FileNameGenerator {
    @Override
    public String generate(String url) {
        String name = url.substring(url.lastIndexOf("/") + 1, url.length());
        return name;
    }
}