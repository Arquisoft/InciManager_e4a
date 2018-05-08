package inciManager.actors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;

public class SimpleTestsIncidence {

	LatLong latlong = new LatLong("42.422789,", "-10.071153");
	Incidence incidence;
	
	@Before
	public void definirIncidencia()
	{
		incidence = new Incidence("Inci", latlong, "38864922A", "Desx");
		assertNotNull(incidence);
	}
	
	@Test
	public void incidenceName()
	{
		assertNotNull(incidence.getInciName());
		assertEquals("Inci", incidence.getInciName());
		
		incidence.setInciName("Inci2");
		assertEquals("Inci2", incidence.getInciName());
	}
	
	@Test
	public void incidenceLocation()
	{
		assertNotNull(incidence.getLocation());
		assertEquals(latlong, incidence.getLocation());
		
		LatLong latlong2 = new LatLong("12.422789,", "-18.071153");
		incidence.setLocation(latlong2);
		assertEquals(latlong2, incidence.getLocation());
	}
	
	@Test
	public void incidenceAgent()
	{
		assertNotNull(incidence.getAgent());
		assertEquals("38864922A", incidence.getAgent());
		
		incidence.setAgent("38864911A");
		assertEquals("38864911A", incidence.getAgent());
	}
	
	@Test
	public void incidenceDescription()
	{
		assertNotNull(incidence.getInciDescription());
		assertEquals("Desx", incidence.getInciDescription());
		
		incidence.setInciDescription("Desx2");
		assertEquals("Desx2", incidence.getInciDescription());
	}
}
