package entity.gr.com;

import java.io.Serializable;
import java.sql.Date;

public class HitRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String urlShort;
	private String urlFull;
	private Date date; 
	private Date dateCreated; 
	private String browser;
	private String opSys;
	private int url_Id;
	private UrlEntity urlE;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public int getUrl_Id() {
		return url_Id;
	}
	public void setUrl_Id(int url_Id) {
		this.url_Id = url_Id;
	}
	public UrlEntity getUrlE() {
		return urlE;
	}
	public void setUrlE(UrlEntity urlE) {
		this.urlE = urlE;
	}
	public String getOpSys() {
		return opSys;
	}
	public void setOpSys(String opSys) {
		this.opSys = opSys;
	}
	public String getUrlShort() {
		return urlShort;
	}
	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrlFull() {
		return urlFull;
	}
	public void setUrlFull(String urlFull) {
		this.urlFull = urlFull;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
}