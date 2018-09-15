package com.tre;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Utils {
    public static URL transformUrl(String link) {
        try {
            return new URL(link);
        } catch(java.net.MalformedURLException e) {
            return null;
        }
    }

    public static Document parseHtml(String html) {
        return Jsoup.parse(html);
    }

    public static String getHtml(URL url) {
        String content = null;
        try {
            URLConnection cnx = url.openConnection();
            Scanner s = new Scanner(cnx.getInputStream());
            s.useDelimiter("\\Z");
            content = s.next();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
