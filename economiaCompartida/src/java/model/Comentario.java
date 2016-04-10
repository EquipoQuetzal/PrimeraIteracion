package model;
// Generated 10/04/2016 01:23:58 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comentario generated by hbm2java
 */
@Entity
@Table(name="comentario"
    ,schema="public"
)
public class Comentario  implements java.io.Serializable {


     private int idcomentario;
     private Publicacion publicacion;
     private Usuario usuario;
     private Date fecha;
     private String contenido;

    public Comentario() {
    }

    public Comentario(int idcomentario, Publicacion publicacion, Usuario usuario, Date fecha, String contenido) {
       this.idcomentario = idcomentario;
       this.publicacion = publicacion;
       this.usuario = usuario;
       this.fecha = fecha;
       this.contenido = contenido;
    }
   
     @Id 

    
    @Column(name="idcomentario", unique=true, nullable=false)
    public int getIdcomentario() {
        return this.idcomentario;
    }
    
    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpublicacion", nullable=false)
    public Publicacion getPublicacion() {
        return this.publicacion;
    }
    
    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", nullable=false, length=13)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="contenido", nullable=false)
    public String getContenido() {
        return this.contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }




}


