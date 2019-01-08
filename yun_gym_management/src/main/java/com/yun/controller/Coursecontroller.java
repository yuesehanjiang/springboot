package com.yun.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.constant.ResponseMessage;
import com.yun.pojo.Course;
import com.yun.service.CourseService;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

/**
 * 课程表
 * 
 * @author qzy017
 *
 */
@RestController
public class Coursecontroller {
    @Autowired
    private CourseService courseService;
    
    private final static Logger logger=Logger.getLogger(Coursecontroller.class);

    // 1 课程表的新增
    @PostMapping(value = "/course/add")
    public Map<String, Object> courseadd(@RequestBody String course) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map readValue = objectMapper.readValue(course, Map.class);
            // map 转 json
            String json = readValue.get("content").toString();
            Course course1 = new Course();
            course1.setContent(json);
            course1.setGymAddr(readValue.get("gymAddr").toString());
            courseService.courseadd(course1);
            return ServerResponse.build("200", ResponseMessage.success);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }

    // 2 根据门店查找 课程表 /course/find
    @PostMapping(value = "/course/find")
    public Map<String, Object> coursfind(@RequestBody Course course) {
        try {
            Course courfind = courseService.courfind(course);
            return ServerResponse.build("200", ResponseMessage.success, courfind);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    // 3 根据门店查找 课程表给终端机 /course/find
    @PostMapping(value = "/course/courseToMachine")
    public Map<String, Object> coursfindcourseToMachine(String gymAddr) {
        try {
            if (StringUtils.isHasEmpty(gymAddr)) { 
                return ServerResponse.build("201", ResponseMessage.isNull);
            } 
            Course course = new Course();
            course.setGymAddr(gymAddr);
            Course courfind = courseService.coursfindMachine(course);
            if (courfind == null) {
                return ServerResponse.build("200", ResponseMessage.success, "");
            }
            return ServerResponse.build("200", ResponseMessage.success, courfind);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    
    //4 终端机  根据 周几获取数据 
    @PostMapping(value = "/course/courseToMachineForWeek")
    public Map<String, Object> courseToMachineForWeek(String gymAddr,String week) {
        try {
            Course courfind = courseService.courseToMachineForWeek(gymAddr,week);
            if (courfind == null) {
                return ServerResponse.build("200", ResponseMessage.success, "");
            }
            return ServerResponse.build("200", ResponseMessage.success, courfind);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ServerResponse.build("203", ResponseMessage.exception);
        }
    }
    
    
  
    @GetMapping(value="/hs")
    public String  hs() {
        try {
            int i=10/0;
        } catch (Exception e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
            
        }
        return null;
    }
    

}