package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.model.Lofi;
import ec.ftt.util.DBUtil;

public class LofiDao {
	
	private static Connection connection;

    public LofiDao() {
        connection = DBUtil.getConnection();
    } //LofiDao

    public static void addLofi(Lofi lofi) {
        
    	//https://www.devmedia.com.br/assertions-em-java/28781
    	
    	try {
    		
    		System.out.println("Here we are...");
    		System.out.println(lofi);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO lofi.lofi (Name, Link, Mood, Dimension) VALUES (?, ?, ?, ?)");
            int myInt = lofi.isDimension() ? 1 : 0;
            // Parameters start with 1
            preparedStatement.setString(1, lofi.getName());
            preparedStatement.setString(2, lofi.getLink());
            //preparedStatement.setDate(3, (java.sql.Date)Lofi.getDob());
            preparedStatement.setString(3, lofi.getMood());
            preparedStatement.setInt(4, myInt);

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addLofi
    
    public void deleteLofi(int id) {
    	
    	Lofi Lofi = new Lofi();
    	Lofi.setId(id);
    	
    	deleteLofi(Lofi);
    	
    } // deleteLofi long

    public void deleteLofi(Lofi Lofi) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM lofi.lofi WHERE ID=?");
        	
        	/*
        	 * 
        	 * DELETE FROM `ftt`.`Lofi` WHERE <{where_expression}>;

        	 * 
        	 */
            
            // Parameters start with 1
            preparedStatement.setLong(1, Lofi.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteLofi

    public void updateLofi(Lofi Lofi) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE lofi.lofi SET NAME=?, " 
                    		                          + "Link=?, " 
                    		                          + "Mood=? " 
                                               + "WHERE ID=?");
            
            /*
             * 
             * UPDATE `ftt`.`Lofi`
				SET
					`ID` = <{ID: }>,
					`NAME` = <{NAME: }>,
					`Link` = <{Link: }>,
					`Mood` = <{Mood: }>
					WHERE `ID` = <{expr}>;

             * 
             */
            
            // Parameters start with 1
            preparedStatement.setString(1, Lofi.getName());
            preparedStatement.setString(2, Lofi.getLink());
            //preparedStatement.setDate(3, (java.sql.Date)Lofi.getDob());
            preparedStatement.setString(3, Lofi.getMood());
            
            preparedStatement.setLong(4, Lofi.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateLofi

    public List<Lofi> getAllLofi() {
        
    	List<Lofi> LofiList = new ArrayList<Lofi>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM lofi.lofi");
            while (rs.next()) {
                
            	Lofi Lofi = new Lofi();
                
            	Lofi.setId(rs.getInt("Id"));
                Lofi.setName(rs.getString("Name"));
                Lofi.setLink(rs.getString("Link"));
                //Lofi.setDob(rs.getDate("DOB"));
                Lofi.setMood(rs.getString("Mood"));

                LofiList.add(Lofi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return LofiList;
    } //getAllLofi

    public Lofi getLofiById(int id) {
    	
    	Lofi Lofi = new Lofi();
    	Lofi.setId(id);
    	
    	return getLofiById(Lofi);
    	
    } // getLofiById long
    
    
    	
    public Lofi getLofiById(Lofi Lofi) {

    	Lofi LofiOutput = new Lofi();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from lofi.lofi WHERE ID=?");
            
            preparedStatement.setLong(1, Lofi.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	LofiOutput.setId(rs.getInt("Id"));
            	LofiOutput.setName(rs.getString("Name"));
            	LofiOutput.setLink(rs.getString("Link"));
            	//LofiOutput.setDob(rs.getDate("DOB"));
            	LofiOutput.setMood(rs.getString("Mood"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return LofiOutput;
    } //getLofiById
    
    public String getDbDate() {

    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    } //getDbDate
	

}