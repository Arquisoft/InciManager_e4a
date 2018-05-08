package inciManager;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import asw.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {


	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;


	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	private MediaType JSONContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	public void T01notifyIncidenceSuccess() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "descripcion de la incidencia");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isOk());
	}

	@Test
	public void T02incorrectLogin() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "gonzi");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "descripcion de la incidencia");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());

	}

	@Test
	public void T03incorrectPassword() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "1234566666");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "descripcion de la incidencia");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void T04incorrectKind() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Sensor");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "descripcion de la incidencia");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void T05emptyIncidenceName() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "");
		payload.put("description", "descripcion de la incidencia");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void T06emptyIncidenceDescription() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void T07incorrectIncidenceLocation() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void T08icorrectIncidenceLocation2() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		Map<String, Object> prop = new HashMap<>();
		List<String> locat = new ArrayList<String>();
		List<String> morinfo = new ArrayList<String>();
		locat.add("11.111111");locat.add("11.111111");locat.add("11.111111");
		morinfo.add("adInfo1");
		prop.put("key1","value1");
		prop.put("key2","value2");

		payload.put("login", "13864928P");
		payload.put("password", "123456");
		payload.put("kind", "Person");
		payload.put("name", "nombreIncidencia");
		payload.put("description", "");
		payload.put("location", locat);
		payload.put("labels", "label1,label2");
		payload.put("moreInfo", morinfo);
		payload.put("properties", prop);   
		mockMvc.perform(post("/notify")
				.content(this.json(payload))
				.contentType(JSONContentType))
		.andExpect(status().isNotFound());

	}

	@Test 
	public void T09icorrectIncidenceLocation3() throws Exception { 
		Map<String, Object> payload = new HashMap<>(); 
		Map<String, Object> prop = new HashMap<>(); 
		List<String> locat = new ArrayList<String>(); 
		List<String> morinfo = new ArrayList<String>(); 
		locat.add("Oviedo");locat.add("11.111111"); 
		morinfo.add("adInfo1"); 
		prop.put("key1","value1"); 
		prop.put("key2","value2"); 

		payload.put("login", "13864928P"); 
		payload.put("password", "123456"); 
		payload.put("kind", "Person"); 
		payload.put("name", "nombreIncidencia"); 
		payload.put("description", ""); 
		payload.put("location", locat); 
		payload.put("labels", "label1,label2"); 
		payload.put("moreInfo", morinfo); 
		payload.put("properties", prop);    
		mockMvc.perform(post("/notify") 
				.content(this.json(payload)) 
				.contentType(JSONContentType)) 
		.andExpect(status().isNotFound()); 

	} 

	/**
	 * Transforma un objeto en un string JSON
	 * @param o objeto a convertir
	 * @return string conteniendo el JSON
	 * @throws IOException
	 */

	@SuppressWarnings("unchecked")
	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}