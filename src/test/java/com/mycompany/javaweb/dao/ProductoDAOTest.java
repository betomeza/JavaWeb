package com.mycompany.javaweb.dao;

import clase2.dao.ExcepcionDAO;
import clase2.dao.ProductoDAO;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author LAB704-00
 */
public class ProductoDAOTest {

    @Test
    public void obtenerTotalTest() {
        ProductoDAO dao = new ProductoDAO();
        try {
            int total = dao.obtenerTotal();
            System.out.println("Total --> " + total);

            Assert.assertTrue(total > 0);
        } catch (ExcepcionDAO e) {
            Assert.fail("falló");
        }
    }
}