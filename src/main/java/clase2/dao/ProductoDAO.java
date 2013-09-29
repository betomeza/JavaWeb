/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clase2.dao;

import app.zelper.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author LAB704-00
 */
public class ProductoDAO extends BaseDAO {

    public int obtenerTotal() throws ExcepcionDAO {
        System.out.println("obtenerTotal()");
        Connection cons = null;
        CallableStatement callstmt = null;
        int resultado = -1;
        try {
            String call = "{CALL sp_productos_total(?)}";
            cons = ConexionDB.obtenerConexion();
            callstmt = (CallableStatement) cons.prepareCall(call);
            callstmt.registerOutParameter(1, Types.INTEGER);
            callstmt.execute();
            resultado = callstmt.getInt(1);
        } catch (SQLException e) {
            throw new ExcepcionDAO(e.toString());
        } catch (Exception e) {
            throw new ExcepcionDAO(e.toString());
        } finally {
            cerrarCallable(callstmt);
            cerrarConexion(cons);
        }
        return resultado;
    }
}
