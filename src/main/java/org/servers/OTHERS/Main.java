package org.servers.OTHERS;
import org.servers.OTHERS.DB;

import java.sql.SQLException;

public class Main {


        public static void main(String[] args){
            try (var _ =  DB.connect()){
                System.out.println("Connected to the PostgresSQL database.");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

}
