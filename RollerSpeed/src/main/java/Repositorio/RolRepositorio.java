package Iudigital.RollerSpeed.Repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface RolRepositorio extends CrudRepository<Iudigital.RollerSpeed.Modelo.Rol, Long> {
    Optional<Iudigital.RollerSpeed.Modelo.Rol> findByNombre(String nombre);
}
