/*
    Clase diseniada como controlador pararealizar busquedas.

 */
package logic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Publicacion;
import java.util.*;

/**
 *
 * @author Alan
 */
public class ConsultarC {

    private ArrayList<Publicacion> resultados;
    private Session session;

    public ConsultarC() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Método que obtiene las palabras de una cadena y crea unaexpresión regular
     * para buscar coincidencias con todas las palabras.
     *
     * @param cadena Cadena a separar por palabras.
     * @return La consulta p
     */
    public static String obtenerPalabras(String cadena) {
        if (cadena.length() <= 0) {
            return cadena;
        }
        cadena = cadena.toLowerCase(); //Transforma la cadena a minúsculas.
        String[] palabras = cadena.split(" ");
        String resultado = ".*(";
        for (String p : palabras) {
            resultado += p + "|";
        }
        if (resultado.length() >= 5) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        resultado += ").*";
        return resultado;
    }

    public List<Publicacion> buscar(String clave) {
        clave = obtenerPalabras(clave);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Publicacion> r = new ArrayList<>();
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createSQLQuery("select * from publicacion where "
                    + "LOWER(publicacion.descripcion) ~ :clave ; ").addEntity(Publicacion.class).setString("clave", clave);
            resultados = (ArrayList<Publicacion>) q.list();
            //session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            //session.getTransaction().rollback();
        }
        return resultados;
    }
    
    public ArrayList<Publicacion> getResultados() {
        return this.resultados;
    }
    
    public void setResultados(ArrayList<Publicacion> r) {
        this.resultados = r;
    }
    
}
