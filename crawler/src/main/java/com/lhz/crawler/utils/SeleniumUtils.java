package com.lhz.crawler.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Neo
 * @date 2020/6/4 21:44
 */
public class SeleniumUtils
{

    private static WebDriver driver;
    private static String path = "F:\\crawler\\crawler";
    private static boolean ifonce = true;

    public SeleniumUtils()
    {
    }

    public static WebDriver getDriver()
    {
        if (StringUtils.isEmpty(path))
              {
                  System.out.println("没有找到Quark");
                  return null;
              }

        System.setProperty("webdriver.chrome.driver", path + File.separator + "chromedriver.exe");
              driver = new ChromeDriver();
              driver.manage().window().maximize();
              driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }

    public static WebDriver goToHtml(String url){
        Optional.ofNullable(driver).orElse(getDriver()).get(url);
        return driver;
    }
}
