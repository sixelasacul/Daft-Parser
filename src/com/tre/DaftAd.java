package com.tre;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.tre.Utils.getHtml;
import static com.tre.Utils.parseHtml;
import static com.tre.Utils.transformUrl;


public class DaftAd {
    private String Link;
    private URL Url;
    private Document Document;

    public String getLink() {
        return Link;
    }
    public boolean setLink(String link) {
        Link = link;
        URL url = transformUrl(Link);
        if(url == null) return false;
        else {
            return setUri(url);
        }
    }
    public URL getUri() {
        return Url;
    }
    public boolean setUri(URL uri) {
        Url = uri;
        if(!isValidDaftUrl(Url)) return false;
        else {
            setDocument(parseHtml(getHtml(Url)));
            return true;
        }
    }
    public Document getDocument() {
        return Document;
    }
    public void setDocument(Document document) {
        Document = document;
    }

    public int getId() {
        //return Integer.parseInt(getDocument().selectFirst(".description_extras a").html().substring(20));
        int id = 0;
        Elements as = getDocument().select("#description .description_extras a");
        for(Element a : as) {
            String href = a.attr("href");
            boolean match = href.contains("daft.ie");
            if(match) {
                String html = a.html();
                String ids = html.substring(20);
                id = Integer.parseInt(ids);
            }
        }
        return id;
    }

    public Date getDate() {
        String date = getDocument().selectFirst("#description .description_extras").html().split("<h3>Date Entered/Renewed:</h3>")[1].split("<h3>Property Views:</h3>")[0].split(" ")[0];
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        try {
            return format.parse(date);
        } catch(java.text.ParseException e) {
            return null;
        }
    }

    public int getViews() {
        Element extra = getDocument().selectFirst("#description .description_extras");
        if(extra != null) return Integer.parseInt(extra.html().split("<h3>Property Views:</h3>")[1].replace(",", "").trim());
        else return 0;
    }

    public String getAddress() {
        Element address = getDocument().selectFirst("#address_box h1");
        if(address != null) return address.ownText();
        else return "";
    }

    public int getPrice() {
        Element price = getDocument().selectFirst("#smi-price-string");
        if(price != null) return Integer.parseInt(price.ownText().split(" ")[0].split("€")[1]);
        else return 0;
    }

    public String getFrequency() {
        Element frequency = getDocument().selectFirst("#smi-price-string");
        if(frequency != null) return frequency.ownText().split(" ")[2];
        else return "";
    }

    public String getType() {
        Element type = getDocument().selectFirst("#smi-summary-items span.header_text");
        if(type != null) return type.ownText().split(" ")[0];
        else return "";
    }

    public String getBedroom() {
        Elements list = getDocument().select("#overview li");
        for(Element item : list) {
            if(item.html().contains("€")) {
                return item.html().split("€")[0].trim();
            }
        }
        return "";
    }

    public int getFlatmatesNumber() {
        Elements list = getDocument().select("#overview li");
        for(Element item : list) {
            if(item.html().contains("currently living")) {
                return Integer.parseInt(item.html().split(" ")[0]);
            }
        }
        return 0;
    }

    public String getLookingFor() {
        Elements list = getDocument().select("#overview li");
        for(Element item : list) {
            if(item.html().contains("Looking for")) {
                return item.html().split("Looking for")[1].trim();
            }
        }
        return "";
    }

    public String getOwnerOccupied() {
        Elements list = getDocument().select("#overview li");
        for(Element item : list) {
            if(item.html().contains("owner occupied")) {
                if(item.html().contains("not")) return "No";
                else return "Yes";
            }
        }
        return "";
    }

    public String getFacilities() {
        String facilities = "";
        Elements list = getDocument().select("#facilities li");
        int i = list.size();
        for(Element item : list) {
            facilities = facilities.concat(item.ownText());
            if(--i > 0) facilities = facilities.concat(", ");
        }
        return facilities;
    }

    public String getBer() {
        Element ber = getDocument().selectFirst("#smi-ber-details img");
        if(ber == null) return "";
        String temp = ber.attr("alt").split(" ")[1];
        if(temp.matches("exempt")) return "exempt";
        else return temp;
    }

    private boolean isValidDaftUrl(URL url) {
        String host = url.getHost();
        String domain = host.startsWith("www.") ? host.substring(4) : host;
        if(!domain.equals("daft.ie")) return false;
        String path = url.getPath();
        return path.matches("(/)\\d{7,8}");
    }
}
