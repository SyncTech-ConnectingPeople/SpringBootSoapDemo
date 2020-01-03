package com.soapws.example.demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapws.example.demo.exception.ServiceFault;
import com.soapws.example.demo.exception.ServiceFaultException;
import com.soapws.example.demo.repo.StudentRepository;

import https.www_synctech_ga.xml.student.StudentDetailsRequest;
import https.www_synctech_ga.xml.student.StudentDetailsResponse;

/*Indicates that an annotated class is an "Endpoint" (e.g. a web service endpoint). */
@Endpoint
public class StudentEndpoint {
	
	private static final String NAMESPACE_URI = "https://www.synctech.ga/xml/student";
	
	private StudentRepository studentRepository;
	
	@Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	/*
	 * Marks an endpoint method as the handler for an incoming request. 
	 * The annotation values signify the requestpayload root element that is handled by the method.
	 * 
	 * */
	@PayloadRoot(namespace = NAMESPACE_URI , localPart = "StudentDetailsRequest")
	/*Annotation which indicates that a method return value should be bound to the response payload. 
	 * Supported for annotated endpointmethods.*/
	@ResponsePayload
	public StudentDetailsResponse getStudentDetailsResponse(@RequestPayload StudentDetailsRequest request) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		if(studentRepository.findStudent(request.getName())!=null) {
			response.setStudent(studentRepository.findStudent(request.getName()));
		}else {
			ServiceFault serviceFault = new ServiceFault("404","NOT_FOUND");
			throw new ServiceFaultException("NotFound",serviceFault);
		}
 
        return response;
	}

}
