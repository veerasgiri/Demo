package org.springframework.cloud.servicebroker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A binding to a service instance
 * 
 */
public class ServiceInstanceBinding {

	private String id;
	private String serviceInstanceId;
	private Map<String,Object> credentials = new HashMap<String,Object>();
	private String syslogDrainUrl;
	private String appGuid;

	public ServiceInstanceBinding(String id, 
			String serviceInstanceId, 
			Map<String,Object> credentials,
			String syslogDrainUrl, String appGuid) {
		this.id = id;
		this.serviceInstanceId = serviceInstanceId;
		setCredentials(credentials);
		this.syslogDrainUrl = syslogDrainUrl;
		this.appGuid = appGuid;
	}

	public String getId() {
		return id;
	}

	public String getServiceInstanceId() {
		return serviceInstanceId;
	}

	public Map<String, Object> getCredentials() {
		return credentials;
	}

	private void setCredentials(Map<String, Object> credentials) {
		if (credentials == null) {
			this.credentials = new HashMap<>();
		} else {
			this.credentials = credentials;
		}
	}

	public String getSyslogDrainUrl() {
		return syslogDrainUrl;
	}
	
	public String getAppGuid() {
		return appGuid;
	}
	
}
