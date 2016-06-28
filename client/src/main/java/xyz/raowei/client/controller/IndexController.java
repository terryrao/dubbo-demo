package xyz.raowei.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.raowei.api.service;

import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 * create: 2016-06-22 15:00
 *
 * @author admin
 */
@Controller
public class IndexController {

    @Autowired
    @Qualifier("helloServer")
    private service service;


    @RequestMapping("/hello")
    public String index(HttpServletRequest request,Model model) {
        String name = service.hello("dfdasf");
        System.out.println(name);
        model.addAttribute("name",name);
        return "index";
    }
}

