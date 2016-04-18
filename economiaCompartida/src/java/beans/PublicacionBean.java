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
import logic.PublicacionC;

/**
 *
 * @author jorge
 */
@ManagedBean
@RequestScoped
public class PublicacionBean {
    
    private Publicacion publicacion = new Publicacion(); //la nueva publicacion
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.
    private PublicacionC helper;  

 public PublicacionBean (){
    faceContext = FacesContext.getCurrentInstance();
    httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    helper = new PublicacionC();
}

 public String registrarPublicacion(){
     try{
         helper.registrarBD(publicacion);
         message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Publicacion realizada correctamente", null);
        faceContext.addMessage(null, message);
     }catch(Exception e){
         return "PublicarOferta";
     }
     return "PerfilIH"; // Por lo mientras regreso al perfil 
 }
 
 public Publicacion getPublicacion(){
     return publicacion;
 }
 
 public void setPublicacion(Publicacion publicacion){
     this.publicacion = publicacion;
}
 
} 