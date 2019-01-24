package com.qingxiao.wechat.controller;

import com.qingxiao.wechat.MenuManager;
import com.qingxiao.wechat.entity.AccessToken;
import com.qingxiao.wechat.utility.MenuUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authot Sjm
 * @date 2019/1/8 19:09
 **/
@RestController
public class CreatController {
    @RequestMapping("/creatmenu")
    public String createMenu() {
        AccessToken token = MenuUtil.getAccessToken(MenuUtil.appId,MenuUtil.appSecret);
        if(token==null){
            return "失败";
        }else{
            int menu = MenuUtil.createMenu(MenuManager.getMenu(),token.getToken());
            if(menu==0){
                return "success";
            }else{
                return "false";
            }
        }
    }
}
