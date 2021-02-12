package com.poltest.poltest.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class WebController {

    @Controller
    public class HomeController {

        @RequestMapping(value = "/")
        public String index() {
            return "index";
        }

    }
}
