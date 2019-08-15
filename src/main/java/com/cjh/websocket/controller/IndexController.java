package com.cjh.websocket.controller;

import com.cjh.websocket.service.TestTS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chen jia hao
 */
@Controller
public class IndexController {
    @Resource
    private TestTS testTS;

    @RequestMapping(value = "toIndex")
    public ModelAndView toIndex(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "test")
    public void test(HttpServletRequest request, HttpServletResponse response){

        System.out.println("kkk.....");
        testTS.send();
        try {
            response.getWriter().print("ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
