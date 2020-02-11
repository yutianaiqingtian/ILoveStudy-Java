package top.ilovestudy.data.extraction;

import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class KingSanHeroProcessor implements PageProcessor {
  private Site site = Site.me().setDomain("sgz.ejoy.com");

  public static void main(String[] args) throws IOException {
    List<String> urls = FileUtils.readLines(new File("data-extraction/src/main/resources/heroUrls"));
    Spider spider = Spider.create(new KingSanHeroProcessor());
    for (String url : urls.subList(0, 3)) {
      spider.addUrl(url);
    }
    spider.addPipeline(new ConsolePipeline())
        .run();
  }

  @Override
  public void process(Page page) {
    // List<String> links = page.getHtml().links().regex("https://sgz\\.ejoy\\.com/m/station/detail\\-\\d+").all();
    // page.addTargetRequests(links);

    page.putField("name", page.getHtml().xpath("/html/body/div[2]/div[1]/div[1]/text()").toString());
    page.putField("wl", page.getHtml().xpath("//*[@id='wl']/@value").toString());
    page.putField("ml", page.getHtml().xpath("//*[@id='ml']/@value").toString());
    page.putField("zz", page.getHtml().xpath("//*[@id='zz']/@value").toString());
    page.putField("sd", page.getHtml().xpath("//*[@id='sd']/@value").toString());
    page.putField("ts", page.getHtml().xpath("//*[@id='ts']/@value").toString());
    page.putField("zl", page.getHtml().xpath("//*[@id='zl']/@value").toString());
    page.putField("骑兵", page.getHtml().xpath("/html/body/div[2]/div[2]/div[1]/ul/li[1]/div/text()").toString());
    page.putField("盾兵", page.getHtml().xpath("/html/body/div[2]/div[2]/div[1]/ul/li[2]/div/text()").toString());
    page.putField("弓兵", page.getHtml().xpath("/html/body/div[2]/div[2]/div[1]/ul/li[3]/div/text()").toString());
    page.putField("枪兵", page.getHtml().xpath("/html/body/div[2]/div[2]/div[1]/ul/li[4]/div/text()").toString());
    page.putField("器械", page.getHtml().xpath("/html/body/div[2]/div[2]/div[1]/ul/li[5]/div/text()").toString());
    page.putField("自带战法名称", page.getHtml().xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[1]/text()").toString());
    page.putField("自带战法内容", page.getHtml().xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/text()").toString());

    page.putField("拆解战法名称", page.getHtml().xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[1]/text()").toString());
    page.putField("拆解战法内容", page.getHtml().xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[2]/text()").toString());
    page.putField("列传", page.getHtml().xpath("/html/body/div[2]/div[2]/div[4]/div[2]/text()").toString());
  }

  @Override
  public Site getSite() {
    return site;
  }
}
