package Iudigital.RollerSpeed.Repositorio;

import Iudigital.RollerSpeed.Modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}


