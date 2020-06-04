package com.lhz.crawler.task;

import com.lhz.crawler.pojo.Item;
import com.lhz.crawler.service.ItemService;
import com.lhz.crawler.utils.SeleniumUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Neo
 * @date 2020/6/3 23:04
 */
@Component
public class ItemTask
{
    @Autowired
    private ItemService itemService;

    private WebDriver webDriver =
            SeleniumUtils.goToHtml("https://search.jd.com/Search?keyword=狗&qrst=1&wq=狗&stock=1&s=50&click=0&page=1");

    public ItemTask()
    {
    }

    @Scheduled(fixedDelay = 100 * 1000)
    public void itemTask() throws Exception
    {
       webDriver.findElement(By.className("pn-next")).click();
        this.parse(webDriver.getPageSource());
    }

    private void parse(String html)
    {
        Document doc = Jsoup.parse(html);

        Elements elements = doc.select("div#J_goodsList >ul > li");
        for (Element element : elements)
        {
            String sku = element.attr("data-sku");
            Item item = new Item();
            item.setSku(Long.parseLong(sku));

            List<Item> all = itemService.findAll(item);
            if (all.size() > 0)
                continue;
           // item.setSpu(Long.parseLong(sku));
            item.setPic(element.select("img").first().attr("src"));
            item.setTitle(element.select(".p-name em").first().text());
            item.setCreated(new Date());
            item.setUrl("https://item.jd.com/" + sku + ".html");
            item.setPrice(Double.parseDouble(element.select(".p-price i").first().text()));


            item.setUpdated(new Date());

            itemService.save(item);
        }

    }
}
