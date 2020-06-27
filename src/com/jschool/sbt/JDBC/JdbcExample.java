package com.jschool.sbt.JDBC;

import java.sql.*;

public class JdbcExample {

    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/-/test","sa","")){
            try(Statement statement = connection.createStatement()){
                //statement.execute("");

                statement.execute("create table client");



                statement.setFetchSize(50);
                ResultSet resultSet = statement.executeQuery("select * from operation");
                while (resultSet.next()){
                    System.out.println(resultSet.getInt(1));
                    System.out.println(resultSet.getInt("ID"));
                }



                PreparedStatement preparedStatement = connection.prepareStatement("select * from operation where id = ?");
                preparedStatement.setInt(1,345);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while (resultSet1.next()){
                    System.out.println(resultSet1.getInt(1)+ " : "+ resultSet1.getInt("ID"));
                }



                //для массовой обработки однотипных операций, например ихзменения
                PreparedStatement preparedStatement2 = connection.prepareStatement("update operation set status = 'opened' where id=?");
                for (int i=-10; i<1000; i++){
                    preparedStatement2.setInt(1,i);
                    preparedStatement2.addBatch();
                }
                int[] executeStatement = preparedStatement2.executeBatch();
                //System.out.println(executeStatement);

                try(Statement bathedStatement = connection.createStatement()){
                    bathedStatement.addBatch("insert into client values(-1,'Victor')");
                    bathedStatement.addBatch("update client set name = 'victor' where id = 0");
                    bathedStatement.addBatch("delete operation where id = 1");
                }




            }





        }

    }
}
