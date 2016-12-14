package com.baustem.dataspider.dao;

import org.apache.ibatis.annotations.Insert;

import com.baustem.dataspider.model.CharactersMTime;

public interface CharactersMTimeDao {
	
	@Insert("INSERT INTO PeopleData(`name`,birthDay,constellation,bloodType,height,weight,born,playExperenceStr,story,url,posterUrl) "
			+ "VALUES(#{name},#{birthDay},#{constellation},#{bloodType},#{height},#{weight},#{born},#{playExperenceStr},#{story},#{url},#{posterUrl})")
	int add(CharactersMTime charactersMTime);
	
	

}
