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

import logic.UsuarioC;
import model.Usuario;
/**
 *
 * @author oem
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();    //Representa al usuario actual
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.
    private UsuarioC helper;
    
    public UsuarioBean(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        helper = new UsuarioC();
    }

    public String registrar(){
        System.out.println("Intentando insertar al usuario: "+usuario.getNombre()+ ", "+usuario.getCorreo()+", "+usuario.getContrasena());
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(usuario.getContrasena().getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest)
              sb.append(String.format("%02x", b & 0xff));            
            usuario.setContrasena(sb.toString());
            System.out.println(usuario.getContrasena());
            helper.registrarBD(usuario);
            //asignar a sessionUsuario (pork iniciara sesion automaticamente el usuario k se registro)
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro finalizado correctamente", null);
            faceContext.addMessage(null, message);
        }catch(NoSuchAlgorithmException ex){ //Excepcion de hasheo
            System.out.println("|-| Algo raro paso con el algoritmo de cifrado");
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return "RegistroIH";
        }catch(Exception e){ //Excepcion general (Acotar excepciones especificas, para saber si correo repetido o demas)
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio la excepcion: "+e, null);
            faceContext.addMessage(null, message);
            return "RegistroIH";
        }
        //org.hibernate.exception.ConstraintViolationException (formato de correo incorrecto)
                
            return "index"; //Se registro correctamente el usuario
            //(TEMPORAL), ya que este bien implementado, mandar a pagina de perfil con sesion iniciada
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
