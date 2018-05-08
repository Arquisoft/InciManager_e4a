package asw.services.chatbot;

public class Preguntas{
	public String[] preguntas;
	public String[] aclaracion;
	
	public Preguntas() {
		preguntas = new String[7];
		preguntas[0] = "Introduzca el nombre que quiere darle a la incidencia.";
		preguntas[1] = "Introduzca una descripción en pocas palabras de la incidencia.";
		preguntas[2] = "Introduzca la latitud en la que se ha producido la incidencia.";
		preguntas[3] = "Introduzca la longitud en la que se ha producido la incidencia.";
		preguntas[4] = "Introduzca las etiquetas que quiere asignarle a la incidencia.";
		preguntas[5] = "Introduzca información adicional.";
		preguntas[6] = "Introduzca propiedades adicionales de la incidencia.";
		
		aclaracion = new String[7];
		aclaracion[0] = "";
		aclaracion[1] = "";
		aclaracion[2] = "";
		aclaracion[3] = "";
		aclaracion[4] = "Han de estar separadas por comas.";
		aclaracion[5] = "Han de estar separadas por comas.";
		aclaracion[6] = "(Propiedad:valor,...)";
	}
	
	
	public String get(int i) {
		return this.preguntas[i];
	}
	public String getAclaracion(int i) {
		return aclaracion[i];
	}
	
}