package clase2.service;

import clase2.dao.ExcepcionDAO;
import clase2.dao.PedidoDAO;
import clase2.modelo.DetallePedido;
import clase2.modelo.Pedido;
import java.util.Collection;

public class PedidosNegocio {

    public void realizarPedido(Pedido pedido, Collection<DetallePedido> detalles) throws ExcepcionDAO {


        Pedido p = new Pedido();

        pedido.setDetalles(detalles);

        PedidoDAO dao = new PedidoDAO();
        try {
            dao.insertar(pedido);
        } catch (ExcepcionDAO e) {
            throw e;
        }

    }
}
