package de.metroag.dto;

public class BoardDTO {
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLetter() {
	return letter;
}
public void setLetter(String letter) {
	this.letter = letter;
}
public String getClassStyle() {
	return classStyle;
}
public void setClassStyle(String classStyle) {
	this.classStyle = classStyle;
}
private String letter="";
private String classStyle="box";
private String style="";
public BoardDTO(String id, String style) {
	super();
	this.id = id;
	this.style = style;
}
public String getStyle() {
	return style;
}
public void setStyle(String id,String style) {
	this.style = style;
	this.id = id;
}
public BoardDTO(String id) {
	super();
	this.id = id;
}

}
