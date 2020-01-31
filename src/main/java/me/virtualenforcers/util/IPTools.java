package me.virtualenforcers.util;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IPTools {
    static String IPapi = "http://ip-api.com/json/";
    static IPTools tools = new IPTools();

    public static IPTools getTools() {
        return tools;
    }

    public JSONObject getIPData(String ip){
        Document doc = null;
        try {
            doc = Jsoup.connect(IPapi+ip)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .header("Accept", "text/javascript")
                    .timeout(3000).ignoreContentType(true)
                    .get();
            JSONObject obj =  new JSONObject(doc.toString());
            return obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
