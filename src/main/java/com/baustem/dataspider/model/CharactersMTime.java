package com.baustem.dataspider.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
@TargetUrl("http://people.mtime.com/\\d+/")
@HelpUrl("http://movie.mtime.com/people/list/*")
public class CharactersMTime implements AfterExtractor {
	
	@ExtractBy("//title/text()")
	private String name;
	
	@ExtractBy("//i[@id='birthdayRegion']/@birth")
	private String strBirthDay;
	private Date birthDay;
	
	
	@ExtractBy("//dl[@class='per_base_star clearfix __r_c_']/dd/allText()")
	private List<String> baseDetail;
	private String constellation;
	private String bloodType;
	private String height;
	private String weight;
	
	@ExtractBy("//dl[@class='per_base_born __r_c_']/dd/text()")
	private String born;
	
	@ExtractBy("//dl[@id='experienceRegion']/dd/allText()")
	private List<String> playExperence;
	private String playExperenceStr;
	
	
	@ExtractBy("//div[@class='per_rmod per_info __r_c_']/p/allText()")
	private String story;
	
	@ExtractByUrl
	private String url;
	
	@ExtractBy("//div[@class='per_cover __r_c_']/span/a/img/@src")
	private String posterUrl;
	
	@Override
	public void afterProcess(Page page) {
		init();
	}
	
	public void init(){
		setBirthDay(strBirthDay);
		setConstellation(baseDetail);
		setBloodType(baseDetail);
		setHeight(baseDetail);
		setWeight(baseDetail);
		setPlayExperenceStr(playExperence);
		String pu = downPoster(posterUrl);
		setPosterUrl(pu);
	}
	
	
	

	
	public String downPoster(String url){
		try {
			URL u = new URL(url);
			InputStream in = u.openStream();
			File store = new File("D:/poster/people/"+System.currentTimeMillis()+".jpg");
			OutputStream out = new FileOutputStream(store);
			byte[] buff = new byte[2048];
			int b;
			while((b=in.read(buff))!=-1){ 
				out.write(buff,0 , b);
			}
			out.close();
			return store.getAbsolutePath();
		} catch (IOException e) {
			return "url:无效";
		}
	}

	//-----------------------setter,getter---------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String strBirthDay) {
		if(strBirthDay!=null&&strBirthDay.trim().length()>0){
			try {
				this.birthDay = new SimpleDateFormat("yyyy-MM-dd").parse(strBirthDay);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(List<String> baseDetail) {
		if(baseDetail.size()>0){
			String c = baseDetail.get(0).trim();
			if(c.length()%2==0){
				c = c.substring(0,c.length()/2);
			}
			this.constellation = c;
		}
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(List<String> baseDetail) {
		for (String d : baseDetail) {
			if(d.contains("A")||d.contains("B")||d.contains("O")){
				this.bloodType = d;
			}
		}
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(List<String> baseDetail) {
		for (String d : baseDetail) {
			String[] detail = d.trim().split(" ");
			for (String de : detail) {
				if(de.contains("cm")){
					this.height = de;
				}
			}
		}
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(List<String> baseDetail) {
		for (String d : baseDetail) {
			String[] s = d.trim().split(" ");
			for (String de : s) {
				if(de.contains("kg")){
					this.weight = de;
				}
			}
		}
	}

	public String getBorn() {
		if(born!=null&&strBirthDay!=null){
			born = born.replace(strBirthDay, "").trim();
		}
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getPlayExperenceStr() {
		return playExperenceStr;
	}

	public void setPlayExperenceStr(List<String> playExperence) {
		StringBuilder sb = new StringBuilder();
		for (String p : playExperence) {
			sb.append(p);
		}
		this.playExperenceStr = sb.toString();
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	
	

	
	
	
	
	
	

}
