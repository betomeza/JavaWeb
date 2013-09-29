package clase2.dao;

import clase2.dao.BaseDAO;
import clase2.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.zelper.ConexionDB;

public class PedidoDAO extends BaseDAO{
    
    public Pedido insertar(Pedido vo) throws ExcepcionDAO {
		System.out.println("PedidoDAO: insertar()");
		String query = "INSERT INTO pedido (id_usuario,fecha,estado,total) VALUES (?,?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConexionDB.obtenerConexion();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getIdUsuario());
			stmt.setString(2, vo.getFecha());
			stmt.setString(3, vo.getEstado());
			stmt.setDouble(4, vo.getTotal());

			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new SQLException("No se pudo insertar");
			}

			int idp = 0;
			query = "SELECT LAST_INSERT_ID()";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			if (rs.next()) {
				idp = rs.getInt(1);
			}
			vo.setIdPedido(idp);

			for (DetallePedido ped : vo.getDetalles()) {
				query = "INSERT INTO detalle_pedido(id_pedido, idproducto, precio, cantidad) VALUES (?,?,?,?)";
				stmt = con.prepareStatement(query);
				stmt.setInt(1, idp);
				stmt.setInt(2, ped.getIdProducto());
				stmt.setDouble(3, ped.getPrecio());
				stmt.setInt(4, ped.getCantidad());

				int u = stmt.executeUpdate();
				if (u != 1) {
					throw new SQLException("No se pudo insertar");
				}
				int idd = 0;
				query = "SELECT LAST_INSERT_ID()";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				if (rs.next()) {
					idd = rs.getInt(1);
				}
				ped.setIdDetallePedido(idd);
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new ExcepcionDAO(e.getMessage());
			}
			System.err.println(e.getMessage());
			throw new ExcepcionDAO(e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new ExcepcionDAO(e.getMessage());
			}
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return vo;
	}

}
