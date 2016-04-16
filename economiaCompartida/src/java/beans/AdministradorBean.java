/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import logic.HibernateUtil;

import logic.UsuarioC;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Transaction;
/**
 *
 * @author oem
 */
@ManagedBean
@RequestScoped
public class AdministradorBean{

    private Usuario usuario = new Usuario();    //Representa al usuario actual
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.
    private UsuarioC helper;
    
    public AdministradorBean(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        helper = new UsuarioC();
    }

    public String borrarUsuario(){
        
        System.out.println("|-| Recibido correo en vista: "+usuario.getCorreo());
        model.Usuario usuarioBD = helper.buscarPorCorreo(usuario.getCorreo());
        if (usuarioBD != null) {
            try {
                System.out.println("|-| Usuario encontrado en la base de datos: "+usuarioBD.getCorreo()+"|"+usuarioBD.getContrasena());
                usuario = usuarioBD;                
                //Borrando al usuario
                helper.borrarUsuarioBD(usuario);                                
            } catch (Exception ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                // Agregar bien el mensaje de la excepcion
            }
        } else { //El correo es incorrecto (Pues no se encontro ningun usuario con ese correo)
            System.out.println("|-| El correo: "+usuario.getCorreo()+" no esta en la base de datos");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El correo: "+ usuario.getCorreo()+" no existe en la base de datos.", null);
            faceContext.addMessage(null, message);
            return "BorrarUsuarioIH";
        }
        return "BorrarUsuarioIH";
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
