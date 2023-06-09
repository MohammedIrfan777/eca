package com.eca.visitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private String firstName;
    private String lastName;

    private String userFirstName;
    private String userLastName;

    private String userEmailId;
    @NotEmpty
    private String emailId;
    private int apartmentId;
    private String apartmentName;


}
