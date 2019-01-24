package com.qingxiao.wechat.entity;

/**
 * 复杂按钮（父按钮）
 * @authot Sjm
 * @date 2019/1/8 18:32
 **/
public class ComplexButton extends Button{
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
