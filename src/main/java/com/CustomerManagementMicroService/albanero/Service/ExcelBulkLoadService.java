package com.CustomerManagementMicroService.albanero.Service;

import com.CustomerManagementMicroService.albanero.Domain.CustomerDomain;
import com.CustomerManagementMicroService.albanero.Repository.CustomerDomainRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ExcelBulkLoadService {

    private final CustomerDomainRepository customerRepository;

    @Autowired
    public ExcelBulkLoadService(CustomerDomainRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void bulkLoadFromExcel(InputStream inputStream) throws IOException {
        List<CustomerDomain> customerDomainList = readExcelData(inputStream);
        customerRepository.saveAll(customerDomainList);
    }

    @Transactional
    public void bulkUpdateFromExcel(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Long customerId = (long) row.getCell(0).getNumericCellValue();
                Optional<CustomerDomain> existingCustomerOptional = customerRepository.findById(customerId);

                if (existingCustomerOptional.isPresent()) {
                    CustomerDomain existingCustomer = existingCustomerOptional.get();
                    existingCustomer.setName(row.getCell(1).getStringCellValue());
                    existingCustomer.setEmail(row.getCell(2).getStringCellValue());
                    existingCustomer.setPhone((long) row.getCell(3).getNumericCellValue());
                    existingCustomer.setAddress(row.getCell(4).getStringCellValue());
                    existingCustomer.setCompanyName(row.getCell(5).getStringCellValue());
                    existingCustomer.setIndustryType(row.getCell(6).getStringCellValue());
                    existingCustomer.setCustomerStatus(row.getCell(7).getStringCellValue());
                    existingCustomer.setAccountManager(row.getCell(8).getStringCellValue());

                    existingCustomer.setAudit(LocalDateTime.now());

                    customerRepository.save(existingCustomer);
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<CustomerDomain> readExcelData(InputStream inputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        List<CustomerDomain> customerDomainList = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            CustomerDomain entity = new CustomerDomain();
            entity.setName(row.getCell(0).getStringCellValue());
            entity.setEmail(row.getCell(1).getStringCellValue());
            entity.setPhone((long) row.getCell(2).getNumericCellValue());
            entity.setAddress(row.getCell(3).getStringCellValue());
            entity.setCompanyName(row.getCell(4).getStringCellValue());
            entity.setIndustryType(row.getCell(5).getStringCellValue());
            entity.setCustomerStatus(row.getCell(6).getStringCellValue());
            entity.setAccountManager(row.getCell(7).getStringCellValue());
            entity.setAudit(LocalDateTime.now());
            customerDomainList.add(entity);
        }

        workbook.close();
        return customerDomainList;
    }
}
