package com.qingxiao.wechat.utility;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  Sjm
 * @date 2019/1/3 15:20
 **/
public class MessageUtil {
    private static  final String testTYPE = "TEXT";
    public static Map<String,String> pareXml(HttpServletRequest request) throws Exception{
        Map<String,String> reqMap = new HashMap<String,String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for(Element elem:elementList){
            reqMap.put(elem.getName(),elem.getText());
        }
        inputStream.close();
        return reqMap;
    }
}
