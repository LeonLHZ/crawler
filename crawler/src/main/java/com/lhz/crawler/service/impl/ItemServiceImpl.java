package com.lhz.crawler.service.impl;

import com.lhz.crawler.dao.ItemDao;
import com.lhz.crawler.pojo.Item;
import com.lhz.crawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neo
 * @date 2020/6/2 23:33
 */
@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private ItemDao itemDao;

    public ItemServiceImpl()
    {
    }

    @Override
    public void save(Item item)
    {
        this.itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item)
    {
        Example<Item> example = Example.of(item);
       return this.itemDao.findAll(example);
    }
}
