package com.cicosy.crm.web.util;

import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.service.LeadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@Slf4j
public class ExcelHelper {

    @Autowired
    private LeadService leadService;

    public void readExcel(MultipartFile file) {

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean headerRow = true;

            for (Row row : sheet) {
                if (headerRow) {
                    headerRow = false;
                    continue; // skip header
                }

                LeadData lead = new LeadData();
                String firstName = row.getCell(0).getStringCellValue();
                String lastName = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String phonenumber = row.getCell(3).getStringCellValue();
                String companyName = row.getCell(4).getStringCellValue();
                double companySize = row.getCell(5).getNumericCellValue();
                lead.setFirstName(firstName);
                lead.setLastName(lastName);
                lead.setPhoneNumber(phonenumber);
                lead.setEmailAddress(email);
                lead.setCompany(companyName);
                lead.setCompanySize(companySize);

                leadService.createLead(lead);

            }

        } catch (Exception e) {
            e.printStackTrace();
        log.error(e.getMessage());
        }

    }
}
