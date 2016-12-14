package com.baustem.dataspider.dao;

import org.apache.ibatis.annotations.Insert;

import com.baustem.dataspider.model.LieTouJobInfo;

public interface JobInfoDao {
	
	@Insert("insert into JobInfo (`title`,`salary`,`company`,`description`,`source`,`url`,`urlMd5`) values (#{title},#{salary},#{company},#{description},#{source},#{url},#{urlMd5})")
	int add(LieTouJobInfo lieTouJobInfo);
	
	

}
