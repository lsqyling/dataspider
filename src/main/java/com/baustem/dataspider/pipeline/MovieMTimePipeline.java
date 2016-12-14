package com.baustem.dataspider.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.dao.MovieMTimeDao;
import com.baustem.dataspider.model.MovieMTime;
@Component("MovieMTimePipeline")
public class MovieMTimePipeline implements PageModelPipeline<MovieMTime> {
	
	@Resource
	private MovieMTimeDao movieDao;

	@Override
	public void process(MovieMTime movie, Task task) {
		movieDao.add(movie);
	}

}
