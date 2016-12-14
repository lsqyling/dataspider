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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
@TargetUrl("http://movie.mtime.com/\\d+/")
@HelpUrl("http://movie.mtime.com/tv/list/*")
public class TVMTime implements AfterExtractor {
	
	private static Map<String,String> PDETAIL = null;
	
	@ExtractBy("//title/text()")
	private String name;
	
	@ExtractBy("//p[@class='db_year']/a/text()")
	private String day;
	private Date birthDay;
	
	@ExtractBy("//div[@class='otherbox __r_c_']/a/text()")
	private List<String> types;
	private String type;
	
	@ExtractBy("//img[@rel='v:image']/@src")
	private String posterUrl;
	
	@ExtractBy("//a[@rel='v:directedBy']/text()")
	private List<String> directors;
	private String director;
	
	@ExtractBy("//dl[@class='info_l']/dd[@class='__r_c_']/allText()")
	private List<String> playDetail;
	
	private String scriptwriter;
	private String producer;
	private String region;
	private String issueCompany;
	
	@ExtractBy("//p[@class='mt6 lh18']/text()")
	private String story;
	
	@ExtractBy("//p[@class='__r_c_']/a/text()")
	private List<String> actors;
	private String actor;
	
	@ExtractByUrl
	private String url;
	
	@Override
	public void afterProcess(Page page) {
		init();
	}
	
	public void init(){
		PDETAIL = processPlayDetail(playDetail);
		setBirthDay(day);
		setType(types);
		
		String posterPath = downPoster(posterUrl);
		setPosterUrl(posterPath);
		setDirector(directors);
		setScriptwriter(PDETAIL);
		setProducer(PDETAIL);
		setRegion(PDETAIL);
		setIssueCompany(PDETAIL);
		setActor(actors);
	}
	
	private Map<String,String> processPlayDetail(List<String> playDetail){
		Map<String,String> detail = new HashMap<>();
		for (String d : playDetail) {
			String[] split = d.trim().replace(".", "").split("： ");
			StringBuilder sb = new StringBuilder();
			for (String s : split) {
				if(s.trim().length()>0){
					sb.append(s.trim()).append(":");
				}
			}
			String[] sp = sb.toString().split(":");
			if(sp.length==2){
				detail.put(sp[0], sp[1]);
			}
		}
		return detail;
	}
	

	
	public String downPoster(String url){
		try {
			URL u = new URL(url);
			InputStream in = u.openStream();
			File store = new File("D:/poster/tv/"+System.currentTimeMillis()+".jpg");
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


	public void setBirthDay(String day) {
		if(day!=null&&day.trim().length()>0){
			try {
				this.birthDay = new SimpleDateFormat("yyyy").parse(day);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}


	public String getType() {
		return type;
	}


	public void setType(List<String> types) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < types.size()-1; i++) {
			sb.append(types.get(i)).append(" ");
		}
		this.type = sb.toString().trim();
	}


	public String getPosterUrl() {
		return posterUrl;
	}


	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(List<String> directors) {
		StringBuilder sb = new StringBuilder();
		for (String s : directors) {
			sb.append(s).append(" ");
		}
		this.director = sb.toString().trim();
	}


	public String getScriptwriter() {
		return scriptwriter;
	}


	public void setScriptwriter(Map<String,String> pd) {
		if(pd.containsKey("编剧")){
			this.scriptwriter = pd.get("编剧");
		}
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(Map<String,String> pd) {
		if(pd.containsKey("制作人")){
			this.producer = pd.get("制作人");
		}
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(Map<String,String> pd) {
		if(pd.containsKey("国家地区")){
			this.region = pd.get("国家地区");
		}
	}
	
	public String getIssueCompany() {
		return issueCompany;
	}
	
	public void setIssueCompany(Map<String,String> pd) {
		if(pd.containsKey("发行公司")){
			this.issueCompany = pd.get("发行公司");
		}
	}
	


	public String getStory() {
		return story;
	}


	public void setStory(String story) {
		this.story = story;
	}


	public String getActor() {
		return actor;
	}


	public void setActor(List<String> as) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < as.size()-1; i++) {
			sb.append(as.get(i)).append(" ");
		}
		this.actor = sb.toString().trim();
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TVMTime [name=" + name + ", birthDay=" + birthDay + ", type="
				+ type + ", posterUrl=" + posterUrl + ", director=" + director
				+ ", scriptwriter=" + scriptwriter + ", producer=" + producer
				+ ", region=" + region + ", story=" + story + ", actor="
				+ actor + "]";
	}



	
	
	
	
	

}
