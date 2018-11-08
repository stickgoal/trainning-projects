package me.maiz.ittrainning.simplecrawlerboot.service.crawler;

import me.maiz.ittrainning.simplecrawlerboot.domain.Page;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * DOM解析器
 */
@Component("parser")
public class Parser {

    private Logger logger = LoggerFactory.getLogger(Parser.class);

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
    public Set<String> getLinks(Page page,String cssSelector){
        Set<String> links  = new HashSet<String>() ;
        Elements es = select(page , cssSelector);
        Iterator iterator  = es.iterator();
        while(iterator.hasNext()) {
            Element element = (Element) iterator.next();
            if ( element.hasAttr("href") ) {
                //"abs:"前缀表示的获取绝对路径
                links.add(element.attr("abs:href"));
            }else if( element.hasAttr("src") ){
                links.add(element.attr("abs:src"));
            }
        }
        return links;
    }


    /**
     * 得到需要的DOM元素
     * @param page 指定的页面
     * @param cssSelector 指定的css选择器,可以有多个
     * @return
     */
    public Elements getTargetElements(Page page, String... cssSelector){
        logger.info("获取内容，{}", Arrays.toString(cssSelector));
        Elements elements = new Elements();
        for (String selector : cssSelector) {
            Elements singleResult = select(page,selector);
            if(singleResult!=null&&singleResult.size()>0){
                elements.addAll(singleResult);
            }
        }
        return elements;
    }

}
