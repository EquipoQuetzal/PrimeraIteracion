/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;

import model.Publicacion;
import model.Usuario;
/**
 *
 * @author jorge
 */
public class PublicacionC {
    
    private Session session;
    private Usuario usuario = new Usuario();
    
    public PublicacionC(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

        public void registrarBD(Publicacion publicacion,Usuario usu ){        
            Transaction tx = session.beginTransaction();
            java.util.Date fecha = new Date();
            //usuario.setIdusuario(idUsuario); //Checar como extraer el id del usuario alctual  
            publicacion.setUsuarioByIdusuario(usu);
            publicacion.setFechapublicacion(fecha);
            session.save(publicacion);
            session.getTransaction().commit();
    }        
        
    public Publicacion buscarPublicacion(Integer id){
        Publicacion resultado;
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("BuscarPublicacion").setInteger("id",id);
            resultado = (Publicacion) q.uniqueResult();
            //Si regresa null, significa que el usuario no esta registrado en la BD, no recuerdo donde afecta eso
            session.close();
            return resultado;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void borrarPublicacionBD(Publicacion publicacion){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(publicacion);
            session.getTransaction().commit();
        }catch (Exception e) {
            // Esto nunca deberia pasar porque ya sacamos anteriormente al usuario de la base de datos
            e.printStackTrace();
        }
    }        
    public void prestarPublicacion(Publicacion publicacion,Usuario usu ){
        if (publicacion.getUsuarioByIdusuario() != null){
        //usuario.setIdusuario(idUsuario); //Checar como extraer el id del usuario alctual  
        Transaction tx = session.beginTransaction();
        publicacion.setUsuarioByIdprestatario(usu);
        session.update(publicacion);
        session.getTransaction().commit();
        }
    }    
}
