package Iudigital.RollerSpeed.Servicio;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Iudigital.RollerSpeed.Modelo.Usuario;
import Iudigital.RollerSpeed.Repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Iudigital.RollerSpeed.Controlador.dto.UsuarioRegistroDTO;
import Iudigital.RollerSpeed.Modelo.Rol;
import Iudigital.RollerSpeed.Repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements Iudigital.RollerSpeed.Servicio.UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio,
                               BCryptPasswordEncoder passwordEncoder,
                               Iudigital.RollerSpeed.Repositorio.RolRepositorio rolRepositorio, RolRepositorio rolRepositorio1) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.rolRepositorio = rolRepositorio1;
    }

    @Override
    public Usuario registrarUsuario(UsuarioRegistroDTO registroDTO) {
        String nombreRol = registroDTO.getRol();

        Rol rolSeleccionado = rolRepositorio.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol " + nombreRol + " no encontrado"));

        Usuario usuario = new Usuario(
                registroDTO.getNombre(),
                registroDTO.getApellido(),
                registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                List.of(rolSeleccionado)
        );

        return usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return List.of();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return null;
    }

    @Override
    public boolean existePorEmail(String email) {
        return false;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(
                usuario.get().getEmail(),
                usuario.get().getPassword(),
                mapearAutoridadesRoles(usuario.get().getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        return registrarUsuario(registroDTO);
    }

    //@Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public RolRepositorio getRolRepositorio() {
        return rolRepositorio;
    }
}