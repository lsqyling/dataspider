package com.baustem.dataspider.pipeline;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.baustem.dataspider.dao.JobInfoDao;
import com.baustem.dataspider.model.LieTouJobInfo;

@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo>{
	
	@Resource
	private JobInfoDao jobInfoDao;

	@Override
	public void process(LieTouJobInfo lieTouJobInfo, Task task) {
		jobInfoDao.add(lieTouJobInfo);
	}

}
