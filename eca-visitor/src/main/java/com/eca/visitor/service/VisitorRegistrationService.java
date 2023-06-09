package com.eca.visitor.service;

import com.eca.visitor.dto.VisitorDTO;
import com.eca.visitor.dto.response.VisitorRegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface VisitorRegistrationService {

    ResponseEntity<VisitorRegistrationResponse> visitorRegistration(VisitorDTO visitorDto) ;
}
