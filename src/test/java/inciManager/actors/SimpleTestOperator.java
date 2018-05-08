package inciManager.actors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asw.dbManagement.entities.Operator;

public class SimpleTestOperator {

	Operator oper;
	
	@Before
	public void definirOperator()
	{
		oper = new Operator(new Long(1), "jesusamen@gmail.com", "Jesus", 0);
		assertNotNull(oper);
	}
	
	@Test
	public void operatorEmail()
	{
		assertNotNull(oper.getEmail());
		assertEquals("jesusamen@gmail.com", oper.getEmail());
		
		oper.setEmail("des2");
		assertEquals("des2", oper.getEmail());
	}
	
	@Test
	public void operatorNombre()
	{
		assertNotNull(oper.getOperatorname());
		assertEquals("Jesus", oper.getOperatorname());
		
		oper.setOperatorname("Jesus2");
		assertEquals("Jesus2", oper.getOperatorname());
		
	}
	
	@Test
	public void operatorAdmin()
	{
		assertNotNull(oper.getIsAdmin());
		assertEquals(0, oper.getIsAdmin());
		
		oper.setIsAdmin(1);
		assertEquals(1, oper.getIsAdmin());
	}
}
