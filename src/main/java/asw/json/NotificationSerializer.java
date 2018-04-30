package asw.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;

public class NotificationSerializer extends JsonSerializer<Notification> {

	@Override
	public void serialize(Notification notification, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		generator.writeStartObject();

		generator.writeStringField("id", String.valueOf(notification.getId()));
		generator.writeStringField("descripcion", notification.getDescription());

		generator.writeObjectFieldStart("incidencia");
		Incidence incidence = notification.getIncidencia();
		// agent info
		generator.writeObjectFieldStart("agent");
		generator.writeStringField("dni", incidence.getAgent());		
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

		generator.writeObjectFieldStart("operator");
		generator.writeStringField("id", String.valueOf(notification.getOperator().getID()));
		generator.writeStringField("name", notification.getOperator().getOperatorname());
		generator.writeStringField("email", notification.getOperator().getEmail());
		generator.writeEndObject();

		generator.writeEndObject();

	}

}
