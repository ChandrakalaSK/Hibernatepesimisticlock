package in.ineuron.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.InsurencePolicy;

import in.ineuron.Util.HibernateUtil;

public class InsertApp {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		Integer id = null;
		
		
		try {
		session=HibernateUtil.getSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
			InsurencePolicy policy	= new InsurencePolicy();
			policy.setPname("LIC");
			policy.setPtype("Twice");
			policy.setTenure(2);
			
			id =	(Integer) session.save(policy);
				flag=true;
				
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object saved to database");
			
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
