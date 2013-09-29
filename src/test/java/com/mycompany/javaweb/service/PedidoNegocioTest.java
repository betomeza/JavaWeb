package com.mycompany.javaweb.service;

import clase2.dao.ExcepcionDAO;
import clase2.modelo.DetallePedido;
import clase2.modelo.Pedido;
import clase2.service.PedidosNegocio;
import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import org.junit.Test;

public class PedidoNegocioTest  {
    
    @Test        
    public void realizarPedidoTest() {

        DetallePedido dp1 = new DetallePedido();
        dp1.setIdProducto(1);
        dp1.setPrecio(10);
        dp1.setCantidad(1);

        DetallePedido dp2 = new DetallePedido();
        dp2.setIdProducto(2);
        dp2.setPrecio(20);
        dp2.setCantidad(1);

        Collection<DetallePedido> detalles = new ArrayList<DetallePedido>();
        detalles.add(dp1);
        detalles.add(dp2);

        Pedido pedido = new Pedido();
        pedido.setIdUsuario("Alberto");
        pedido.setFecha("2011-07-15");
        pedido.setEstado("1");
        pedido.setTotal(500);
        PedidosNegocio negocio = new PedidosNegocio();
        try {
            negocio.realizarPedido(pedido, detalles);
        } catch (ExcepcionDAO e) {
            Assert.fail("Falló la inserción");
        }
    }
}
