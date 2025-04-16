package com.cicosy.crm.web;

import com.cicosy.crm.web.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LeadUploadController {

    @Autowired
    private ExcelHelper excelHelper;


    @GetMapping("/lead-upload")
    public String showUploadForm() {
        return "lead-upload.html";
    }

    @PostMapping("/lead-upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) {

        excelHelper.readExcel(file);

        return "redirect:/";
    }
}


