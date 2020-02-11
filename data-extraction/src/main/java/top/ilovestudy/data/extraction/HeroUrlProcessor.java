package top.ilovestudy.data.extraction;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HeroUrlProcessor implements PageProcessor {
  private Site site = Site.me().setDomain("sgz.ejoy.com");

  public static void main(String[] args) {
    Spider.create(new HeroUrlProcessor())
        .addUrl("https://sgz.ejoy.com/m/station/ajax/350659087930767364/undefined/1")
        .addPipeline(new ConsolePipeline())
        .run();
  }

  @Override
  public void process(Page page) {
    Set<String> urls = new HashSet<>();
    Elements elements = page.getHtml().getDocument().body().getElementsByTag("a");
    for (Element element : elements) {
      String href = element.attr("href");
      urls.add(href);
    }

    List<String> heroUrls = urls.stream().map(o -> "https://sgz.ejoy.com" + o).collect(Collectors.toList());

    page.putField("heroUrls", heroUrls);
  }

  @Override
  public Site getSite() {
    return site;
  }
}
