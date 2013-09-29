package app.service;

import app.dao.CategoriaDAO;
import app.dao.ExcepcionDAO;
import app.model.Categoria;
import java.util.Collection;

public class GestionCategoria {

    public static void main(String[] args) throws ExcepcionDAO {
        Categoria catOficina = GestionCategoria.insertar("Oficina", "Productos de Oficina");
        Categoria catCasa = GestionCategoria.insertar("Casa", "Productos de Casa");
        GestionCategoria.listar();
   }
    
    public Collection<Categoria> buscarPorNombre(String nombre)
            throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.listar();
    }

    public static Categoria insertar(String nombre, String descripcion)
            throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();

        Categoria vo = new Categoria();
        vo.setNombre(nombre);
        vo.setDescripcion(descripcion);

        return dao.insertar(vo);
    }

    public Categoria obtener(int idCategoria) throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.obtener(idCategoria);
    }

    public void eliminar(int idCategoria) throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();
        dao.eliminar(idCategoria);
    }

    public Categoria actualizar(int idCategoria, String nombre,
            String descripcion) throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();

        Categoria vo = new Categoria();
        vo.setIdCategoria(idCategoria);
        vo.setNombre(nombre);
        vo.setDescripcion(descripcion);

        return dao.actualizar(vo);
    }

    public static void listar() throws ExcepcionDAO {
        CategoriaDAO dao = new CategoriaDAO();
        
        Collection<Categoria> categorias = dao.listar();
        
        for(Categoria categoria : categorias){
            System.out.println(categoria.getIdCategoria() 
                    + " " + categoria.getNombre());
        }
    }
}
