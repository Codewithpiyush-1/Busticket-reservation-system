package com.reservationapp.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.reservationapp.Entity.Passenger;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfTicketGeneratorService {
    public byte[] generateTicket(Passenger passenger,String fromLocation,String toLocation,String fromDate,String toDate){
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        Document document= new Document();
        try{
            PdfWriter.getInstance(document,outputStream);
            document.open();
            document.add(new Paragraph("Ticket Information"));
            document.add(new Paragraph("Name"+passenger.getFirstName()+" "+passenger.getLastName()));
            document.add(new Paragraph("Email:"+passenger.getEmail()));
            document.add(new Paragraph("Mobile:"+passenger.getMobile()));
            document.add(new Paragraph("Bus ID"+passenger.getBusId()));
            document.add(new Paragraph("Route ID"+passenger.getRouteId()));
            document.add((new Paragraph("FromLocation"+fromLocation)));
            document.add((new Paragraph("ToLocation"+toLocation)));
            document.add((new Paragraph("FromDate"+fromDate)));
            document.add((new Paragraph("ToDate"+toDate)));
            document.close();
        } catch (DocumentException e) {
          e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

}
