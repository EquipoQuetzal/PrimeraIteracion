/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import model.Publicacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;

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
}
