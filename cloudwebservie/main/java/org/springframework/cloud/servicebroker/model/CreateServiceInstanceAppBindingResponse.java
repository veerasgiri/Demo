package org.springframework.cloud.servicebroker.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Details of  a response to a request to create a new service instance binding for an application.
 * 
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreateServiceInstanceAppBindingResponse extends CreateServiceInstanceBindingResponse {

	/**
	 * A free-form hash of credentials that the bound application can use to access the service.
	 */
	@JsonSerialize
	@JsonProperty("credentials")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object> credentials;

	/**
	 * The URL to which Cloud Foundry should drain logs for the bound application. Can be <code>null</code> to
	 * indicate that the service binding does not support syslog drains.
	 */
	@JsonSerialize
	@JsonProperty("syslog_drain_url")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String syslogDrainUrl;

	public CreateServiceInstanceAppBindingResponse withCredentials(final Map<String, Object> credentials) {
		this.credentials = credentials;
		return this;
	}

	public CreateServiceInstanceAppBindingResponse withSyslogDrainUrl(final String syslogDrainUrl) {
		this.syslogDrainUrl = syslogDrainUrl;
		return this;
	}

	public CreateServiceInstanceAppBindingResponse withBindingExisted(final boolean bindingExisted) {
		this.bindingExisted = bindingExisted;
		return this;
	}
}
