package com.lhz.crawler.controller;

import com.lhz.crawler.pojo.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Neo
 * @date 2020/6/4 20:40
 */


@RestController
public class OrderController
{
    private WebDriver driver;
    private static String path = "F:\\crawler\\crawler";
    private boolean ifonce = true;

    @GetMapping("/order")
    public String openAndLogin() throws InterruptedException
    {
        //找到本地ie驱动
        if (StringUtils.isEmpty(path))
        {
            System.out.println("没有找到Quark");
            return null;
        }
        //设置驱动位置属性
//        System.setProperty("webdriver.ie.driver", path + File.separator + "Quark.exe");
//        driver = new InternetExplorerDriver();

        System.setProperty("webdriver.chrome.driver", path + File.separator + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver == null)
        {
            return null;
        }
        //TODO 模拟手点
        driver.get(
                "https://search.jd.com/Search?keyword=%E7%8B%97%E7%8B%97&enc=utf-8&pvid=d6c92584af1f47658df90c95f740d5b9");

        //TODO 窗口切换
//        if(ifonce) {
//            System.out.println("URL1：" + driver.getCurrentUrl());
//            String url1=driver.getCurrentUrl();
//            Set winHandels = driver.getWindowHandles();
//            List it = new ArrayList(winHandels);
//            driver.switchTo().window((String) it.get(0));
//            Thread.sleep(1000);
//            if(driver.getCurrentUrl().equals(url1)){
//                driver.switchTo().window((String) it.get(1));
//            }
//            System.out.println("URL2：" + driver.getCurrentUrl());
//            ifonce=false;
//        }
        //Thread.sleep(500);
        //找到购买标签，点击
        //driver.findElement(By.className("goods-buy-price")).click();
        ;
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements elements = doc.select("div#J_goodsList >ul > li");
        for (Element element : elements)
        {
            String sku = element.attr("data-sku");
            Item item = new Item();

            item.setSku(Long.parseLong(sku));
            item.setPic(element.select("img").first().attr("src"));
            item.setTitle(element.select(".p-name em").first().text());
            item.setCreated(new Date());
            item.setUrl("https://item.jd.com/" + sku + ".html");
            item.setPrice(Double.parseDouble(element.select(".p-price i").first().text()));


            item.setUpdated(new Date());


        }
        driver.findElement(By.className("pn-next")).click();
     /*   Thread.sleep(1000);
        //选择商品属性，点击
        List<WebElement> list = driver.findElements(By.className("sku-spec-value"));
        for (WebElement element : list) {
            Thread.sleep(500);
            element.click();
        }
        Thread.sleep(1000);

        //点击购买，点击
        driver.findElement(By.className("sku-selector-bottom")).click();
        Thread.sleep(1000);
        //得到购买链接
        String url = driver.getCurrentUrl();
       */
        return "";
    }
}

