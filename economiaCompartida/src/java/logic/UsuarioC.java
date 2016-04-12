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
    
    public void registrar(Usuario usuario){        
        try{
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("IncertarUsuario");
            q.setString("nombre",usuario.getNombre());
            q.setString("correo",usuario.getCorreo());
            q.setString("contrasena",usuario.getContrasena());
            // q.executeUpdate(); //buscar como guardar el usuario en la base de datos, aun no sabemos bien como, quizas sea con .save(modelo)
            session.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
