package com.cicosy.crm.web.util;

import com.cicosy.crm.data.CustomerData;
import com.cicosy.crm.data.LeadData;
import com.cicosy.crm.service.CustomerService;
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
import java.util.Date;

@Service
@Slf4j
public class ExcelHelper {

    @Autowired
    private LeadService leadService;
    @Autowired
    private CustomerService customerService;

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

    public void readCustomerExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean headerRow = true;

            for (Row row : sheet) {
                if (headerRow) {
                    headerRow = false;
                    continue; // skip header
                }
                CustomerData data = new CustomerData();
                String customerID = row.getCell(0).getStringCellValue();
                String firstName = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                String companyName = row.getCell(3).getStringCellValue();
                String city = row.getCell(4).getStringCellValue();
                String country = row.getCell(5).getStringCellValue();
                String phonenumber1 = row.getCell(6).getStringCellValue();
                String phonenumber2 = row.getCell(7).getStringCellValue();
                String emailAddress = row.getCell(8).getStringCellValue();
                Date subscriptionDate = row.getCell(9).getDateCellValue();
                data.setFirstName(firstName);
                data.setLastName(lastName);
                data.setCustomerID(customerID);
                data.setPhonenumber1(phonenumber1);
                data.setPhonenumber2(phonenumber2);
                data.setEmailAddress(emailAddress);
                data.setCompanyName(companyName);
                data.setCity(city);
                data.setCountry(country);
                //data.setSubscriptionDate(subscriptionDate);
                customerService.createCustomer(data);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
