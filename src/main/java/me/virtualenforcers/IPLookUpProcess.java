package me.virtualenforcers;

import me.virtualenforcers.sql.SQLHandler;
import me.virtualenforcers.util.Cacher;
import me.virtualenforcers.util.IPTools;
import org.json.JSONObject;

import java.sql.Connection;

public class IPLookUpProcess {
    String ip;
    boolean portScan = false;
    IPResultSet result;
    public IPLookUpProcess(String ip,boolean portScan){
        this.portScan = portScan;
        this.ip = ip;
        result = new IPResultSet(ip);
    }


    public void start(){
        JSONObject ipData = IPTools.getTools().getIPData(ip);
        String AS = ipData.getString("as");


        String ASN = AS.split( " ")[0].replace("AS","");
        String ISP = ipData.getString("isp");
        Connection con = SQLHandler.getSql().getConnection();

        boolean tor = SQLHandler.getSql().isTorNode(ip,con); //TODO:: BUNTY

        //Company CHECKS
        int companyResult = SQLHandler.getSql().getCompanyData(ISP,con);
        if(companyResult == SQLHandler.NOT_FOUND){
            //DO COMPANY CHECK  //TODO:: ANFAAS
            //SAVE TO DB//TODO::ME
        }

        //ASN check
        int asnResult = SQLHandler.getSql().getASNData(ASN,con);
        if(asnResult == SQLHandler.NOT_FOUND){
            //NOT IN BLACKLIST // TODO::BUNTY
        }



        if(!portScan)return;

        //Do Port Scan//TODO::ME


    }


    public IPResultSet getResult(){
        return result;
    }
}
