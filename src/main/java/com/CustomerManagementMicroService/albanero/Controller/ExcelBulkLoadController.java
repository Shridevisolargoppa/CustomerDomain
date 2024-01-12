package com.CustomerManagementMicroService.albanero.Controller;

import com.CustomerManagementMicroService.albanero.Service.ExcelBulkLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class ExcelBulkLoadController {

    private final ExcelBulkLoadService excelBulkLoadService;

    @Autowired
    public ExcelBulkLoadController(ExcelBulkLoadService excelBulkLoadService) {
        this.excelBulkLoadService = excelBulkLoadService;
    }

    @PostMapping("/api/excelBulkLoad")
    public ResponseEntity<String> excelBulkLoad(@RequestPart("file") MultipartFile excelFile) {
        try {
            excelBulkLoadService.bulkLoadFromExcel(excelFile.getInputStream());
            return ResponseEntity.ok("Excel bulk load completed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Error processing Excel file: " + e.getMessage());
        }
    }
    @PutMapping("/excelBulkUpdate")
    public ResponseEntity<String> excelBulkUpdate(@RequestPart("file") MultipartFile excelFile) {
        try {
            excelBulkLoadService.bulkUpdateFromExcel(excelFile.getInputStream());
            return ResponseEntity.ok("Excel bulk update completed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(400).body("Error processing Excel file: " + e.getMessage());
        }
    }
}

