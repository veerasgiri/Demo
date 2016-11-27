package org.springframework.cloud.servicebroker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Details of a response to a request to update a service instance.
 *
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateServiceInstanceResponse extends AsyncServiceInstanceResponse {
	public UpdateServiceInstanceResponse withAsync(final boolean async) {
		this.async = async;
		return this;
	}
}
