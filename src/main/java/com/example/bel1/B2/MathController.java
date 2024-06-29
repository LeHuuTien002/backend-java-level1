package com.example.bel1.B2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
    @GetMapping("/add")
    public double add(@RequestParam double num1, @RequestParam double num2){
        return num1+num2;
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double num1, @RequestParam double num2){
        return num1-num2;
    }

    @GetMapping("/mutiply")
    public double mutiply(@RequestParam double num1, @RequestParam double num2){
        return num1*num2;
    }

    @GetMapping("/devide")
    public String devide(@RequestParam double num1, @RequestParam double num2){
        if (num2 == 0){
            return "Canot divide by zero";
        }else {
            return (num1/num2) + "";
        }
    }
}
