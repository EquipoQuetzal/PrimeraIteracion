/*
    Clase diseniada como controlador pararealizar busquedas.

*/
package logic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import java.util.ArrayList;
//import java.util.List;
import model.Publicacion;
//import java.util.Collections;
//import java.util.Date;
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
          //init();
       
 
    }
    public void init(){
        Usuario u = new Usuario(); 
        this.resultados = new ArrayList();
        Date d = new Date();
        resultados.add(new Publicacion(1,u, "Oaxaca","Mmemoria ram",d ));
        resultados.add(new Publicacion(2,u, "Oaxaca","Mmemoria2 ram",d ));
        resultados.add(new Publicacion(3,u, "Oaxaca","Mmemoria3 ram",d ));
        
        
        
       
    }
    
   
    
    
     public List<Publicacion> buscar(String clave ){
         clave = clave.toLowerCase();
         session = HibernateUtil.getSessionFactory().getCurrentSession();
         List<Publicacion> r = new ArrayList<>();
          try{
            Transaction tx = session.beginTransaction();
             Query q = session.createSQLQuery("select * from publicacion where "
            + "LOWER(publicacion.descripcion) like :clave ; ").addEntity(Publicacion.class).setString("clave", "%" + clave + "%");

            
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
        
        //Collections.sort(this.resultados);
        
    }
   
  
     public ArrayList<Publicacion> getResultados(){
        return this.resultados;
    }
    public void setResultados(ArrayList<Publicacion> r){
        
        this.resultados  = r;
    }
    
}
