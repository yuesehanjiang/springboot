package com.yun;

import java.util.HashMap;

import org.junit.Test;
import com.yun.utils.OkHttpUtil;

public class YunGymManagementApplicationTests {
    
   
    @Test
    public void contextLoads() {
        String url = "http://47.107.56.215:8080/statistic/statisticSportsDatas";
        HashMap<String, String> params = new HashMap<>();
        params.put("flag", "kcal");
        params.put("gymAddr", "00000142");
        params.put("limit", "10");
        params.put("page", "1");   
        params.put("dateFlag","week");
        params.put("sportsType", "111");
        System.out.println(params.toString());
        String result = OkHttpUtil.post(url, params);
        System.out.println(result);
    }
    
    
    @Test
    public void testTrain(){
        String url = "http://47.107.56.215:8080/history/trainingRecordDatas";
        HashMap<String, String> params = new HashMap<>();
        params.put("openId", "161534776152213&oTEZDxDGAbWm7KadMt9ome9dsTgM");
        params.put("date", "2018-08-21");
        String result = OkHttpUtil.post(url, params);
        System.out.println(result);
    }
    
    
    
    
    

}
