package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            /*
            st = conn.prepareStatement(
                    "insert into seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "values "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Atribuindo valores ao prepareStatement (?,?,?,?,?)
            st.setString(1, "Vanderlei Kleinschmidt");
            st.setString(2, "vanderlei.master@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("14/04/1975").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
             */

            //Inserir 2 departamentos
            st = conn.prepareStatement(
                    "insert into department (Name) values('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS
            );

            int rowsAffected = st.executeUpdate();//executa a operação de update

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! ID = " + id);
                }

            } else {
                System.out.println("No rows affected.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//        } catch (ParseException ex) {
//            ex.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
