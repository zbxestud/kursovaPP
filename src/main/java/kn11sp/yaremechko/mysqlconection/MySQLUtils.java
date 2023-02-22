package kn11sp.yaremechko.mysqlconection;
import kn11sp.yaremechko.insurances.BaseInsurance;
import kn11sp.yaremechko.derivative.Derivative;
import kn11sp.yaremechko.insurances.HealthInsurance;
import kn11sp.yaremechko.insurances.RealEstateInsurance;
import kn11sp.yaremechko.insurances.TransportInsurance;

import java.sql.*;
import java.util.ArrayList;

public class MySQLUtils {
    public int getDerivativesAmount (){
        int derivativesAmount = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kursova","root","davil2332");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select derivativeID from insurances group by derivativeID");
            while(rs.next())
                derivativesAmount++;
            con.close();
        }catch(Exception e){ System.out.println(e);}
        return derivativesAmount;
    }

    public void getData (ArrayList<Derivative> derivatives, ArrayList<ArrayList<BaseInsurance>> insurances){
        derivatives.clear();
        insurances.clear();
        String type = new String();
        int derivativesAmount = getDerivativesAmount();
        for (int i = 1; i<=derivativesAmount;i++){
            insurances.add(new ArrayList<BaseInsurance>());
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kursova","root","davil2332");
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from insurances where derivativeID = "+i);
                while(rs.next()) {
                    type = rs.getString("insuranceType");
                    switch (type) {
                        case "Health":
                            insurances.get(i - 1).add(new HealthInsurance(
                                    rs.getInt("insuranceID"), rs.getInt("timeInYears"), rs.getInt("levelOfRisk"),
                                    rs.getString("personName")
                            ));
                            break;
                        case "Transport":
                            insurances.get(i - 1).add(new TransportInsurance(
                                    rs.getInt("insuranceID"), rs.getInt("timeInYears"), rs.getInt("levelOfRisk"),
                                    rs.getString("transportName"), rs.getString("transportClass"), rs.getString("transportNumber")
                            ));
                            break;
                        case "Real estate":
                            insurances.get(i - 1).add(new RealEstateInsurance(
                                    rs.getInt("insuranceID"), rs.getInt("timeInYears"), rs.getInt("levelOfRisk"),
                                    rs.getString("city"), rs.getString("address"), rs.getDouble("area")
                            ));
                    }
                }
                con.close();
            }catch(Exception e){ System.out.println(e);}
            derivatives.add(new Derivative(i,insurances.get(i-1)));
        }
    }

    public void updateData (BaseInsurance insurance){
        String type = insurance.getClass().toString();
        String querry = new String();
        switch (type){
            case "class kn11sp.yaremechko.insurances.HealthInsurance":
                querry = "update insurances " +
                        "set insuranceType = \"Health\", " +
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "personName = \""+insurance.getPersonName()+"\" " +
                        "where insuranceID = "+insurance.getId();
                break;
            case "class kn11sp.yaremechko.insurances.RealEstateInsurance":
                querry = "update insurances " +
                        "set insuranceType = \"Real estate\", " +
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "area = "+insurance.getArea()+", " +
                        "city = \""+insurance.getCity()+"\", " +
                        "address = \""+ insurance.getAddress()+"\" " +
                        "where insuranceID = "+insurance.getId();
                break;
            case "class kn11sp.yaremechko.insurances.TransportInsurance":
                querry = "update insurances " +
                        "set insuranceType = \"Transport\", " +
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "transportName = \""+insurance.getTransportName()+"\", " +
                        "transportClass = \""+insurance.getTransportClass()+"\", " +
                        "transportNumber = \""+insurance.getTransportNumber()+"\" "+
                        "where insuranceID = "+ insurance.getId();
                break;
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kursova","root","davil2332");
            PreparedStatement pstmt = con.prepareStatement(querry);
            pstmt.executeUpdate();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData (BaseInsurance insurance, int derivativeID){
        String type = insurance.getClass().toString();
        String querry = new String();
        switch (type){
            case "class kn11sp.yaremechko.insurances.HealthInsurance":
                querry = "insert into insurances " +
                        "set insuranceType = \"Health\", " +
                        "derivativeID = "+ derivativeID+", "+
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "personName = \""+insurance.getPersonName()+"\"";
                break;
            case "class kn11sp.yaremechko.insurances.RealEstateInsurance":
                querry = "insert into insurances " +
                        "set insuranceType = \"Real estate\", " +
                        "derivativeID = "+ derivativeID+", "+
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "area = "+insurance.getArea()+", " +
                        "city = \""+insurance.getCity()+"\", " +
                        "address = \""+ insurance.getAddress()+"\"";
                break;
            case "class kn11sp.yaremechko.insurances.TransportInsurance":
                querry = "insert into insurances " +
                        "set insuranceType = \"Transport\", " +
                        "derivativeID = "+ derivativeID+", "+
                        "timeInYears = "+insurance.getTimeInYears()+", " +
                        "levelOfRisk = "+insurance.getLevelOfRisk()+", " +
                        "transportName = \""+insurance.getTransportName()+"\", " +
                        "transportClass = \""+insurance.getTransportClass()+"\", " +
                        "transportNumber = \""+insurance.getTransportNumber()+"\"";
                break;
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kursova","root","davil2332");
            PreparedStatement pstmt = con.prepareStatement(querry);
            pstmt.execute();
        }catch(Exception e){ System.out.println(e);}
    }
    public void deleteDerivative(int derivativeId){
        String querry = "DELETE from insurances where derivativeId = "+ derivativeId;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kursova","root","davil2332");
            PreparedStatement pstmt = con.prepareStatement(querry);
            pstmt.execute();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void deleteInurance(int insuranceId){
        String querry = "DELETE from insurances where insuranceID = "+ insuranceId;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kursova","root","davil2332");
            PreparedStatement pstmt = con.prepareStatement(querry);
            pstmt.execute();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
