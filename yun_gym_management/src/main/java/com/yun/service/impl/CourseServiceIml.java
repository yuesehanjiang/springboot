package com.yun.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.mapper.CourseMapper;
import com.yun.pojo.Course;
import com.yun.service.CourseService;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class CourseServiceIml implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    // 新增课程
    @Override
    public void courseadd(Course course) {
        // 删除以改俱乐部的 数据
        courseMapper.delCourseByGymArr(course);
        courseMapper.courseadd(course);

    }

    // 根据门店找课程表
    @Override
    public Course courfind(Course course) {
        return courseMapper.courfind(course);
    }
		
    //给中积极 找全部数据
    @Override
    public Course coursfindMachine(Course course) {
        Course course1 = courseMapper.coursfindMachine(course);
        if(null==course1) {
        	return null;
        }

        String content = course1.getContent();

        ObjectMapper objectMapper = new ObjectMapper();

        LinkedHashMap con = null;
        LinkedHashMap tue = null;
        LinkedHashMap wed = null;
        LinkedHashMap thur = null;
        LinkedHashMap fri = null;
        LinkedHashMap sat = null;
        LinkedHashMap sun = null;
        try {
            con = objectMapper.readValue(content, LinkedHashMap.class);
            String mon = con.get("mon").toString();
            String yi = mon.substring(1, mon.length() - 1);

            List<String> strings = StringUtils.stringtoArr(yi);
            for (int i = strings.size() - 1; i >= 0; i--) {
                String str = strings.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings.remove(i);
                }
            }

            // 周二
            String tue1 = con.get("tue").toString();
            String er = tue1.substring(1, tue1.length() - 1);

            List<String> strings1 = StringUtils.stringtoArr(er);
            for (int i = strings1.size() - 1; i >= 0; i--) {
                String str = strings1.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings1.remove(i);
                }
            }

            // 周三
            String wed1 = con.get("wed").toString();
            String san = wed1.substring(1, wed1.length() - 1);

            List<String> strings2 = StringUtils.stringtoArr(san);
            for (int i = strings2.size() - 1; i >= 0; i--) {
                String str = strings2.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings2.remove(i);
                }
            }

            // 周四
            String thur1 = con.get("thur").toString();
            String si = thur1.substring(1, thur1.length() - 1);

            List<String> strings3 = StringUtils.stringtoArr(si);
            for (int i = strings3.size() - 1; i >= 0; i--) {
                String str = strings3.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings3.remove(i);
                }
            }
            // 周五

            String fri1 = con.get("fri").toString();
            String wu = fri1.substring(1, fri1.length() - 1);

            List<String> strings4 = StringUtils.stringtoArr(wu);
            for (int i = strings4.size() - 1; i >= 0; i--) {
                String str = strings4.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings4.remove(i);
                }
            }

            // 周六

            String sat1 = con.get("sat").toString();
            String liu = sat1.substring(1, sat1.length() - 1);

            List<String> strings5 = StringUtils.stringtoArr(liu);
            for (int i = strings5.size() - 1; i >= 0; i--) {
                String str = strings5.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings5.remove(i);
                }
            }
            // 周七
            String sun1 = con.get("sun").toString();
            String qi = sun1.substring(1, sun1.length() - 1);

            List<String> strings6 = StringUtils.stringtoArr(qi);
            for (int i = strings6.size() - 1; i >= 0; i--) {
                String str = strings6.get(i);
                if (str.trim().equalsIgnoreCase("null")) {
                    strings6.remove(i);
                }
            }
            // map 转string
            String cont = objectMapper.writeValueAsString(con).toString();
            // 周一 在解析
            List<List<LinkedHashMap>> listyi = new ArrayList<>();
            List<LinkedHashMap> listHashyi = new ArrayList<>();
            LinkedHashMap map1 = new LinkedHashMap();
            for (int i = 0; i < strings.size(); i++) {
                String str = strings.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map1.put(split[0].trim(), split[1]);
                if ((i + 1) % 4 == 0) {
                    listHashyi.add(map1);
                    map1 = new LinkedHashMap();
                    listyi.add(listHashyi);
                    // listHashyi=new ArrayList<>();
                    continue;
                }
            }
            // 周二在解析
            List<List<LinkedHashMap>> lister = new ArrayList<>();
            List<LinkedHashMap> listHasher = new ArrayList<>();
            LinkedHashMap map2 = new LinkedHashMap();
            for (int i = 0; i < strings1.size(); i++) {

                String str = strings1.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map2.put(split[0].trim(), split[1]);
                if ((i + 1) % 4 == 0) {
                    listHasher.add(map2);
                    map2 = new LinkedHashMap();
                    lister.add(listHasher);
                    // listHasher=new ArrayList<>();
                    continue;
                }
            }
            // 周三在解析
            List<List<LinkedHashMap>> listsan = new ArrayList<>();
            List<LinkedHashMap> listHashsan = new ArrayList<>();
            LinkedHashMap map3 = new LinkedHashMap();
            for (int i = 0; i < strings2.size(); i++) {

                String str = strings2.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map3.put(split[0].trim(), split[1]);
                if ((i + 1) % 4 == 0) {
                    listHashsan.add(map3);
                    map3 = new LinkedHashMap();
                    lister.add(listHasher);
                    // listHashsan=new ArrayList<>();
                    continue;
                }
            }
            // 周四在解析
            List<List<LinkedHashMap>> listsi = new ArrayList<>();
            List<LinkedHashMap> listHashsi = new ArrayList<>();
            LinkedHashMap map4 = new LinkedHashMap();
            for (int i = 0; i < strings3.size(); i++) {
                String str = strings3.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map4.put(split[0].trim(), split[1]);

                if ((i + 1) % 4 == 0) {
                    listHashsi.add(map4);
                    map4 = new LinkedHashMap();
                    listsi.add(listHashsi);
                    // listHashsi=new ArrayList<>();
                    continue;
                }
            }
            // 周五在解析
            List<List<LinkedHashMap>> listwu = new ArrayList<>();
            List<LinkedHashMap> listHashwu = new ArrayList<>();
            LinkedHashMap map5 = new LinkedHashMap();
            for (int i = 0; i < strings4.size(); i++) {
                String str = strings4.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map5.put(split[0].trim(), split[1]);

                if ((i + 1) % 4 == 0) {
                    listHashwu.add(map5);
                    map5 = new LinkedHashMap();
                    listwu.add(listHashwu);
                    // listHashwu=new ArrayList<>();
                    continue;
                }
            }

            // 周六在解析
            LinkedHashMap map6 = new LinkedHashMap();
            List<List<LinkedHashMap>> listliu = new ArrayList<>();
            List<LinkedHashMap> listHashliu = new ArrayList<>();
            for (int i = 0; i < strings5.size(); i++) {

                String str = strings5.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map6.put(split[0].trim(), split[1]);
                if ((i + 1) % 4 == 0) {
                    listHashliu.add(map6);
                    map6 = new LinkedHashMap();
                    listliu.add(listHashliu);
                    // listHashliu=new ArrayList<>();
                    continue;
                }
            }

            // 周七在解析
            List<List<LinkedHashMap>> listqi = new ArrayList<>();
            List<LinkedHashMap> listHashqi = new ArrayList<>();
            LinkedHashMap map7 = new LinkedHashMap();
            for (int i = 0; i < strings6.size(); i++) {

                String str = strings6.get(i);
                if (str.contains("{")) {
                    str = str.substring(str.indexOf("{") + 1);
                }
                if (str.contains("}")) {
                    str = str.substring(0, str.length() - 1);
                }
                String[] split = str.split("=");
                if(null !=split && split.length >1)
                map7.put(split[0].trim(), split[1]);

                if ((i + 1) % 4 == 0) {
                    listHashqi.add(map7);
                    map7 = new LinkedHashMap();
                    listqi.add(listHashqi);
                    // listHashqi=new ArrayList<>();
                    continue;
                }
            }
            con.put("mon", listHashyi);
            con.put("tue", listHasher);
            con.put("wed", listHashsan);
            con.put("thur", listHashsi);
            con.put("fri", listHashwu);
            con.put("sat", listHashliu);
            con.put("sun", listHashqi);
            course1.setContent(null);
            course1.setContentMap(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course1;
    }

    
    
    //根据周几来拿数据
	@Override
	public Course courseToMachineForWeek(String gymAddr, String week) {
		Course course=new Course();
		course.setGymAddr(gymAddr);
		 Course course1 = courseMapper.coursfindMachine(course);
	        if(null==course1) {
	        	return null;
	        }
	        String content = course1.getContent();
	        ObjectMapper objectMapper = new ObjectMapper();
	        LinkedHashMap con = null;
	        LinkedHashMap tue = null;
	        LinkedHashMap wed = null;
	        LinkedHashMap thur = null;
	        LinkedHashMap fri = null;
	        LinkedHashMap sat = null;
	        LinkedHashMap sun = null;
	        try {
	            con = objectMapper.readValue(content, LinkedHashMap.class);
	            String mon = con.get("mon").toString();
	            String yi = mon.substring(1, mon.length() - 1);

	            List<String> strings = StringUtils.stringtoArr(yi);
	            for (int i = strings.size() - 1; i >= 0; i--) {
	                String str = strings.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings.remove(i);
	                }
	            }

	            // 周二
	            String tue1 = con.get("tue").toString();
	            String er = tue1.substring(1, tue1.length() - 1);

	            List<String> strings1 = StringUtils.stringtoArr(er);
	            for (int i = strings1.size() - 1; i >= 0; i--) {
	                String str = strings1.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings1.remove(i);
	                }
	            }

	            // 周三
	            String wed1 = con.get("wed").toString();
	            String san = wed1.substring(1, wed1.length() - 1);

	            List<String> strings2 = StringUtils.stringtoArr(san);
	            for (int i = strings2.size() - 1; i >= 0; i--) {
	                String str = strings2.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings2.remove(i);
	                }
	            }

	            // 周四
	            String thur1 = con.get("thur").toString();
	            String si = thur1.substring(1, thur1.length() - 1);

	            List<String> strings3 = StringUtils.stringtoArr(si);
	            for (int i = strings3.size() - 1; i >= 0; i--) {
	                String str = strings3.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings3.remove(i);
	                }
	            }
	            // 周五

	            String fri1 = con.get("fri").toString();
	            String wu = fri1.substring(1, fri1.length() - 1);

	            List<String> strings4 = StringUtils.stringtoArr(wu);
	            for (int i = strings4.size() - 1; i >= 0; i--) {
	                String str = strings4.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings4.remove(i);
	                }
	            }

	            // 周六

	            String sat1 = con.get("sat").toString();
	            String liu = sat1.substring(1, sat1.length() - 1);

	            List<String> strings5 = StringUtils.stringtoArr(liu);
	            for (int i = strings5.size() - 1; i >= 0; i--) {
	                String str = strings5.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings5.remove(i);
	                }
	            }
	            // 周七
	            String sun1 = con.get("sun").toString();
	            String qi = sun1.substring(1, sun1.length() - 1);

	            List<String> strings6 = StringUtils.stringtoArr(qi);
	            for (int i = strings6.size() - 1; i >= 0; i--) {
	                String str = strings6.get(i);
	                if (str.trim().equalsIgnoreCase("null")) {
	                    strings6.remove(i);
	                }
	            }
	            // map 转string
	            String cont = objectMapper.writeValueAsString(con).toString();
	            // 周一 在解析
	            List<List<LinkedHashMap>> listyi = new ArrayList<>();
	            List<LinkedHashMap> listHashyi = new ArrayList<>();
	            LinkedHashMap map1 = new LinkedHashMap();
	            for (int i = 0; i < strings.size(); i++) {
	                String str = strings.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	              if(null !=split && split.length >1)
	                map1.put(split[0].trim(), split[1]);
	                if ((i + 1) % 4 == 0) {
	                    listHashyi.add(map1);
	                    map1 = new LinkedHashMap();
	                    listyi.add(listHashyi);
	                    // listHashyi=new ArrayList<>();
	                    continue;
	                }
	            }
	            // 周二在解析
	            List<List<LinkedHashMap>> lister = new ArrayList<>();
	            List<LinkedHashMap> listHasher = new ArrayList<>();
	            LinkedHashMap map2 = new LinkedHashMap();
	            for (int i = 0; i < strings1.size(); i++) {

	                String str = strings1.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map2.put(split[0].trim(), split[1]);
	                if ((i + 1) % 4 == 0) {
	                    listHasher.add(map2);
	                    map2 = new LinkedHashMap();
	                    lister.add(listHasher);
	                    // listHasher=new ArrayList<>();
	                    continue;
	                }
	            }
	            // 周三在解析
	            List<List<LinkedHashMap>> listsan = new ArrayList<>();
	            List<LinkedHashMap> listHashsan = new ArrayList<>();
	            LinkedHashMap map3 = new LinkedHashMap();
	            for (int i = 0; i < strings2.size(); i++) {

	                String str = strings2.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map3.put(split[0].trim(), split[1]);
	                if ((i + 1) % 4 == 0) {
	                    listHashsan.add(map3);
	                    map3 = new LinkedHashMap();
	                    lister.add(listHasher);
	                    // listHashsan=new ArrayList<>();
	                    continue;
	                }
	            }
	            // 周四在解析
	            List<List<LinkedHashMap>> listsi = new ArrayList<>();
	            List<LinkedHashMap> listHashsi = new ArrayList<>();
	            LinkedHashMap map4 = new LinkedHashMap();
	            for (int i = 0; i < strings3.size(); i++) {
	                String str = strings3.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map4.put(split[0].trim(), split[1]);

	                if ((i + 1) % 4 == 0) {
	                    listHashsi.add(map4);
	                    map4 = new LinkedHashMap();
	                    listsi.add(listHashsi);
	                    // listHashsi=new ArrayList<>();
	                    continue;
	                }
	            }
	            // 周五在解析
	            List<List<LinkedHashMap>> listwu = new ArrayList<>();
	            List<LinkedHashMap> listHashwu = new ArrayList<>();
	            LinkedHashMap map5 = new LinkedHashMap();
	            for (int i = 0; i < strings4.size(); i++) {
	                String str = strings4.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map5.put(split[0].trim(), split[1]);

	                if ((i + 1) % 4 == 0) {
	                    listHashwu.add(map5);
	                    map5 = new LinkedHashMap();
	                    listwu.add(listHashwu);
	                    // listHashwu=new ArrayList<>();
	                    continue;
	                }
	            }

	            // 周六在解析
	            LinkedHashMap map6 = new LinkedHashMap();
	            List<List<LinkedHashMap>> listliu = new ArrayList<>();
	            List<LinkedHashMap> listHashliu = new ArrayList<>();
	            for (int i = 0; i < strings5.size(); i++) {

	                String str = strings5.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map6.put(split[0].trim(), split[1]);
	                if ((i + 1) % 4 == 0) {
	                    listHashliu.add(map6);
	                    map6 = new LinkedHashMap();
	                    listliu.add(listHashliu);
	                    // listHashliu=new ArrayList<>();
	                    continue;
	                }
	            }

	            // 周七在解析
	            List<List<LinkedHashMap>> listqi = new ArrayList<>();
	            List<LinkedHashMap> listHashqi = new ArrayList<>();
	            LinkedHashMap map7 = new LinkedHashMap();
	            for (int i = 0; i < strings6.size(); i++) {

	                String str = strings6.get(i);
	                if (str.contains("{")) {
	                    str = str.substring(str.indexOf("{") + 1);
	                }
	                if (str.contains("}")) {
	                    str = str.substring(0, str.length() - 1);
	                }
	                String[] split = str.split("=");
	                if(null !=split && split.length >1)
	                map7.put(split[0].trim(), split[1]);

	                if ((i + 1) % 4 == 0) {
	                    listHashqi.add(map7);
	                    map7 = new LinkedHashMap();
	                    listqi.add(listHashqi);
	                    // listHashqi=new ArrayList<>();
	                    continue;
	                }
	            }
	            con=new LinkedHashMap<>();
	            if(week.trim().equalsIgnoreCase("mon"))
	            con.put("mon", listHashyi);
	            
	            if(week.trim().equalsIgnoreCase("tue"))
	            con.put("tue", listHasher);
	            
	            if(week.trim().equalsIgnoreCase("wed"))
	            con.put("wed", listHashsan);
	            
	            if(week.trim().equalsIgnoreCase("thur"))
	            con.put("thur", listHashsi);
	            
	            if(week.trim().equalsIgnoreCase("fri"))
	            con.put("fri", listHashwu);
	            
	            if(week.trim().equalsIgnoreCase("sat"))
	            con.put("sat", listHashliu);
	            
	            if(week.trim().equalsIgnoreCase("sun"))
	            con.put("sun", listHashqi);
	            
	            course1.setContent(null);
	            course1.setContentMap(con);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return course1;
	}
}
