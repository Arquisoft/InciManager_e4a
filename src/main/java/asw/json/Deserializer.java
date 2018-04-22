package asw.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import asw.dbManagement.entities.Agent;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.LatLong;

public class Deserializer extends JsonDeserializer<Incidence> {
	@SuppressWarnings("unchecked")
	@Override
	public Incidence deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ObjectCodec objectCodec = parser.getCodec();
		JsonNode jsonNode = objectCodec.readTree(parser);

		Incidence incident = new Incidence();
		incident.setAgent(new Agent(jsonNode.get("agent").get("dni").asText(),jsonNode.get("agent").get("email").asText(),
				new LatLong(jsonNode.get("location").get("lat").asText(),jsonNode.get("location").get("lon").asText()),
				jsonNode.get("agent").get("nombre").asText(),jsonNode.get("agent").get("password").asText(),jsonNode.get("agent").get("kind").asInt()));
		incident.setInciName(jsonNode.get("inciName").asText());
		incident.setLocation(new LatLong(jsonNode.get("location").get("lat").asText(),
				jsonNode.get("location").get("lon").asText()));

		Iterator<JsonNode> tagsIter = jsonNode.get("tags").elements();
		while (tagsIter.hasNext()) {
			JsonNode tag = tagsIter.next();
			incident.getTags().add(tag.asText());
		}

		Iterator<JsonNode> infoIter = jsonNode.get("moreInfo").elements();
		while (infoIter.hasNext()) {
			JsonNode info = infoIter.next();
			incident.getMoreInfo().add(info.asText());
		}

		JsonNode properties = jsonNode.get("properties");
		ObjectMapper mapper = new ObjectMapper();
		incident.setProperties(mapper.convertValue(properties, Map.class));

		return incident;
	}
}
