package Iudigital.RollerSpeed.Servicio;

import java.util.List;

import Iudigital.RollerSpeed.Modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import Iudigital.RollerSpeed.Controlador.dto.UsuarioRegistroDTO;

public interface UsuarioServicio extends UserDetailsService {

    Usuario guardar(UsuarioRegistroDTO registroDTO);

    List<Usuario> listarUsuarios();

    /**
     * Registra un nuevo usuario en el sistema
     * @param registroDTO Datos del usuario a registrar
     * @return Usuario registrado
     * @throws IllegalArgumentException Si los datos son inválidos
     * @throws IllegalStateException Si el email ya está registrado
     */
    Usuario registrarUsuario(UsuarioRegistroDTO registroDTO)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Obtiene todos los usuarios registrados
     * @return Lista de usuarios ordenados por fecha de creación
     */
    List<Usuario> obtenerTodosUsuarios();

    /**
     * Busca un usuario por su email
     *
     * @param email Email del usuario a buscar
     * @return Optional con el usuario si existe
     */
    Usuario buscarPorEmail(String email);

    /**
     * Verifica si existe un usuario con el email especificado
     * @param email Email a verificar
     * @return true si existe, false si no
     */
    boolean existePorEmail(String email);

    /**
     * Actualiza la información de un usuario
     * @param usuario Usuario con los datos actualizados
     * @return Usuario actualizado
     */
    Usuario actualizarUsuario(Usuario usuario);

    /**
     * Elimina un usuario por su ID
     * @param id ID del usuario a eliminar
     */
    void eliminarUsuario(Long id);
}