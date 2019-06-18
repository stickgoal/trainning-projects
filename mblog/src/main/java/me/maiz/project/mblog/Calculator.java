package me.maiz.project.mblog;

public class Calculator {

    public double sum(double a,double b){
        if(a<0||b<0){
            throw new IllegalArgumentException("参数错误");
        }
        return a+b;
    }
}
