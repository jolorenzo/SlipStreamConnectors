package com.sixsq.slipstream.connector.openstack;

/*
 * +=================================================================+
 * SlipStream Server (WAR)
 * =====
 * Copyright (C) 2013 SixSq Sarl (sixsq.com)
 * =====
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -=================================================================-
 */

import com.sixsq.slipstream.connector.CredentialsBase;
import com.sixsq.slipstream.credentials.Credentials;
import com.sixsq.slipstream.credentials.ICloudCredential;
import com.sixsq.slipstream.exceptions.InvalidElementException;
import com.sixsq.slipstream.exceptions.SlipStreamRuntimeException;
import com.sixsq.slipstream.exceptions.ValidationException;
import com.sixsq.slipstream.persistence.User;
import com.sixsq.slipstream.persistence.UserParameter;

import java.util.Map;

import static com.sixsq.slipstream.credentials.CloudCredential.fromJson;

public class OpenStackCredentials extends CredentialsBase implements Credentials {
	
	public OpenStackCredentials(User user, String connectorInstanceName) {
		super(user);
		try {
			cloudParametersFactory = new OpenStackUserParametersFactory(connectorInstanceName);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw (new SlipStreamRuntimeException(e));
		}
	}

	public String getKey() throws InvalidElementException {
		return getParameterValue(OpenStackUserParametersFactory.KEY_PARAMETER_NAME);
	}

	public String getSecret() throws InvalidElementException {
		return getParameterValue(OpenStackUserParametersFactory.SECRET_PARAMETER_NAME);
	}
	/**
	 * If mapping is not known null is returned.
	 *
	 * @param pName          parameter name of the UserParameter.
	 * @param cloudCredsJSON JSON with cloud cred definition document.
	 * @return value for the parameter or null
	 * @throws ValidationException
	 */
	protected String getCloudCredParamValue(String pName, String
			cloudCredsJSON) throws ValidationException {
		OpenStackCloudCredDef credDef;
		switch (pName) {
			case OpenStackUserParametersFactory.KEY_DOMAIN_NAME:
				credDef = (OpenStackCloudCredDef) fromJson(cloudCredsJSON,
						OpenStackCloudCredDef.class);
				return credDef.domainName;
			case OpenStackUserParametersFactory.KEY_TENANT_NAME:
				credDef = (OpenStackCloudCredDef) fromJson(cloudCredsJSON,
						OpenStackCloudCredDef.class);
				return credDef.tenantName;
			default:
				return super.getCloudCredParamValue(pName, cloudCredsJSON);
		}
	}

	public ICloudCredential getCloudCredential(Map<String, UserParameter> params, String connInstanceName) {
		if (params.size() < 1) {
			return null;
		}
		return new OpenStackCloudCredDef(connInstanceName, params);
	}
}
