package com.soapws.example.demo.exception;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

/*The DetailSoapFaultDefinitionExceptionResolver extends from the SoapFaultMappingExceptionResolver 
 * and is used for enhancing the SoapFault with detailed information about the error that 
 * occurred. We can override the customizeFault method to enhance the exception with detail 
 * information. We do this by calling the addFaultDetail method of the SoapFault class, 
 * and adding QName indicating the element with the addFaultDetailElement.*/
public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

	private static final QName CODE = new QName("code");
	private static final QName DESCRIPTION = new QName("description");

	@Override
	protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		logger.warn("Exception processed ", ex);
		if (ex instanceof ServiceFaultException) {
			ServiceFault serviceFault = ((ServiceFaultException) ex).getServiceFault();
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(CODE).addText(serviceFault.getCode());
			detail.addFaultDetailElement(DESCRIPTION).addText(serviceFault.getDescription());
		}
	}

}
