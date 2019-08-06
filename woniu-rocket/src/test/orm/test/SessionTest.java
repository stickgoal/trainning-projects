package test.orm.test;

import java.util.Date;

import com.woniuxy.rocket.orm.core.Session;
import com.woniuxy.rocket.orm.core.SessionFactory;

import junit.framework.TestCase;
import test.orm.test.entity.User;

public class SessionTest extends TestCase{

	public  void testInit() {
		SessionFactory sef = new SessionFactory("/orm.properties");
		assertNotNull(sef);
		assertNotNull(sef.getConfiguration());
		assertNotNull(sef.getMetaMap());
		System.out.println(sef);
	}
	
	public void testSave() {
		SessionFactory sef = new SessionFactory("/orm.properties");
		Session session = sef.openSession();
		User user = new User();
		user.setUserId(1);
		user.setRegisterTime(new Date());
		user.setUsername("abc");
		user.setPassword("asdfadf");
		session.save(user);
		User result = session.get(User.class, 1);
		assertNotNull(result);
		
		session.delete(User.class, 1);
		User result2 = session.get(User.class, 1);
		assertNull(result2);
	}
	
}
