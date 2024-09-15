package com.pdf.controller;

import com.pdf.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/generate-pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestParam("data") String data) {
        try {
            ByteArrayInputStream bis = pdfService.generatePdf(data);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=generated.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}