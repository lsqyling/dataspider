package com.baustem.dataspider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.model.CharactersMTime;
import com.baustem.dataspider.model.MovieMTime;
import com.baustem.dataspider.model.TVMTime;

@Component
public class DataSpider {
	@Qualifier("TVMTimePipeline")
	@Autowired
	private PageModelPipeline<TVMTime> tvMTimePipeline;
	
	@Qualifier("MovieMTimePipeline")
	@Autowired
	private PageModelPipeline<MovieMTime> movieMTimePipeline;
	
	@Qualifier("CharactersMTimePipeline")
	@Autowired
	private PageModelPipeline<CharactersMTime> peopleMTimePipeline;
	
	public void crawlPeople(){
		OOSpider.create(
				Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
				peopleMTimePipeline, CharactersMTime.class)
				.addUrl("http://movie.mtime.com/people/list/")
				.thread(5)
				.runAsync();
	}
	
	public void crawlTV(){
		OOSpider.create(
				Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
				tvMTimePipeline, TVMTime.class)
				.addUrl("http://movie.mtime.com/tv/list/")
				.thread(5)
				.runAsync();
	}
	
	public void crawlMovie(){
		OOSpider.create(
				Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
				movieMTimePipeline, MovieMTime.class)
				.addUrl("http://movie.mtime.com/all/list/")
				.thread(5)
				.runAsync();
	}
	
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
	public static void main(String[] args) {
		final DataSpider spider = applicationContext.getBean(DataSpider.class);
		spider.crawlTV();
		spider.crawlMovie();
		spider.crawlPeople();
	}
	
	
}
