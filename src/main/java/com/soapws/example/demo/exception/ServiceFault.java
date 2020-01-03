package com.soapws.example.demo.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Raju
 * 
 *         The ServiceFault class is used to propagate the appropriate error
 *         code and description up the chain. Typically the code is used to map
 *         the exception on the client side and the message is just a
 *         description indicating the user/developer what exactly went wrong.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceFault", propOrder = { "code", "description" })
public class ServiceFault {

	private String code;
	private String description;

	public ServiceFault() {
	}

	public ServiceFault(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
