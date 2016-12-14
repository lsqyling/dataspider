package com.baustem.dataspider.dao;

import org.apache.ibatis.annotations.Insert;

import com.baustem.dataspider.model.TVMTime;

public interface TVMTimeDao {
	@Insert("INSERT INTO TVData(`name`,birthDay,`type`,posterUrl,director,scriptwriter,producer,region,issueCompany,story,actor,url) "
			+ "VALUES(#{name},#{birthDay},#{type},#{posterUrl},#{director},#{scriptwriter},#{producer},#{region},#{issueCompany},#{story},#{actor},#{url})")
	int add(TVMTime tv);

}
