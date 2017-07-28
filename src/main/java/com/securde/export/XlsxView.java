package com.securde.export;

import com.securde.model.reservation.RoomReservation;
import com.securde.model.reservation.TextReservation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Map;

public class XlsxView extends AbstractXlsxView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"status-reservations.xlsx\"");

        @SuppressWarnings("unchecked")
        ArrayList<TextReservation> textReservations = (ArrayList<TextReservation>) model.get("textReservations");
        @SuppressWarnings("unchecked")
        ArrayList<RoomReservation> roomReservations = (ArrayList<RoomReservation>) model.get("roomReservations");

        generateTextSheet(workbook, textReservations);
        generateRoomSheet(workbook, roomReservations);
    }

    private void generateTextSheet(Workbook workbook, ArrayList<TextReservation> textReservations) {
        Sheet textSheet = workbook.createSheet("Text Reservations");
        textSheet.setDefaultColumnWidth(30);

        Row header = textSheet.createRow(0);
        header.createCell(0).setCellValue("Text Title");
        header.createCell(1).setCellValue("Reserved By");
        header.createCell(2).setCellValue("Reservation Start Date");
        header.createCell(3).setCellValue("Reservation End Date");

        int rowCount = 1;

        for (TextReservation textReservation : textReservations) {
            Row row = textSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(textReservation.getText().getTitle());
            row.createCell(1).setCellValue(textReservation.getUser().getIdNumber());
            row.createCell(2).setCellValue(textReservation.getReservationStartDate());
            row.createCell(3).setCellValue(textReservation.getReservationEndDate());
        }

    }

    private void generateRoomSheet(Workbook workbook, ArrayList<RoomReservation> roomReservations) {
        Sheet roomSheet = workbook.createSheet("Room Reservations");
        roomSheet.setDefaultColumnWidth(30);
        Row header = roomSheet.createRow(0);
        header.createCell(0).setCellValue("Room Name");
        header.createCell(1).setCellValue("Reserved By");
        header.createCell(2).setCellValue("Reservation Date");
        header.createCell(3).setCellValue("Reservation Start Time");
        header.createCell(4).setCellValue("Reservation End Time");

        int rowCount = 1;

        for (RoomReservation roomReservation : roomReservations) {
            Row row = roomSheet.createRow(rowCount++);

            row.createCell(0).setCellValue(roomReservation.getRoom().getName());
            row.createCell(1).setCellValue(roomReservation.getUser().getIdNumber());
            row.createCell(2).setCellValue(roomReservation.getReservationDate());
            row.createCell(3).setCellValue(roomReservation.getReservationStartTime());
            row.createCell(4).setCellValue(roomReservation.getReservationEndTime());
        }
    }
}
