package top.ilovestudy.data.extraction.model;

import lombok.Data;
import org.apache.commons.io.FileUtils;
import top.ilovestudy.data.extraction.CSVFilePageModelPipeline;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author jinghui.zhang
 */
@Data
public class Hero {
  @ExtractBy(value = "/html/body/div[2]/div[1]/div[1]/text()", notNull = true)
  private String name;

  @ExtractBy(value = "//*[@id='wl']/@value", notNull = true)
  private String wl;

  @ExtractBy(value = "//*[@id='ml']/@value", notNull = true)
  private String ml;

  @ExtractBy(value = "//*[@id='zz']/@value", notNull = true)
  private String zz;

  @ExtractBy(value = "//*[@id='sd']/@value", notNull = true)
  private String sd;

  @ExtractBy(value = "//*[@id='ts']/@value", notNull = true)
  private String ts;

  @ExtractBy(value = "//*[@id='zl']/@value", notNull = true)
  private String zl;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[1]/ul/li[1]/div/text()", notNull = true)
  private String qiBing;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[1]/ul/li[2]/div/text()", notNull = true)
  private String duBing;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[1]/ul/li[3]/div/text()", notNull = true)
  private String gongBing;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[1]/ul/li[4]/div/text()", notNull = true)
  private String qiangBing;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[1]/ul/li[5]/div/text()", notNull = true)
  private String qiXie;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[2]/div[2]/div[1]/text()", notNull = true)
  private String selfMagicName;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/text()", notNull = true)
  private String selfMagicValue;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[3]/div[2]/div[1]/text()", notNull = true)
  private String otherMagicName;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[3]/div[2]/div[2]/text()", notNull = true)
  private String otherMagicValue;

  @ExtractBy(value = "/html/body/div[2]/div[2]/div[4]/div[2]/text()", notNull = true)
  private String lieZhuan;

  public static void main(String[] args) throws IOException {
    List<String> urls = FileUtils.readLines(new File("data-extraction/src/main/resources/heroUrls"));
    Spider spider = OOSpider.create(Site.me().setSleepTime(1000), new CSVFilePageModelPipeline(), Hero.class)
        .addUrl("https://sgz.ejoy.com/m/station/detail-350659087930767364-376405201398020100");
    for (String url : urls) {
      spider.addUrl(url);
    }
    spider.thread(1).run();
  }
}
