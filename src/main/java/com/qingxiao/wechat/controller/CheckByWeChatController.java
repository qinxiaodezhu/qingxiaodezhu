package com.qingxiao.wechat.controller;

import com.qingxiao.wechat.utility.MessageUtil;
import com.qingxiao.wechat.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 处理微信请求,判断是否是微信请求过来的
 * @author Sjm
 * @date 2019-01-02
 */
@RestController
@RequestMapping(value = "/login")
public class CheckByWeChatController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping
    public String get(@RequestParam(name="signature", required=false) String signature, @RequestParam(name="timestamp", required=false) String timestamp, @RequestParam(name="nonce", required=false) String nonce, @RequestParam(name="echostr", required=false) String echostr){
        this.logger.info("进入系统,开始识别请求是否来自微信认证");
        logger.info("打印请求数据:"+"signature"+signature+"  timestamp"+timestamp+"  nonce"+nonce+"  echostr"+echostr);
        if(Utility.checkSignature(signature, timestamp, nonce)){
            logger.info("请求确认来自微信");
            return echostr;
        }
        logger.info("请求不是来自微信");
        return "错误";
    }
    @PostMapping
    public String post(HttpServletRequest request){
        this.logger.info("进入系统,开始识别请求是否来自微信");
        String respMessage = null;
        String respContent = "对不起请输入正确的语句";
        String fromUserName = "";
        String toUserName = "";
        String msgType = "";
        String mediaId = "";
        try {
            Map<String,String> map = MessageUtil.pareXml(request);
            fromUserName = map.get("FromUserName");
            toUserName = map.get("ToUserName");
            msgType = map.get("MsgType");
            mediaId = map.get("MediaId");
            if("text".equalsIgnoreCase(msgType)){
                String content = map.get("Content");
                logger.info(content);
                if(content.contains("王露馨")){
                    respContent = "那是宋健明的大宝贝!";
                }
                respMessage=("<xml><ToUserName><![CDATA["+fromUserName+"]]>" + "</ToUserName>" +
                        "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>" +
                        "<CreateTime>"+System.currentTimeMillis()+"</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA["+respContent+"]]></Content></xml>");
                logger.info(respMessage);
            }else if("image".equalsIgnoreCase(msgType)){
                respMessage=("<xml><ToUserName><![CDATA["+fromUserName+"]]>" + "</ToUserName>" +
                        "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>" +
                        "<CreateTime>"+System.currentTimeMillis()+"</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image><MediaId><![CDATA["+mediaId+"]]></MediaId></Image></xml>");
                logger.info(respMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
