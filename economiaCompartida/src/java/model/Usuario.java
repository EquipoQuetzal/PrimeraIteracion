package model;
// Generated 10/04/2016 05:15:29 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames="correo") 
)
public class Usuario  implements java.io.Serializable {


     private int idusuario;
     private String nombre;
     private String contrasena;
     private Integer calificacion;
     private Boolean esadmin;
     private String correo;
     private Set comentarios = new HashSet(0);
     private Set publicacionsForIdprestatario = new HashSet(0);
     private Set publicacionsForIdusuario = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idusuario, String nombre, String contrasena, String correo) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
    }
    public Usuario(int idusuario, String nombre, String contrasena, Integer calificacion, Boolean esadmin, String correo, Set comentarios, Set publicacionsForIdprestatario, Set publicacionsForIdusuario) {
       this.idusuario = idusuario;
       this.nombre = nombre;
       this.contrasena = contrasena;
       this.calificacion = calificacion;
       this.esadmin = esadmin;
       this.correo = correo;
       this.comentarios = comentarios;
       this.publicacionsForIdprestatario = publicacionsForIdprestatario;
       this.publicacionsForIdusuario = publicacionsForIdusuario;
    }
   
     @Id 

    
    @Column(name="idusuario", unique=true, nullable=false)
    public int getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    
    @Column(name="nombre", nullable=false)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="contrasena", nullable=false)
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    @Column(name="calificacion")
    public Integer getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    
    @Column(name="esadmin")
    public Boolean getEsadmin() {
        return this.esadmin;
    }
    
    public void setEsadmin(Boolean esadmin) {
        this.esadmin = esadmin;
    }

    
    @Column(name="correo", unique=true, nullable=false)
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioByIdprestatario")
    public Set getPublicacionsForIdprestatario() {
        return this.publicacionsForIdprestatario;
    }
    
    public void setPublicacionsForIdprestatario(Set publicacionsForIdprestatario) {
        this.publicacionsForIdprestatario = publicacionsForIdprestatario;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioByIdusuario")
    public Set getPublicacionsForIdusuario() {
        return this.publicacionsForIdusuario;
    }
    
    public void setPublicacionsForIdusuario(Set publicacionsForIdusuario) {
        this.publicacionsForIdusuario = publicacionsForIdusuario;
    }

}


