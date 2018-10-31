package me.maiz.trainningproject.core;

import me.maiz.trainningproject.model.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * DOM解析器
 */
public class Parser {

    /* 通过选择器来选取页面的 */
    private static Elements select(Page page , String cssSelector) {
        return page.getDoc().select(cssSelector);
    }

    /**
     * 得到链接
     * @param page
     * @param cssSelector
     * @return
     */
    Set<String> getLinks(Page page,String cssSelector){
        Set<String> links  = new HashSet<String>() ;
        Elements es = select(page , cssSelector);
        Iterator iterator  = es.iterator();
        while(iterator.hasNext()) {
            Element element = (Element) iterator.next();
            if ( element.hasAttr("href") ) {
                links.add(element.attr("abs:href"));
            }else if( element.hasAttr("src") ){
                links.add(element.attr("abs:src"));
            }
        }
        return links;
    }

    /**
     * 解析内容
     * @param page 指定的页面
     * @param cssSelector 指定的css选择器
     * @param index 对象索引
     * @return
     */
    Element extractContent(Page page, String cssSelector,int index){
        return null;
    }

}
