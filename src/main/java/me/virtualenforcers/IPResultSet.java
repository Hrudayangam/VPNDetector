package me.virtualenforcers;

import org.json.JSONObject;

public class IPResultSet {
    String ip;
    int isTor;
    int ISPCache;
    int ASNBlackListed;
    int portScan;
    public IPResultSet(String ip){
        this.ip = ip;
    }
    /**
     * ResultCodes::
     * 0: Incomplete
     * 1: Success
     * 2: Fail
     * 3: Error
     * 4: Not-Used
     */
    public final static int INCOMPLETE = 0;
    public final static int SUCCESS = 1;
    public final static int FAIL = 2;
    public final static int ERROR = 3;
    public final static int UNUSED = 4;

    public void setASNBlackListed(int ASNBlackListed) {
        this.ASNBlackListed = ASNBlackListed;
    }



    public void setIsTor(int isTor) {
        this.isTor = isTor;
    }

    public void setISPCache(int ISPCache) {
        this.ISPCache = ISPCache;
    }

    public void setPortScan(int portScan) {
        this.portScan = portScan;
    }


    public String toJSON(){
        return JSONObject.valueToString(this);
    }
}
