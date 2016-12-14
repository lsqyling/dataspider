package com.baustem.dataspider.dao;

import org.apache.ibatis.annotations.Insert;

import com.baustem.dataspider.model.MovieMTime;

public interface MovieMTimeDao {
	@Insert("INSERT INTO MovieData(`name`,birthDay,`type`,posterUrl,director,scriptwriter,producer,region,issueCompany,story,actor,url) "
			+ "VALUES(#{name},#{birthDay},#{type},#{posterUrl},#{director},#{scriptwriter},#{producer},#{region},#{issueCompany},#{story},#{actor},#{url})")
	int add(MovieMTime tv);

}
