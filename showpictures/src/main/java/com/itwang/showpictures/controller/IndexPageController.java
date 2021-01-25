package com.itwang.showpictures.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: showpictures
 * @Package: com.itwang.showpictures.controller
 * @ClassName: IndexController
 * @Author: wangyaqiang
 * @Description:
 * @Date: 2020/12/4 17:35
 * @Version: 1.0
 */
@Controller
public class IndexPageController {
    public static void main(String[] args) {
        long round = Math.round(Math.random() * 4);
        System.out.println(round);
    }
}
