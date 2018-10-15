package entity.gr.com;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class UrlEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String fullname;
	private String shortname;
	private Date date; 
	private int urlhits;
	private Set<HitRecord> hitrecords = new HashSet<HitRecord>(0);
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public int getUrlhits() {
		return urlhits;
	}
	public void setUrlhits(int urlhits) {
		this.urlhits = urlhits;
	}
	public Set<HitRecord> getHitrecords() {
		return hitrecords;
	}
	public void setHitrecords(Set<HitRecord> hitrecords) {
		this.hitrecords = hitrecords;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}