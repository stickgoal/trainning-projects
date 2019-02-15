package me.maiz.project.mblog.component;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaiduAIClient {

    private static  final Logger logger = LoggerFactory.getLogger(BaiduAIClient.class);

    //设置APPID/AK/SK
    public static final String APP_ID = "15565341";
    public static final String API_KEY = "dp2Qu9L9q45Ofc9Z3l38RexT";
    public static final String SECRET_KEY = "VWz1jXYk9F3ngUFHy4crRfIlbwCnCPnr";

    // 初始化一个AipImageClassifyClient
    private static final AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
    //置信度阈值，阈值以下不采用
    private static final double KEYWORD_SCORE_THRESHOLD = 0.2;


    public static JSONObject detectImage(String path){
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        HashMap<String,String> options = new HashMap<>();
        final JSONObject result = client.advancedGeneral(path, options);
        logger.info("Baidu AI 响应结果为：{}",result);
        return result;
    }

    public static List<String> getTagsOfImage(String path){
        List<String> tags = new ArrayList<>();
        JSONObject jo = detectImage(path);
        final JSONArray result = jo.getJSONArray("result");

        for(int i=0;i<result.length();i++){
            JSONObject resultItem = result.getJSONObject(i);
            double score = resultItem.getDouble("score");

            if(score> KEYWORD_SCORE_THRESHOLD) {
                tags.add(resultItem.getString("keyword"));
            }
        }
        return tags;
    }

}
