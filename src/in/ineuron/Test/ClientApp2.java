package in.ineuron.Test;



import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.InsurencePolicy;
import in.ineuron.Util.HibernateUtil;

public class ClientApp2 {
	public static void main(String[] args)
	{
		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		Boolean flag = false;
		session = HibernateUtil.getSession();
		
		try {
			
			transaction = session.beginTransaction();
			InsurencePolicy policy= session.get(InsurencePolicy.class, 1,LockMode.UPGRADE_NOWAIT);
			System.out.println(policy);
			
			
			policy.setTenure(30);
			flag = true;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				System.out.println("Object inserted to the database with the id :: " + id);
			}
			else
			{
				transaction.rollback();
				System.out.println("Object not inserted to the database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
