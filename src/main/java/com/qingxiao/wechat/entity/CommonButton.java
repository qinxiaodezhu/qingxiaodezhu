package com.qingxiao.wechat.entity;

/**
 * 普通按钮（子按钮）
 * @authot Sjm
 * @date 2019/1/8 18:31
 **/
public class CommonButton extends Button {
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
