package me.maiz.ittrainning.simplecrawlerboot.service.crawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 存储已访问和未访问的链接
 */
public class LinkStore {

    private static  final Logger logger = LoggerFactory.getLogger(LinkStore.class);

    //已访问的链接，set去重
    private Set<String> visitedLinks = new HashSet<>();

    // 未访问的链接，queue保证顺序
    private Queue<String> unvisitLinkQueue = new LinkedList<>();

    public LinkStore(){}

    public LinkStore(String[] seeds){
        for (int i = 0; i < seeds.length; i++) {
            this.addUnvisited(seeds[i]);
        }
    }

    /**
     * 添加未访问的链接到队列中
     * @param unvisitedLink
     */
    public void addUnvisited(String unvisitedLink){

        if(checkUnvisitUrl(unvisitedLink)){
            logger.debug("添加URL到未访问链接队列中{}",unvisitedLink);

            unvisitLinkQueue.add(unvisitedLink);
        }
    }

    private boolean checkUnvisitUrl(String unvisitedLink) {
        if(!(unvisitedLink!=null&&unvisitedLink.trim().length()>0)){
            return false;
        }
        if(unvisitLinkQueue.contains(unvisitedLink)||unvisitLinkQueue.contains(unvisitedLink.concat("/"))){
            logger.info("{}已在未访问队列中，忽略",unvisitedLink);
            return false;
        }
        if(visitedLinks.contains(unvisitedLink)||visitedLinks.contains(unvisitedLink.concat("/"))){
            logger.info("{}已访问，忽略",unvisitedLink);
            return false;
        }
        if(unvisitedLink.contains("?")){
            String url = unvisitedLink.substring(0,unvisitedLink.indexOf("?"));
            if(visitedLinks.contains(url)||unvisitLinkQueue.contains(url)){
                logger.info("{}已存在，忽略",unvisitedLink);
                return false;
            }
        }

        return true;
    }

    /**
     * 添加所有未访问的链接到队列中
     * @param unvisitedLinks
     */
    public void addUnvisitedAll(Set<String> unvisitedLinks){
        logger.info("添加一批URL到未访问链接队列中{}",unvisitedLinks.size());
       for (String u:unvisitedLinks){
           addUnvisited(u);
       }
    }

    /**
     * 添加已访问的链接
     * @param visitedLink
     */
    public void addVisited(String visitedLink){
        visitedLinks.add(visitedLink);
    }

    /**
     * 得到第一个未被访问的URL，并且移除之
     */
    public String pollUnVisited(){
       return  unvisitLinkQueue.poll();
    }

    /**
     * 统计已访问过的链接数量
     * @return
     */
    public int countVisited(){
        return visitedLinks.size();
    }

    /**
     * 未访问队列是否为空
     * @return
     */
    public boolean isUnVisitedUrlQueueEmpty(){
        return unvisitLinkQueue.isEmpty();
    }

}
