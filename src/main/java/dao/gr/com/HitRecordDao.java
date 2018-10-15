package dao.gr.com;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.gr.com.HitRecord;
import entity.gr.com.UrlEntity;
import factory.gr.com.GrSessionFactory;



public class HitRecordDao {

	public List<HitRecord> getAll(int urlid) {

		List<HitRecord> hitrecords = null;
		Session session = GrSessionFactory.startTransaction();
		String sql = "SELECT * FROM hitrecord where url_id="+urlid;
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(HitRecord.class);
		hitrecords = query.list();
		GrSessionFactory.endTransaction(session);
		
		if(hitrecords.size()!=0)
		{
			return hitrecords;
			}
		else {
			UrlEntityDao urlEntityDao=new UrlEntityDao();
			List<UrlEntity> urls=urlEntityDao.getAllUrl();
			for(int i=0; i<urls.size(); i++){
	             if(urls.get(i).getId()==urlid)
	             {
	            	 HitRecord hit=new HitRecord();
	            	 hit.setUrlFull(urls.get(i).getFullname());
	            	 hit.setUrlShort(urls.get(i).getShortname());
	            	 hit.setDateCreated(urls.get(i).getDate());
	            	 hitrecords.add(hit);
	             }
	         }
			return hitrecords;
		}
	}
	
	public Boolean addHit(HitRecord hitRecord) {
		try {
			Session session = GrSessionFactory.startTransaction();
			 Criteria criteria = session.createCriteria(UrlEntity.class)
	                    .add(Restrictions.eq("shortname", hitRecord.getUrlShort()));
			 Object result = criteria.uniqueResult();
			 UrlEntity urlEntity=(UrlEntity) result;
			 hitRecord.setUrlE(urlEntity);
			 hitRecord.setUrlFull(urlEntity.getFullname());
			 hitRecord.setDateCreated(urlEntity.getDate());
			 urlEntity.setUrlhits(urlEntity.getUrlhits()+1);
			 urlEntity.getHitrecords().add(hitRecord);
			 session.save(hitRecord);
			GrSessionFactory.endTransaction(session);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
