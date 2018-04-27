package asw.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import asw.dbManagement.entities.Incidence;

public class IncidenceSerializer extends JsonSerializer<Incidence> {

	@Override
	public void serialize(Incidence incidence, JsonGenerator generator,
			SerializerProvider provider) throws IOException {
		
		generator.writeStartObject();
		
		// agent info
		generator.writeObjectFieldStart("agent");
		generator.writeStringField("dni", incidence.getAgent().getDni());
		generator.writeStringField("email", incidence.getAgent().getEmail());
		generator.writeStringField("nombre", incidence.getAgent().getNombre());
		generator.writeStringField("password", incidence.getAgent().getPassword());
		generator.writeNumberField("type", incidence.getAgent().getType());
		generator.writeEndObject();
		
		generator.writeStringField("inciName", incidence.getInciName());
		
		// location
		generator.writeObjectFieldStart("location");
		generator.writeStringField("lat", incidence.getLocation().latitude);
		generator.writeStringField("lon", incidence.getLocation().longitude);
		generator.writeEndObject();
		
		// tags
		generator.writeArrayFieldStart("tags");
		for (String tag : incidence.getTags()) {
			generator.writeString(tag);
		}
		generator.writeEndArray();
		
		// more info
		generator.writeArrayFieldStart("moreInfo");
		for (String info : incidence.getMoreInfo()) {
			generator.writeString(info);
		}
		generator.writeEndArray();
		
		// properties
		generator.writeObjectFieldStart("properties");
		for (String key : incidence.getProperties().keySet()) {
			generator.writeObjectField(key, incidence.getProperties().get(key));
		}
		generator.writeEndObject();
		
		generator.writeEndObject();
	}

}
