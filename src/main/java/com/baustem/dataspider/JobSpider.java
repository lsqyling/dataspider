package com.baustem.dataspider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.model.LieTouJobInfo;

@Component
public class JobSpider {
	@Qualifier("JobInfoDaoPipeline")
	@Autowired
	private PageModelPipeline<LieTouJobInfo> jobInfoDaoPipeline;
	
	public void crawl(){
		OOSpider.create(
				Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36"),
				jobInfoDaoPipeline, LieTouJobInfo.class)
				.addUrl("https://www.liepin.com/sojob/?dqs=020&curPage=0")
				.thread(5)
				.run();
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext*.xml");
		final JobSpider spider = applicationContext.getBean(JobSpider.class);
		spider.crawl();
		
	}
	
	
	

}
