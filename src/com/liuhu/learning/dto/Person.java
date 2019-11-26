package com.liuhu.learning.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuhu-jk
 * @Date 2019/11/9 15:44
 * @Description
 **/
public class Person {
    private String name;

    private int id;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","2");
        map.put("2","2");map.put("6","2");
        map.put("3","2");
        map.put("4","2");map.put("7","2");
        map.put("5","2");
        map.put("9","2");map.put("8","2");
        map.put("10","2");
        map.put("11","2");
        map.put("12","2");
        map.put("13","2");






    }
}
