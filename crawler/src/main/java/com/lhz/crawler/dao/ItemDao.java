package com.lhz.crawler.dao;

import com.lhz.crawler.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Neo
 * @date 2020/6/2 23:28
 */
public interface ItemDao extends JpaRepository<Item,Long>

{

}
