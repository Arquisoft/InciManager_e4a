package asw.services.chatbot;

public class Preguntas{
	public String[] preguntas;
	
	public Preguntas() {
		preguntas = new String[7];
		preguntas[0] = "Introduzca el nombre que quiere darle a la incidencia.";
		preguntas[1] = "Introduzca una descripción en pocas palabras de la incidencia.";
		preguntas[2] = "Introduzca la latitud en la que se ha producido la incidencia.";
		preguntas[3] = "Introduzca la longitud en la que se ha producido la incidencia.";
		preguntas[4] = "Introduzca las etiquetas que quiere asignarle a la incidencia. Han de estar separadas por comas.";
		preguntas[5] = "Introduzca información adicional.  Han de estar separadas por comas.";
		preguntas[6] = "Introduzca propiedades adicionales de la incidencia.";
	}
	
	
	public String get(int i) {
		return this.preguntas[i];
	}
	
}