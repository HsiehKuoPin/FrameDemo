package com.kuopin.frame.net;

import com.benjamin.http.RetrofitManager;

/**
 * @author Ben
 * @date 2019/3/19
 */
public class NetProvider {

    public static APIService getInstance() {
        return RetrofitManager.create(APIService.class);
    }

    public static APIService getInstance(String baseUrl) {
        return RetrofitManager.create(APIService.class, baseUrl);
    }
}
