package com.pdf.service;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.pdf.entity.Transaction;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public ByteArrayInputStream generatePdf(String data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.add(new Paragraph(data).setFont(font).setFontColor(ColorConstants.BLACK));

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generatePdf(Transaction data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        document.add(new Paragraph(data.toString()).setFont(font).setFontColor(ColorConstants.BLACK));

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}