package com.bootdo.gi.service;

import com.bootdo.gi.service.impl.HelloWord;
import com.bootdo.gi.service.impl.HelloWorld;

/**
 * Created by Administrator on 2019/7/29.
 */
public class WebServiceTest {

    public static void main(String[] args) {

        HelloWord helloWord = new HelloWorld().getHelloWordImplPort();

        String doit = helloWord.doit(23);

        System.out.println("调用了doit方法："+doit);

        String sayHi = helloWord.sayHi("调用了sayHi");

        System.out.println(sayHi);

    }
}
