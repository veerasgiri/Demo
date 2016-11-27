package org.springframework.cloud.servicebroker.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Details of  a response to a request to delete a service instance.
 *
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeleteServiceInstanceResponse extends AsyncServiceInstanceResponse {
	public DeleteServiceInstanceResponse withAsync(final boolean async) {
		this.async = async;
		return this;
	}
}
