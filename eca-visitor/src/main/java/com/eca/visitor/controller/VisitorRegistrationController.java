package com.eca.visitor.controller;

import com.eca.visitor.dto.VisitorDTO;
import com.eca.visitor.dto.response.VisitorRegistrationResponse;
import com.eca.visitor.service.VisitorRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/visitor")
@CrossOrigin
@Slf4j
public class VisitorRegistrationController {

    @Autowired
    private VisitorRegistrationService visitorRegistrationService;

    @PostMapping(path="registration",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitorRegistrationResponse> registration(@RequestBody VisitorDTO visitorDto) {
        log.info("Visitor Registration Controller");
        return visitorRegistrationService.visitorRegistration(visitorDto);

    }


    @GetMapping("/checkApprovalStatus/{requestId}")
    public ResponseEntity<String> checkApprovalStatus(@PathVariable Long requestId)  {
        log.info("Checking Visitor Approval Status");
        //visitorRegistrationService.checkApprovalStatus(requestId);
        return ResponseEntity.ok("visitor request has been approved");


    }

}
