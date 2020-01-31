package me.virtualenforcers;

import me.virtualenforcers.util.IPTools;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class RestfulAPI {
    public static void main(String[] args) {
        Spark.init();
        Spark.get("/scan", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                String ip = request.queryParams("ip");
                String b = request.queryParamOrDefault("fullcheck","false");
                response.status(202);
                IPLookUpProcess lookup = new IPLookUpProcess(ip,!b.equals("false"));
                lookup.start();

                return "test";
            }
        });
    }
}
