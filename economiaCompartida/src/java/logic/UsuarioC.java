/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author oem
 */
public class UsuarioC {

    private Session session;

    public UsuarioC(){
    session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void registrarBD(Usuario usuario){        
        try{
            Transaction tx = session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
            // q.executeUpdate(); //buscar como guardar el usuario en la base de datos, aun no sabemos bien como, quizas sea con .save(modelo)
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Usuario buscarPorCorreo(String correo){
        Usuario resultado;
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("BuscarPorCorreo").setString("correo",correo);
            resultado = (Usuario) q.uniqueResult();
            //Si regresa null, significa que el usuario no esta registrado en la BD, no recuerdo donde afecta eso
            session.close();
            return resultado;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    
    
    // Quizas regresar booleano que indique si se elimino correctamente    
    public void borrarUsuarioBD(Usuario usuario){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(usuario);
            session.getTransaction().commit();
        }catch (Exception e) {
            // Esto nunca deberia pasar porque ya sacamos anteriormente al usuario de la base de datos
            e.printStackTrace();
        }
    }    
    
}
