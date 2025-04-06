package Iudigital.RollerSpeed.Servicio;

import Iudigital.RollerSpeed.Modelo.Curso;
import Iudigital.RollerSpeed.Repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoServicio {

    private final CursoRepositorio cursoRepositorio;

    @Autowired
    public CursoServicio(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    public List<Curso> obtenerCursosDisponibles() {
        return cursoRepositorio.findAll(); // Obtiene los 3 cursos  en PostgreSQL
    }
}
