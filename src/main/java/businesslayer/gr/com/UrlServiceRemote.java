package businesslayer.gr.com;

import java.util.List;

import javax.ejb.Remote;

import entity.gr.com.HitRecord;
import entity.gr.com.UrlEntity;



@Remote
public interface UrlServiceRemote {

	public UrlEntity addShortUrl(UrlEntity urlEntity);
	public Boolean deleteShortUrl(UrlEntity urlEntity);
	public List<UrlEntity> getUrl();
	public String hitUrl(String shorturl,String userAgent);
	public List<HitRecord> getRecords(int id);
	
}
