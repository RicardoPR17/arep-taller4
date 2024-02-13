package com.example.taller4.myspark;

import com.example.taller4.myspark.anotations.Component;
import com.example.taller4.myspark.anotations.GetMapping;

@Component
public class HelloController {

    @GetMapping("/hello")
    public static String index() {
        return "Hello anotations";
    }

    @GetMapping("/helloname")
    public static String helloService(String name) {
        return "Hello " + name;
    }

    @GetMapping("/square")
    public static Double square(String val) {
        return Double.valueOf(val) * Double.valueOf(val);
    }
}
