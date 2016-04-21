/*
    Clase diseniada como controlador pararealizar busquedas.

*/
package logic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
<<<<<<< HEAD
import model.Publicacion;
=======
//import java.util.ArrayList;
//import java.util.List;
import model.Publicacion;
//import java.util.Collections;
//import java.util.Date;
>>>>>>> origin/develop
import java.util.*;
import model.Usuario;




/**
 *
 * @author Alan
 */
public class ConsultarC {
    
    private ArrayList  <Publicacion> resultados;
    private Session session;
  
  
  
     public ConsultarC(){
          session = HibernateUtil.getSessionFactory().getCurrentSession();
<<<<<<< HEAD
         
       
 
    }
    
    
    /**
     * Método que obtiene las palabras de una cadena y crea unaexpresión regular para buscar coincidencias con todas las palabras.
     * @param cadena Cadena a separar por palabras.
     * @return La consulta p
     */
    public static String obtenerPalabras(String cadena){
        if(cadena.length()<=0)
            return cadena;
       
        cadena = cadena.toLowerCase(); //Transforma la cadena a minúsculas.
        String [] palabras  = cadena.split(" ");
        String resultado = ".*(";
        for(String p: palabras){
            resultado+= p+"|";
            
        }
        if(resultado.length() >= 5){
            resultado =    resultado.substring(0,resultado.length()-1);
        }
        
        resultado += ").*";
        return resultado;
     
        
=======
          //init();
       
 
    }
    public void init(){
        Usuario u = new Usuario(); 
        this.resultados = new ArrayList();
        Date d = new Date();
        resultados.add(new Publicacion(1,u, "Oaxaca","Mmemoria ram",d ));
        resultados.add(new Publicacion(2,u, "Oaxaca","Mmemoria2 ram",d ));
        resultados.add(new Publicacion(3,u, "Oaxaca","Mmemoria3 ram",d ));
        
        
        
       
>>>>>>> origin/develop
    }
    
   
    
    
     public List<Publicacion> buscar(String clave ){
<<<<<<< HEAD
         
         clave = obtenerPalabras(clave);
=======
         clave = clave.toLowerCase();
>>>>>>> origin/develop
         session = HibernateUtil.getSessionFactory().getCurrentSession();
         List<Publicacion> r = new ArrayList<>();
          try{
            Transaction tx = session.beginTransaction();
             Query q = session.createSQLQuery("select * from publicacion where "
<<<<<<< HEAD
            + "LOWER(publicacion.descripcion) ~ :clave ; ").addEntity(Publicacion.class).setString("clave",  clave );
=======
            + "LOWER(publicacion.descripcion) like :clave ; ").addEntity(Publicacion.class).setString("clave", "%" + clave + "%");
>>>>>>> origin/develop

            
            resultados = (ArrayList<Publicacion>) q.list();
            session.getTransaction().commit();
            this.ordenar();
            
           
        }catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
         return resultados;
         
     }
    
    public void ordenar(){
        
<<<<<<< HEAD
        Collections.sort(this.resultados);
        Collections.reverse(this.resultados);
=======
        //Collections.sort(this.resultados);
>>>>>>> origin/develop
        
    }
   
  
     public ArrayList<Publicacion> getResultados(){
        return this.resultados;
    }
    public void setResultados(ArrayList<Publicacion> r){
        
        this.resultados  = r;
    }
<<<<<<< HEAD


     
            
            
}

=======
    
}
>>>>>>> origin/develop
