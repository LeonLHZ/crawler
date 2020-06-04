package com.lhz.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Neo
 * @date 2020/6/2 22:22
 */
@SpringBootApplication
@EnableScheduling
public class CrawlerApplication
{
    public CrawlerApplication()
    {
    }

    public static void main(String[] args)
    {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
