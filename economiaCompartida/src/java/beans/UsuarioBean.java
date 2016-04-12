/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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

    public void registrar(){
        //Hay k poder verificar de alguna manera k el insert se hizo exitosamente
        //if(helper.registrarBD) El insert se pudo hacer bien pues ya
            // return "perfilIH" Redireeccionamos al perfil en teoria
        //Si no, regresa un mensaje de error o algo
           // Agregar mensaje de error (el ejemplo de mensaje de error ya sta en Sesion.java)
           // return "registro" sE QUEDA EN LA PAGINA DE REGISTRO
    }
}
