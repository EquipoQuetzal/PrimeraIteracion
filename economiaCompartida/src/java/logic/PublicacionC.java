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

        public void registrarBD(Publicacion publicacion){        
        try{
            Transaction tx = session.beginTransaction();
            java.util.Date fecha = new Date();
            usuario.setIdusuario(1); //Checar como extraer el id del usuario alctual  
            publicacion.setUsuarioByIdusuario(usuario);
            publicacion.setFechapublicacion(fecha);
            session.save(publicacion);
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("Hubo un error al hacer la publicacion");
            e.printStackTrace();
        }
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
        
}
