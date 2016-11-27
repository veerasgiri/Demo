package org.springframework.cloud.servicebroker.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * The catalog of services offered by the service broker.
 * 
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog {

	/**
	 * A list of service offerings provided by the service broker.
	 */
	@NotEmpty
	@JsonSerialize(nullsUsing = EmptyListSerializer.class)
	@JsonProperty("services")
	private final List<ServiceDefinition> serviceDefinitions;

	public Catalog() {
		this.serviceDefinitions = new ArrayList<>();
	}

	public Catalog(List<ServiceDefinition> serviceDefinitions) {
		this.serviceDefinitions = serviceDefinitions;
	}
	
	public GetHashMapCatalog(MyHashMap<ServiceDefinition,ServiceDefinition> serviceDefinitions) {
		
		List<serviceDefinitions> list = new ArrayList<serviceDefinitions>(map.values());
		this.serviceDefinitions = list;
	}
	
	
	
}

