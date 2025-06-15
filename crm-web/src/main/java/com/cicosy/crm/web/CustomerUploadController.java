package com.cicosy.crm.web;

import com.cicosy.crm.web.util.CsvService;
import com.cicosy.crm.web.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CustomerUploadController {

    @Autowired
    private ExcelHelper excelHelper;
    @Autowired
    private CsvService csvService;


    @GetMapping("/customer-upload")
    public String showUploadForm() {
        return "customer-upload.html";
    }

    @PostMapping("/customer-upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) {

        csvService.parseCustomerData(file);

        return "redirect:/";
    }
}


