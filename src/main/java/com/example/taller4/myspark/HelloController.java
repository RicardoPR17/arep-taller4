package com.example.taller4.myspark;

import com.example.taller4.myspark.annotations.Component;
import com.example.taller4.myspark.annotations.GetMapping;

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
    public static String square(String val) {
        int number = Integer.parseInt(val);
        return String.valueOf(number * number);
    }

    @GetMapping("/floor")
    public static String floor(String number) {
        double num = Math.floor(Double.parseDouble(number));
        return String.valueOf(num);
    }
}
