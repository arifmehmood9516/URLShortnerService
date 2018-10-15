package dao.gr.com;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import entity.gr.com.UrlEntity;
import factory.gr.com.GrSessionFactory;

public class UrlEntityDao {

	public List<UrlEntity> getAllUrl() {

		List<UrlEntity> urlentities = null;
		Session session = GrSessionFactory.startTransaction();
		String sql = "SELECT * FROM urlentity";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(UrlEntity.class);
		urlentities = query.list();
		GrSessionFactory.endTransaction(session);
		return urlentities;
	}
	
	public Boolean addUrl(UrlEntity urlEntity) {
		try {
			Session session = GrSessionFactory.startTransaction();
			session.save(urlEntity);
			GrSessionFactory.endTransaction(session);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	
	public Boolean deleteUrl(int id) {
		try {
			Session session = GrSessionFactory.startTransaction();
			UrlEntity urlEntity=session.get(UrlEntity.class, id);
			session.delete(urlEntity);
			GrSessionFactory.endTransaction(session);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
}

