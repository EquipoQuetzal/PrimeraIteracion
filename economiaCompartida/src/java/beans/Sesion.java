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

import logic.SesionC;
import model.Usuario;

/**
 *
 * @author Kikinzco
 */
@ManagedBean
@RequestScoped
public class Sesion {
    
    private Usuario usuario = new Usuario();    //Representa al usuario actual
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.
    private SesionC helper;
    
    public Sesion(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        helper = new SesionC();
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String login() {
        model.Usuario usuarioBD = helper.autentificar(usuario);
        if (usuarioBD != null) {
            try {
                System.out.println("|-| Usuario encontrado en la base de datos: "+usuarioBD.getCorreo()+"|"+usuarioBD.getContrasena());
                MessageDigest md = MessageDigest.getInstance("MD5");
                System.out.println("|-| Nombre ingresado: "+usuario.getNombre()+", Correo ingresado: "+usuario.getCorreo());
                System.out.println("|-| Contrasena introducida: "+usuario.getContrasena());
                md.update(usuario.getContrasena().getBytes());
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                System.out.println("|-| Contrasena introducida cifrada: "+sb.toString());
                
                if (sb.toString().equals(usuarioBD.getContrasena())) { //La contrasena introducida coincide con la encontrada en la base de datos
                    System.out.println("|-| La contrasena introducida es correcta! Ingresando al sistema");
                    httpServletRequest.getSession().setAttribute("sessionUsuario", usuario); //Ponemos los datos de entrada en el servlet (sessionUsuario)
                    usuario = usuarioBD; // Guardamos los datos de la BD en la sesion para futuro uso
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                    faceContext.addMessage(null, message);
                    return "valid";
                }else{ //Contrasena incorrecta
                    System.out.println("|-| La contrasena: "+usuario.getContrasena()+" del usuario "+usuario.getCorreo()+" es incorrecta");
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contrasena introducida es incorrecta.", null);
                    faceContext.addMessage(null, message);
                    return "invalid";
                }
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("|-| Algo raro paso con el algoritmo de cifrado");
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //El usuario no ha sido registrado
            System.out.println("|-| El correo: "+usuario.getCorreo()+" no esta en la base de datos");
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El correo: "+ usuario.getCorreo()+" no existe en la base de datos.", null);
            faceContext.addMessage(null, message);
            return "invalid";
        }
        return "index";
    }
    
    public String cerrarSesion() {
	//FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // Asi lo tengo yo en mi practica
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, message);
	return "index";
    }
        
    public boolean verificarSesion() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUsuario") == null)
            return false;
        else
            return true;
    }
    
    public void prueba(){
        System.out.println("Prueba de acceso a metodos del Bean");
    }
        
}