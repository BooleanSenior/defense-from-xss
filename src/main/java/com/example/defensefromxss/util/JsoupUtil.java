package com.example.defensefromxss.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupUtil {
    /**
     * 配置白名单为基本使用的标签再加上img标签
     */
    private static final Whitelist WHITELIST = Whitelist.basicWithImages();

    /**
     * 配置过滤的参数，不对代码格式化
     */
    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);

    static {
        //富文本编辑时一些样式是使用style来进行实现的 比如红色字体 style="color:red;" 所以需要给所有标签添加style属性
        WHITELIST.addAttributes(":all","style");
    }

    public static String clean(String content){
        return Jsoup.clean(content,"",WHITELIST,OUTPUT_SETTINGS);
    }
}