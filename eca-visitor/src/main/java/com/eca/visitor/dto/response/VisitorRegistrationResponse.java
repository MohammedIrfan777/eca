package com.eca.visitor.dto.response;

import com.eca.visitor.dto.UserDTO;
import com.eca.visitor.dto.VisitorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitorRegistrationResponse{

    private String status;
    private int statusCode;

    private String statusMsg;

    @JsonProperty("data")
    private VisitorDTO responseDto;

    @JsonProperty("userData")
    private UserDTO userDTO;

    private String error;


}
