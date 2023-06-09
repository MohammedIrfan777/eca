package com.eca.visitor.service.impl;

import com.eca.visitor.constants.VisitorConstants;
import com.eca.visitor.dto.*;
import com.eca.visitor.entity.Visitor;
import com.eca.visitor.exception.VisitorManagementException;
import com.eca.visitor.repository.VisitorRepository;
import com.eca.visitor.dto.response.VisitorRegistrationResponse;
import com.eca.visitor.service.VisitorRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utils.CommonUtils;
import utils.JsonUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class VisitorRegistrationServiceImpl implements VisitorRegistrationService {

    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private Validator validator;

    @Autowired
    private JsonUtils jsonUtils;

    @Value("${microservice.user-service.endpoints.endpoint.uri}")
    private String endpointUrl;

    @Value("${app.user-mgmt.authTkn}")
    private String usermgmtToken;

    @Value("${app.visitor.kafka.enabled}")
    private boolean kafkaEnabled;

    @Override
    public ResponseEntity<VisitorRegistrationResponse> visitorRegistration(VisitorDTO requestDto)  {

        Visitor visitorEntity = requestToEntity(requestDto);
        Set<ConstraintViolation<Visitor>> validate = validator.validate(visitorEntity);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }

        VisitorDTO visitorDto = toVisitorDto(visitorEntity);
        var userDto = getUserDetailsForVisitor(visitorEntity.getUserPhoneNo());
        var visitorToSave = parseUserDetailsForvisitor(visitorEntity,userDto);
        try {
            visitorRepository.save(visitorToSave);
        } catch (Exception e){
            throw new VisitorManagementException("Error occurred during saving visitor details into DB");
        }
        if (kafkaEnabled) {
            var visitorkafkaMessageDto = commonUtils.createVisitorkafkaMessageDto(visitorToSave);
            commonUtils.pushToKafka(visitorkafkaMessageDto);
        }
        return toVisitorRegistrationResponse(visitorDto, userDto, HttpStatus.CREATED);
    }

    private Visitor parseUserDetailsForvisitor(Visitor visitorEntity, UserDTO userDto) {
        visitorEntity.setUserFirstName(userDto.getUserFirstName());
        visitorEntity.setUserLastName(userDto.getUserLastName());
        visitorEntity.setApartmentId(userDto.getApartmentId());
        visitorEntity.setApartmentName(userDto.getApartmentName());
        visitorEntity.setUserEmailId(userDto.getUserEmailId());
        return visitorEntity;
    }


    private UserDTO getUserDetailsForVisitor(Long userPhoneNo) {

        HttpEntity<String> entity = new HttpEntity<>(getHttpHeaders());

            ResponseEntity<UserRespDTO> resp = restTemplate.exchange(UriComponentsBuilder.fromHttpUrl(endpointUrl)
                    .buildAndExpand(userPhoneNo)
                    .toUriString(), HttpMethod.GET, entity, UserRespDTO.class);

            var userResponse = resp.getBody();
            log.info("user response is...{}", userResponse);
            if (null != userResponse.getData() && !userResponse.getData().isEmpty()) {
                var userdata = userResponse.getData().get(0);
                if (userdata.getOwner() != null) {
                    return commonUtils.parseOwnerDetails(userdata.getOwner());
                } else if (userdata.getVendor() != null) {
                    return commonUtils.parseOwnerDetails(userdata.getOwner());
                } else if (userdata.getTenant() != null) {
                    return commonUtils.parseOwnerDetails(userdata.getOwner());
                } else {
                    throw new VisitorManagementException(VisitorConstants.USER_DETAILS_FOR_VISITOR_NOT_FOUND);
                }
            } else throw new VisitorManagementException(VisitorConstants.USER_DETAILS_FOR_VISITOR_NOT_FOUND);
        }


    private Visitor requestToEntity(VisitorDTO requestDto) {
        return modelMapper.map(requestDto, Visitor.class);
    }

    private VisitorDTO toVisitorDto(Visitor entity) {
        return modelMapper.map(entity, VisitorDTO.class);
    }

    private ResponseEntity<VisitorRegistrationResponse> toVisitorRegistrationResponse (VisitorDTO visitorDto, UserDTO userDTO, HttpStatus httpStatus){
            var visitorRegistrationResponse = new VisitorRegistrationResponse();
            visitorRegistrationResponse.setResponseDto(visitorDto);
            visitorRegistrationResponse.setUserDTO(userDTO);
            return new ResponseEntity<>(visitorRegistrationResponse, httpStatus);
        }

    private HttpHeaders getHttpHeaders() {

        // Create HttpHeaders and set the authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", org.apache.commons.lang3.StringUtils.join("Bearer ", usermgmtToken));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}

