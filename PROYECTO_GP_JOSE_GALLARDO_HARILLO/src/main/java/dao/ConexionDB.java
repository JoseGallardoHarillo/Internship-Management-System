package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConexionDB {
    private static DataSource ds;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GP");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar el DataSource", e);
        }
    }

    public static Connection getConnection() throws Exception {
        // Obtiene la conexi�n del pool
        return ds.getConnection();
    }
}
