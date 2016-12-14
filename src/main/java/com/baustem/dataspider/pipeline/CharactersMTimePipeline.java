package com.baustem.dataspider.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.dao.CharactersMTimeDao;
import com.baustem.dataspider.model.CharactersMTime;

@Component("CharactersMTimePipeline")
public class CharactersMTimePipeline implements PageModelPipeline<CharactersMTime>{

	@Resource
	private CharactersMTimeDao charactersMTimeDao;
	
	
	@Override
	public void process(CharactersMTime t, Task task) {
		charactersMTimeDao.add(t);
	}

}
