package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author Neo
 * @date 2020/5/18 22:41
 */
public class JsoupFirstTest
{
    public JsoupFirstTest()
    {
    }

    @Test
    public void testUrl() throws Exception
    {
       //解析url地址,第一个参数是访问的url第二个是访问超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        //使用标签选择器,获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testString()throws Exception{
        //使用工具类读取文件获取字符串
        String content = FileUtils.readFileToString(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");

        //解析字符串
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
         System.out.println(title);
    }

    @Test
    public void testFile()throws Exception{
        //解析文件
        Document doc = Jsoup.parse(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");
        String title = doc.getElementsByTag("title").first().text();
                System.out.println(title);
    }

    @Test
    public void testDom()throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");
        //根据id获取元素
        Element a = doc.getElementById("a");
        //根据标签获取元素
        Element span = doc.getElementsByTag("span").first();

        //根据class获取
        Element class_a_class_b = doc.getElementsByClass("class_a class_b").first();

        //根据属性获取元素
        Element abc = doc.getElementsByAttribute("abc").first();
        //通过属性加属性值获取
        Element elementsByAttributeValue = doc.getElementsByAttributeValue("href", "wqewq").first();


    }


    @Test
    public void testData()throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");
        Element element = doc.getElementById("test");
        String str = "";
        //获取元素id
        element.id();
        //获取元素class
        element.className();
        Set<String> strings = element.classNames();
        strings.stream().forEach(System.out::print);

        //根据属性的名字获取属性的值
        element.attr("class");

        //获取所有属性
        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

        //获取文本内容
        element.text();
    }

    @Test
    public  void testSelect()throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");

        //通过标签查找元素
        Elements span = doc.select("span");
        span.stream().map(element -> element.text()).forEach(System.out::print);

        //根据id查找元素
        Element select = doc.select("#id").first();

        //根据class获取
        Element first = doc.select(".class").first();

        //根据属性获取元素
        Element first1 = doc.select("[abc]").first();

        //根据属性值查找元素
        Element first2 = doc.select("[class=abc]").first();


    }


    @Test
    public void testSelector()throws Exception{
        Document doc = Jsoup.parse(new File("C:\\Users\\lenovo\\Desktop\\myshop 同城生活网\\dengl.html"), "utf-8");

        //el#id: 元素+id  比如:h3#city_bj
        Element element = doc.select("h3#city_bj").first();

        //el.class: 元素+class 比如：li.class_a
        Element element1 = doc.select("li.class_a").first();

        //el[attr]:元素+属性名  比如：span[abc]
        Element element2 = doc.select("span[abc]").first();

        //任意组合: 比如span[abc].s_
        // name
        Element element3 = doc.select("span[abc].s_name").first();

        //ancestor child :查找某个元素下子元素，比如: .city_con li查找class为city_con下的所有li
        Element element4 = doc.select(".city_con li").first();

        //parent>child:查找某个父元素下的直接子元素  比如：.city_con>ul>li查找city_con的ul直接子元素，在查找ul的直接子元素li
        Element element5 = doc.select(".city_con>ul>li").first();

        //parent>*查找父元素下的所有直接子元素
        Element element6 = doc.select(".city_con>ul>*").first();
    }
}
