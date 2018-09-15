package com.tre;

import org.xml.sax.InputSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class Maps {
    //Faire en sorte de masquer DESTINATION et KEY automatiquement au commit vers git
    //Ou faire en sorte que la source ne soit pas dans le code directement
    private final static String DESTINATION = "WORK_ADDRESS";
    private final static String KEY = "GOOGLE_DISTANCE_MATRIX_API_LEY";
    private final static String MODE = "walking";
    private final static String UNITS = "metric";
    private final static String RETURN = "xml";

    static String pointLocation(String address) {
        try {
            return String.format("https://www.google.com/maps/search/?api=1&query=%s", URLEncoder.encode(address, "UTF-8"));
        } catch(java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    static String routeFrom(String address) {
        try {
            return String.format("https://www.google.com/maps/dir/?api=1&travelmode=%s&destination=%s&origin=%s", MODE, URLEncoder.encode(DESTINATION, "UTF-8"), URLEncoder.encode(address, "UTF-8"));
        } catch(java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    static float getDistance(String address) {
        float distance = 0;
        try {
            String query = String.format("https://maps.googleapis.com/maps/api/distancematrix/%s", RETURN);
            //Bad idea, write parameters in DataOutputStream
            String parameters = String.format("?key=%s", KEY) +
                    String.format("&units=%s", UNITS) +
                    String.format("&mode=%s", MODE) +
                    String.format("&origins=%s", URLEncoder.encode(address, "UTF-8")) +
                    String.format("&destinations=%s", URLEncoder.encode(DESTINATION, "UTF-8"));
            URL request = new URL(query.concat(parameters));
            HttpURLConnection cnx = (HttpURLConnection)request.openConnection();
            cnx.setRequestMethod("GET");
            cnx.setDoOutput(true);
            cnx.setRequestProperty("Content-Type", String.format("application/%s", RETURN));
            DataOutputStream dos = new DataOutputStream(cnx.getOutputStream());
            dos.flush();
            dos.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while((line = br.readLine()) != null) {
                content.append(line);
            }
            br.close();
            cnx.disconnect();

            InputSource is = new InputSource(new StringReader(content.toString()));
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            XPathExpression expr = xpath.compile("//distance/text");
            String resp = expr.evaluate(is).split(" km")[0];
            distance = Float.parseFloat(resp);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return distance;
    }
}
