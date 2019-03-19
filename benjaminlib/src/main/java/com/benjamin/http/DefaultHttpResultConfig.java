package com.benjamin.http;

/**
 * @author Ben
 * @date 2019/1/2
 */
public class DefaultHttpResultConfig implements IHttpResultConfig {

    @Override
    public String getCodeName() {
        return "resultCode";
    }

    @Override
    public String getMessageName() {
        return "resultMsg";
    }

    @Override
    public String getDataName() {
        return "data";
    }

    @Override
    public int getSuccessCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "DefaultHttpResultConfig{" +
                "codeName='" + getCodeName() + '\'' +
                ", messageName='" + getMessageName() + '\'' +
                ", dataName='" + getDataName() + '\'' +
                ", successCode=" + getSuccessCode() +
                '}';
    }
}
