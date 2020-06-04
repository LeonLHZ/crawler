package com.lhz.crawler.service;

import com.lhz.crawler.pojo.Item;

import java.util.List;

/**
 * @author Neo
 * @date 2020/6/2 23:31
 */
public interface ItemService
{
    public void save(Item item);

    public List<Item> findAll(Item item);
}
