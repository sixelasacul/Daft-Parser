package com.tre;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Workbook {
    private Hashtable<String, Integer> headers;
    private String file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private CreationHelper helper;
    private XSSFCellStyle hyperlinkStyle;

    public Workbook(String file) {
        this.initHeaders();
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

    public boolean AddRow(Accommodation acc) {
        if(this.isIdRegistered(acc.getId())) {
            System.out.println("Accommodation already registered.");
            return false;
        }
        int index = this.getLastRowIndex();
        try {
            Row row = this.sheet.createRow(index);

            if(acc.getId() == 0) System.out.println("No value for Id.");
            Cell idCell = row.createCell(this.headers.get("Id"), CellType.NUMERIC);
            idCell.setCellValue(acc.getId());
            this.createLink(idCell, String.format("https://www.daft.ie/%d", acc.getId()));

            if(acc.getType().isEmpty()) System.out.println("No value for Type.");
            row.createCell(this.headers.get("Type"), CellType.STRING).setCellValue(acc.getType());

            if(acc.getAddress().isEmpty()) System.out.println("No value for Address.");
            Cell addressCell = row.createCell(this.headers.get("Address"), CellType.STRING);
            addressCell.setCellValue(acc.getAddress());
            this.createLink(addressCell, Maps.pointLocation(acc.getAddress()));

            if(acc.getDistance() == 0) System.out.println("No value for Distance.");
            Cell distanceCell = row.createCell(this.headers.get("Distance from work (m)"), CellType.NUMERIC);
            distanceCell.setCellValue(acc.getDistance());
            this.createLink(distanceCell, Maps.routeFrom(acc.getAddress()));

            if(acc.getDateEntered() == null) System.out.println("No value for DateEntered.");
            row.createCell(this.headers.get("Date entered"), CellType.STRING).setCellValue(acc.getDateEntered());

            if(acc.getViews() == 0) System.out.println("No value for Views.");
            row.createCell(this.headers.get("Views"), CellType.NUMERIC).setCellValue(acc.getViews());

            if(acc.getBedroom().isEmpty()) System.out.println("No value for Bedroom.");
            row.createCell(this.headers.get("Bedroom"), CellType.STRING).setCellValue(acc.getBedroom());

            if(acc.getOwnerOccupied().isEmpty()) System.out.println("No value for OwnerOccupied.");
            row.createCell(this.headers.get("Owned occupied"), CellType.STRING).setCellValue(acc.getOwnerOccupied());

            if(acc.getFlatmatesNumber() == 0) System.out.println("No value for FlatmatesNumber.");
            row.createCell(this.headers.get("Flatmates number"), CellType.NUMERIC).setCellValue(acc.getFlatmatesNumber());

            if(acc.getFlatmatesInfo().isEmpty()) System.out.println("No value for FlatmatesInfo.");
            row.createCell(this.headers.get("Flatmates info"), CellType.STRING).setCellValue(acc.getFlatmatesInfo());

            if(acc.getLookingFor().isEmpty()) System.out.println("No value for LookingFor.");
            row.createCell(this.headers.get("Looking for"), CellType.STRING).setCellValue(acc.getLookingFor());

            if(acc.getPreferences().isEmpty()) System.out.println("No value for Preferences.");
            row.createCell(this.headers.get("Preferences"), CellType.STRING).setCellValue(acc.getPreferences());

            if(acc.getFacilities().isEmpty()) System.out.println("No value for Facilities.");
            row.createCell(this.headers.get("Facilities"), CellType.STRING).setCellValue(acc.getFacilities());

            if(acc.getPrice() == 0) System.out.println("No value for Price.");
            row.createCell(this.headers.get("Price"), CellType.NUMERIC).setCellValue(acc.getPrice());

            if(acc.getFrequency().isEmpty()) System.out.println("No value for Frequency.");
            row.createCell(this.headers.get("Per"), CellType.STRING).setCellValue(acc.getFrequency());

            if(acc.getBills() == 0) System.out.println("No value for Bills.");
            row.createCell(this.headers.get("Bills"), CellType.NUMERIC).setCellValue(acc.getBills());

            if(acc.getBer().isEmpty()) System.out.println("No value for Ber.");
            row.createCell(this.headers.get("BER"), CellType.STRING).setCellValue(acc.getBer());

            row.createCell(this.headers.get("Total/Month"), CellType.FORMULA).setCellFormula(String.format("(N%1$d+P%1$d)*IF(O%1$d = \"month\",1,4)", index+1));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean SaveAndClose() {
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
            Cell cell = row.getCell(this.headers.get("Id"));
            switch(cell.getCellType()) {
                case STRING:
                    if(cell.getStringCellValue().equals("Id") || Integer.parseInt(cell.getStringCellValue()) != 0)
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
            int id = (int) row.getCell(this.headers.get("Id")).getNumericCellValue();
            if(id != 0) i++;
        }
        return i;
    }

    private boolean isIdRegistered(int id) {
        Iterator<Row> rows = this.sheet.rowIterator();
        rows.next();
        while(rows.hasNext()) {
            int readId = (int)rows.next().getCell(this.headers.get("Id")).getNumericCellValue();
            if(id == readId) return true;
        }
        return false;
    }

    private void initHeaders() {
        this.headers = new Hashtable<>();
        this.headers.put("Id", 0);
        this.headers.put("Type", 1);
        this.headers.put("Address", 2);
        this.headers.put("Distance from work (m)", 3);
        this.headers.put("Date entered", 4);
        this.headers.put("Views", 5);
        this.headers.put("Bedroom", 6);
        this.headers.put("Owned occupied", 7);
        this.headers.put("Flatmates number", 8);
        this.headers.put("Flatmates info", 9);
        this.headers.put("Looking for", 10);
        this.headers.put("Preferences", 11);
        this.headers.put("Facilities", 12);
        this.headers.put("Price", 13);
        this.headers.put("Per", 14);
        this.headers.put("Bills", 15);
        this.headers.put("BER", 16);
        this.headers.put("Total/Month", 17);
        this.headers.put("Notes", 18);
    }

    private void writeHeaders() {
        Row row = this.sheet.createRow(0);
        Set<String> keys = this.headers.keySet();
        for(String key : keys) {
            row.createCell(this.headers.get(key), CellType.STRING).setCellValue(key);
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
