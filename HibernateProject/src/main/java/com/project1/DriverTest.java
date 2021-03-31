package com.project1;

import com.project1.dao.UserDao;

public class DriverTest {
    public static void main(String[] args) {
        System.out.println("Hello World");

        UserDao dao = new UserDao();
        System.out.println(dao.getList().toString());

    }
}
