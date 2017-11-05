package de.metroag.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppProperties {
	@Value("${boardSize}")
private String size;
	public String getMarker1() {
		return marker1;
	}

	public void setMarker1(String marker1) {
		this.marker1 = marker1;
	}

	public String getMarker2() {
		return marker2;
	}

	public void setMarker2(String marker2) {
		this.marker2 = marker2;
	}

	@Value("${boardMarker1}")
	private String marker1;
	@Value("${boardMarker2}")
	private String marker2;
public String getSize() {
	return size;
}

public void setSize(String size) {
	this.size = size;
}

}
