package com.example.ordersapp;


import com.example.ordersapp.network.Retrolnstance;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class UnitTestMyApp {

    private final CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void loginTest_1() throws InterruptedException {
        Login.logIn(
                "0595619096",
                "yehya123");
        latch.await(5, TimeUnit.SECONDS);
        Assert.assertTrue(Login.getStatus);
    }

    @Test
    public void loginTest_2() throws InterruptedException {
        Login.logIn(
                "0595619096",
                "1");

        latch.await(5, TimeUnit.SECONDS);
        Assert.assertTrue(Login.getStatus);
    }

    @Test
    public void getDataTest_1() throws InterruptedException {
        GetData.getData();

        latch.await(5, TimeUnit.SECONDS);
        Assert.assertTrue(GetData.getStatus);

    }

    @Test
    public void getDataTest_2() throws InterruptedException {
        GetData.getData();

        latch.await(5, TimeUnit.SECONDS);
        Assert.assertTrue(GetData.getStatus);

    }

    @Test
    public void urlTest() {
        Assert.assertNotNull(Retrolnstance.BASE_URL);
    }

}
