package com.cicosy.crm.api;

import com.cicosy.crm.api.data.CustomerRegistrationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Customer Relationship Management API", description = "Manages customer relations")
public class CustomerRegistrationController {


    @PostMapping("/save-customer")
    @Operation(summary = "Create a new  Customer in CRM", description = "Creates a new customer in CRM")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    public ResponseEntity saveCustomer(@RequestBody CustomerRegistrationData customerRegistrationData, Model model) {

      return ResponseEntity.ok().build();

    }

}
