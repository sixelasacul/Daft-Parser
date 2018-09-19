package com.tre;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

class Workbook {
    private String file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private CreationHelper helper;
    private XSSFCellStyle hyperlinkStyle;
    private DateFormat dateFormat;

    Workbook(String file) {
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        try {
            this.file = file;
            File oFile = new File(this.file);
            if(!oFile.exists()) {
                System.out.println("File doesn't exist. Trying to create it...");
                if(oFile.createNewFile()) {
                    System.out.println("File successfully created.");
                } else {
                    System.out.println("File cannot be created.");
                    return;
                }
                this.workbook = new XSSFWorkbook();
                this.sheet = this.workbook.createSheet("Ads");
                this.initWorkbookOptions();
                this.writeHeaders();
            } else if(oFile.isFile()) {
                FileInputStream fis = new FileInputStream(this.file);
                this.workbook = new XSSFWorkbook(fis);
                this.sheet = workbook.getSheet("Ads");
                this.initWorkbookOptions();
            } else {
                System.out.println("Path is not pointing to a file.");
            }
        } catch(java.io.IOException e) {
            System.out.println("Excel file not found.");
            e.printStackTrace();
        }
    }

    private void createLink(Cell cell, String url) {
        XSSFHyperlink idLink = (XSSFHyperlink) this.helper.createHyperlink(HyperlinkType.URL);
        idLink.setAddress(url);
        cell.setHyperlink(idLink);
        cell.setCellStyle(this.hyperlinkStyle);
    }

    boolean AddRow(Accommodation acc) {
        if(this.isIdRegistered(acc.getId())) {
            System.out.println("Accommodation already registered.");
            return false;
        }
        int index = this.getLastRowIndex();
        try {
            Row row = this.sheet.createRow(index);

            if(acc.getId() == 0) System.out.println("No value for Id.");
            else {
                Cell idCell = row.createCell(Headers.Id.ordinal(), CellType.NUMERIC);
                idCell.setCellValue(acc.getId());
                this.createLink(idCell, String.format("https://www.daft.ie/%d", acc.getId()));
            }

            if(acc.getType().isEmpty()) System.out.println("No value for Type.");
            else row.createCell(Headers.Type.ordinal(), CellType.STRING).setCellValue(acc.getType());

            if(acc.getAddress().isEmpty()) System.out.println("No value for Address.");
            else {
                Cell addressCell = row.createCell(Headers.Address.ordinal(), CellType.STRING);
                addressCell.setCellValue(acc.getAddress());
                this.createLink(addressCell, Maps.pointLocation(acc.getAddress()));
            }

            if(acc.getDistrict().isEmpty()) System.out.println("No value for District");
            else row.createCell(Headers.District.ordinal(), CellType.STRING).setCellValue(acc.getDistrict());

            if(acc.getDistance() == 0) System.out.println("No value for Distance.");
            else {
                Cell distanceCell = row.createCell(Headers.Distance.ordinal(), CellType.NUMERIC);
                distanceCell.setCellValue(acc.getDistance());
                this.createLink(distanceCell, Maps.routeFrom(acc.getAddress()));
            }

            if(acc.getDateEntered() == null) System.out.println("No value for DateEntered.");
            else row.createCell(Headers.DateEntered.ordinal(), CellType.STRING).setCellValue(this.dateFormat.format(acc.getDateEntered()));

            if(acc.getViews() == 0) System.out.println("No value for Views.");
            else row.createCell(Headers.Views.ordinal(), CellType.NUMERIC).setCellValue(acc.getViews());

            if(acc.getMoveInDate() == null) System.out.println("No value for MoveInDate.");
            else row.createCell(Headers.MoveInDate.ordinal(), CellType.STRING).setCellValue(this.dateFormat.format(acc.getMoveInDate()));

            if(acc.getBedroom().isEmpty()) System.out.println("No value for Bedroom.");
            else row.createCell(Headers.Bedroom.ordinal(), CellType.STRING).setCellValue(acc.getBedroom());

            if(acc.getOwnerOccupied().isEmpty()) System.out.println("No value for OwnerOccupied.");
            else row.createCell(Headers.OwnerOccupied.ordinal(), CellType.STRING).setCellValue(acc.getOwnerOccupied());

            if(acc.getFlatmatesNumber() == 0) System.out.println("No value for FlatmatesNumber.");
            else row.createCell(Headers.FlatmatesNumber.ordinal(), CellType.NUMERIC).setCellValue(acc.getFlatmatesNumber());

            if(acc.getFlatmatesInfo().isEmpty()) System.out.println("No value for FlatmatesInfo.");
            else row.createCell(Headers.FlatmatesInfo.ordinal(), CellType.STRING).setCellValue(acc.getFlatmatesInfo());

            if(acc.getLookingFor().isEmpty()) System.out.println("No value for LookingFor.");
            else row.createCell(Headers.LookingFor.ordinal(), CellType.STRING).setCellValue(acc.getLookingFor());

            if(acc.getPreferences().isEmpty()) System.out.println("No value for Preferences.");
            else row.createCell(Headers.Preferences.ordinal(), CellType.STRING).setCellValue(acc.getPreferences());

            if(acc.getFacilities().isEmpty()) System.out.println("No value for Facilities.");
            else row.createCell(Headers.Facilities.ordinal(), CellType.STRING).setCellValue(acc.getFacilities());

            if(acc.getPrice() == 0) System.out.println("No value for Price.");
            else row.createCell(Headers.Price.ordinal(), CellType.NUMERIC).setCellValue(acc.getPrice());

            if(acc.getFrequency().isEmpty()) System.out.println("No value for Frequency.");
            else row.createCell(Headers.Per.ordinal(), CellType.STRING).setCellValue(acc.getFrequency());

            if(acc.getBills() == 0) System.out.println("No value for Bills.");
            else row.createCell(Headers.Bills.ordinal(), CellType.NUMERIC).setCellValue(acc.getBills());

            if(acc.getBer().isEmpty()) System.out.println("No value for Ber.");
            else row.createCell(Headers.BER.ordinal(), CellType.STRING).setCellValue(acc.getBer());

            String priceCol = CellReference.convertNumToColString(Headers.Price.ordinal());
            String billsCol = CellReference.convertNumToColString(Headers.Bills.ordinal());
            String perCol = CellReference.convertNumToColString(Headers.Per.ordinal());
            row.createCell(Headers.Total.ordinal(), CellType.FORMULA).setCellFormula(String.format("(%2$s%1$d+%3$s%1$d)*IF(%4$s%1$d = \"month\",1,4)", index+1, priceCol, billsCol, perCol));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean SaveAndClose() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(this.file));
            this.workbook.write(fos);
            fos.close();
            System.out.println("Saved and closed successfully.");
            return true;
        } catch(java.io.IOException e) {
            System.out.println("Error while saving the workbook or closing the file.");
            e.printStackTrace();
            return false;
        }
    }

    private int getLastRowIndex() {
        int i = 0;
        Iterator<Row> rows = this.sheet.rowIterator();
        while(rows.hasNext()) {
            Row row = rows.next();
            Cell cell = row.getCell(Headers.Id.ordinal());
            switch(cell.getCellType()) {
                case STRING:
                    if(cell.getStringCellValue().equals(Headers.Id.toString()) || Integer.parseInt(cell.getStringCellValue()) != 0)
                        i++;
                    break;
                case NUMERIC:
                    if((int)cell.getNumericCellValue() != 0)
                        i++;
                    break;
            }
        }
        return i;
    }

    private int getNumberOfItems() {
        int i = 0;
        Iterator<Row> rows = this.sheet.rowIterator();
        rows.next();
        while(rows.hasNext()) {
            Row row = rows.next();
            int id = (int) row.getCell(Headers.Id.ordinal()).getNumericCellValue();
            if(id != 0) i++;
        }
        return i;
    }

    private boolean isIdRegistered(int id) {
        Iterator<Row> rows = this.sheet.rowIterator();
        rows.next();
        while(rows.hasNext()) {
            int readId = (int)rows.next().getCell(Headers.Id.ordinal()).getNumericCellValue();
            if(id == readId) return true;
        }
        return false;
    }

    private void writeHeaders() {
        Row row = this.sheet.createRow(0);
        for(Headers h : Headers.values()) {
            row.createCell(h.ordinal(), CellType.STRING).setCellValue(h.toString());
        }
    }

    private void initWorkbookOptions() {
        this.helper = this.workbook.getCreationHelper();
        this.hyperlinkStyle = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setUnderline(XSSFFont.U_SINGLE);
        this.hyperlinkStyle.setFont(font);
    }
}
