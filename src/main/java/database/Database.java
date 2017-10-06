package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.LineItem;
import models.Product;

import java.sql.*;


public class Database {
    private Connection conn;

    public void openDatabase(){
        try{
            Class.forName("org.sqlite.JDBC") ;
            String dbURL = "jdbc:sqlite:Product.db";
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
    public ObservableList<LineItem> readFile(){
        ObservableList<LineItem> listItem = FXCollections.observableArrayList();
        try {


            String query = "Select * from Product";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id  = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);

                listItem.add(new LineItem(new Product(id,name,price),quantity));
                //System.out.println("ID: "+id+"Name: "+name+"Price: "+price+"Quantity: "+quantity);

            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return  listItem;

    }
    public void Remove(LineItem lineItem){
        try{
            String query = "Delete from Product where Id = '"+lineItem.getProduct().getId()+"'";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public void addData(LineItem lineItem){
        try{
            String query = "insert into Product ('Id','Name','Price','Quantity') values ("+"'"+lineItem.getProduct().getId()+"','"+lineItem.getProduct().getName()
                    +"','"+lineItem.getProduct().getPrice()+"','"+lineItem.getQuantity().get()+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void editData(LineItem lineItem ){
        String query = "update Product set 'Name' = '"+lineItem.getProduct().getName()+ "', 'Price' = '"+
                lineItem.getProduct().getPrice()+"','Quantity' = '"+lineItem.getQuantity().get()+"' where Id = '"
                +lineItem.getProduct().getId()+"'";
        try{

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void decreaseQuantity(LineItem lineItem){
        String query = "update Product set 'Quantity' = '"+lineItem.getQuantity().get()+"' where Id = '" +lineItem.getProduct().getId()+"'";
        try{

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }








}
