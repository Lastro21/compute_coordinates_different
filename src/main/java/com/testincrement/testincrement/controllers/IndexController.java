package com.testincrement.testincrement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
final public class IndexController {


    @RequestMapping(value = "/")

    public String getDataFromInput() {

        return "index";
    }

}
