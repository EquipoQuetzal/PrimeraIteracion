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


import model.Publicacion;
import model.Usuario;
import logic.UsuarioC;
import logic.PublicacionC;
import logic.SesionC;

/**
 *
 * @author jorge
 */
@ManagedBean
@RequestScoped
public class PublicacionBean {
    private Usuario usuario = new Usuario();
    private Publicacion publicacion = new Publicacion(); //la nueva publicacion
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.
    private UsuarioC helper2;
    private PublicacionC helper;  

 public PublicacionBean (){
    faceContext = FacesContext.getCurrentInstance();
    httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    helper = new PublicacionC();
        usuario = (Usuario) httpServletRequest.getSession().getAttribute("sessionUsuario");
}

 public String registrarPublicacion(){
     try{
         helper.registrarBD(publicacion, usuario);
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Publicacion realizada con éxito", null);
        faceContext.addMessage(null, message);
     }catch(org.hibernate.TransientPropertyValueException ex){
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al publicar", null);
        faceContext.addMessage(null, message);
         return "PublicarOfertaIH";
     }
     return "PerfilIH"; // Por lo mientras regreso al perfil 
 }
 
 public Boolean prestado(){
    if (publicacion.getUsuarioByIdprestatario() != null)
            return true;
    return false;
}

 public void pedir(Publicacion publi){
     System.out.print(publi.getIdpublicacion());
     helper.prestarPublicacion(publi, usuario);
 }
 
 public Publicacion getPublicacion(){
     return publicacion;
 }
 
 public void setPublicacion(Publicacion publicacion){
     this.publicacion = publicacion;
}
 
} 