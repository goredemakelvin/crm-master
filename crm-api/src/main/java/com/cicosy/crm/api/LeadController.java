package com.cicosy.crm.api;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.entity.Lead;
import com.cicosy.crm.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Customer Relationship Management API", description = "Manages customer relations")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping("/save-lead")
    @Operation(summary = "Create a new  Lead in CRM", description = "Creates a new Lead in CRM")
    @ApiResponse(responseCode = "201", description = "Lead created successfully")
    public ResponseEntity saveLead(@RequestBody LeadData leadData, Model model) {

        Lead lead = leadService.createLead(leadData);

        return ResponseEntity.ok().body(lead);

    }

}
