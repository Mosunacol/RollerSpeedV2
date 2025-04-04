package Iudigital.RollerSpeed.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Iudigital.RollerSpeed.Modelo.Usuario, Long> {

    @NonNull
    Optional<Iudigital.RollerSpeed.Modelo.Usuario> findByEmail(String email);

    @NonNull
    List<Iudigital.RollerSpeed.Modelo.Usuario> findAll();

    boolean existsByEmail(String email);
}

