package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import models.AdminUser;
import java.sql.*;

public class DatabaseAdmin {
    private Connection conn;

    public void openDatabase(){
        try{
            Class.forName("org.sqlite.JDBC") ;
            String dbURL = "jdbc:sqlite:UserAdmin.db";
            conn = DriverManager.getConnection(dbURL);
            if(conn != null){
                System.out.println("Connect to database!!!");
            }
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void closeDatabase(){
        try{
            conn.close();
            System.out.println("Close Database!!!");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public ObservableList<AdminUser> readFile(){
        ObservableList<AdminUser> listAdmin = FXCollections.observableArrayList();
        try {


            String query = "Select * from UserAdmin";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String user = resultSet.getString(1);
                String password = resultSet.getString(2);
                listAdmin.add(new AdminUser(user,password));


            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return  listAdmin;

    }
    public void Remove(AdminUser adminUser){
        try{
            String query = "Delete from UserAdmin where user = '"+adminUser.getUser()+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public void addData(AdminUser adminUser){
        ObservableList<AdminUser> AdminOld = readFile();
        boolean checkUser = false;
        for (AdminUser search:AdminOld){
            if(search.getUser().equals(adminUser.getUser())){
                checkUser  = true;
                break;
            }

        }
        if(checkUser==false){
            try{
                String query = "insert into UserAdmin ('user','password') values ("+"'"+adminUser.getUser()+"','"+adminUser.getPassword()+"')";
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException ex){
                ex.printStackTrace();
            }

        }

    }
    public void editData(AdminUser adminUser){
        String query = "update UserAdmin set 'user' = '"+adminUser.getUser()+ "', 'password' = '"+
                adminUser.getPassword()+"' where user = '"+adminUser.getUser()+"'";
        try{

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
