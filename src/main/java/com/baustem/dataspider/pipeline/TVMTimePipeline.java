package com.baustem.dataspider.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.dao.TVMTimeDao;
import com.baustem.dataspider.model.TVMTime;
@Component("TVMTimePipeline")
public class TVMTimePipeline implements PageModelPipeline<TVMTime> {
	
	@Resource
	private TVMTimeDao tvDao;

	@Override
	public void process(TVMTime tv, Task task) {
		tvDao.add(tv);
	}

}
