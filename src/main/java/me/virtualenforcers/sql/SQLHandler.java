package me.virtualenforcers.sql;

import java.sql.*;

public class SQLHandler {
   public final static int NOT_FOUND = 3;
   public final static int ERROR = 4;
   public final static int ALLOWED = 2;
   public final static int DISALLOWED = 1;

    static SQLHandler sql = new SQLHandler();
    public SQLHandler(){

        Connection con = getConnection();
        try {
            PreparedStatement table1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS Company (name varchar(30),result int)");
            PreparedStatement table2 = con.prepareStatement("CREATE TABLE IF NOT EXISTS ASN (asn varchar(30), result int)");
            PreparedStatement table3 = con.prepareStatement("CREATE TABLE IF NOT EXISTS TORnodes (node varchar(30))");
            table1.executeUpdate();
            table2.executeUpdate();
            table3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static SQLHandler getSql() {
        return sql;
    }
    static String ip = "localhost";
    static  String port = "3306";
    static String dbName = "Enforcer";
    static String userName = "enforcerAPI";
    static String password = "19BCG10015";
    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc://"+ip+":"+port+"/"+dbName,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public int getASNData(String ASN,Connection con){
        try {
            PreparedStatement query = con.prepareStatement("SELECT result FROM ASN where asn = ? LIMIT 0,1");
            query.setString(1,ASN);
            ResultSet res = query.executeQuery();
            if(res.next()){
                int result = res.getInt(1);
                return result;
            }else{
                return NOT_FOUND;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public int getCompanyData(String name,Connection con){
        try {
            PreparedStatement query = con.prepareStatement("SELECT result FROM Company where name = ? LIMIT 0,1");
            query.setString(1,name);
            ResultSet res = query.executeQuery();
            if(res.next()){
                int result = res.getInt(1);
                return result;
            }else{
                return NOT_FOUND;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ERROR;
    }



    public boolean isTorNode(String IP,Connection con){
        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM TORnodes where node = ? LIMIT 0,1");
            query.setString(1,IP);
            ResultSet res = query.executeQuery();
            if(res.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
