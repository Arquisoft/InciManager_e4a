package asw.json;

import com.fasterxml.jackson.databind.module.SimpleModule;

import asw.dbManagement.entities.Notification;

public class JSONModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335213690569132716L;

	public JSONModule() {		
		this.addSerializer(Notification.class, new NotificationSerializer());
	}
}
