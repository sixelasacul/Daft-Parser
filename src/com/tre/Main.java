package com.tre;

import java.util.Scanner;

public class Main {

    //args[0] daft url
    //?args[1] excel file
    public static void main(String[] args) {
        String link = "";
        String path = "";
	    if(args.length == 0) {
	        Scanner s = new Scanner(System.in);
	        System.out.println(String.format("Enter Daft.ie url (%s):", link));
	        link = s.nextLine();
            System.out.println(String.format("Path to Excel file (%s):", path));
            path = s.nextLine();
        } else {
            link = args[0];
            path = args[1];
        }

        DaftAd ad = new DaftAd();

        if(!ad.setLink(link)) {
            System.out.println("Invalid link or daft.ie url");
            System.exit(0);
            //Reprendre au début du programme
        }

        System.out.println("Parsing...");

        Accommodation acc = new Accommodation();
        acc.setId(ad.getId());
        acc.setAddress(ad.getAddress());
        acc.setBedroom(ad.getBedroom());
        acc.setBer(ad.getBer());
        //acc.setBills();
        acc.setDateEntered(ad.getDate());
        acc.setDistance(Maps.getDistance(acc.getAddress()));
        acc.setFacilities(ad.getFacilities());
        //acc.setFlatmatesInfo();
        acc.setFlatmatesNumber(ad.getFlatmatesNumber());
        acc.setFrequency(ad.getFrequency());
        acc.setLookingFor(ad.getLookingFor());
        acc.setOwnerOccupied(ad.getOwnerOccupied());
        //acc.setPreferences();
        acc.setPrice(ad.getPrice());
        acc.setType(ad.getType());
        acc.setViews(ad.getViews());

        Workbook wb = new Workbook(path);
        System.out.println(String.format("Adding new row: %s", wb.AddRow(acc)));
        System.out.println(String.format("Saving Excel file: %s", wb.SaveAndClose()));
    }
}
