package businesslayer.gr.com;

import java.sql.Date;
import java.util.List;
import java.time.LocalDate;


import javax.ejb.Stateless;

import dao.gr.com.HitRecordDao;
import dao.gr.com.UrlEntityDao;
import entity.gr.com.HitRecord;
import entity.gr.com.UrlEntity;

@Stateless
public class UrlService implements UrlServiceRemote {

	public UrlService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<UrlEntity> getUrl() {
		UrlEntityDao urlentitydao=new UrlEntityDao();
		return urlentitydao.getAllUrl();
	}
	
	@Override
	public UrlEntity addShortUrl(UrlEntity urlEntity) {
		UrlEntityDao urlentitydao=new UrlEntityDao();
		UrlLogic urlogic=new UrlLogic();
		if(urlogic.checkPresent(urlEntity.getFullname())!=null)
		{
			urlEntity.setShortname(urlogic.checkPresent(urlEntity.getFullname()));
		}
		else {
			urlEntity.setShortname(urlogic.shortenURL(urlEntity.getFullname()));
			urlEntity.setDate(Date.valueOf(LocalDate.now()));
			urlentitydao.addUrl(urlEntity);
		}
		return urlEntity;	
	}
	
	@Override
	public Boolean deleteShortUrl(UrlEntity urlEntity) {
		UrlEntityDao urlentitydao=new UrlEntityDao();
		return urlentitydao.deleteUrl(urlEntity.getId());
	}

	@Override
	public String hitUrl(String shorturl, String userAgent) {
		UrlLogic urlogic=new UrlLogic();
		HitRecordDao hitRecordDao=new HitRecordDao();
		HitRecord hitRecord =new HitRecord();
		if(urlogic.getFullUrl(shorturl)!=null) {
		hitRecord.setDate(Date.valueOf(LocalDate.now()));
		hitRecord.setUrlShort(urlogic.getShortUrl(shorturl));
		hitRecord.setBrowser(urlogic.getBrowser(userAgent));
		hitRecord.setOpSys(urlogic.getOS(userAgent));
		hitRecordDao.addHit(hitRecord);
		 return urlogic.getFullUrl(shorturl);
		}
		return "Url Not Found";
	}
	
	@Override
	public List<HitRecord> getRecords(int id)
	{
		HitRecordDao hitRecordDao=new HitRecordDao();
		return hitRecordDao.getAll(id);
	}
	
	
	
}
