package inciManager.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asw.dbManagement.entities.Notification;
import asw.dbManagement.entities.Operator;

public class SimpleTestNotification {

	Notification noti;
	Operator oper;
	
	@Before
	public void definirNotification()
	{
		oper = new Operator(new Long(1), "jesusamen@gmail.com", "Jesus", 0);
		assertNotNull(oper);
		noti = new Notification(new Long(1), "ja1", oper);
		assertNotNull(noti);
	}
	
	@Test
	public void notificationDescripcion()
	{
		assertNotNull(noti.getDescription());
		
		noti.setDescription("des2");
		assertEquals("des2", noti.getDescription());
	}
	
	@Test
	public void notificationOperator()
	{
		assertNotNull(noti.getOperator());
		
		Operator oper2 = new Operator(new Long(2), "ffef@gmail.com", "Jairo", 0);
		noti.setOperator(oper2);
		assertEquals(oper2, noti.getOperator());
	}
}
