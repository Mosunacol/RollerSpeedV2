package Iudigital.RollerSpeed.Modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NonNull
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private Collection<Iudigital.RollerSpeed.Modelo.Rol> roles = new ArrayList<>();

    // ✅ Constructor usado en UsuarioServicioImpl
    public Usuario(String nombre, String apellido, String email, String password, List<Iudigital.RollerSpeed.Modelo.Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static Usuario createUsuario(String nombre, String apellido, String email, String password, List<Iudigital.RollerSpeed.Modelo.Rol> roles) {
        return new Usuario(nombre, apellido, email, password, roles);
    }
}
