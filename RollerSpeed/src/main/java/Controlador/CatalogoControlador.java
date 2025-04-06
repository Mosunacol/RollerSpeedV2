package Iudigital.RollerSpeed.Controlador;

import Iudigital.RollerSpeed.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogo")
public class CatalogoControlador {

    private final CursoServicio cursoServicio;

    @Autowired
    public CatalogoControlador(CursoServicio cursoServicio) {
        this.cursoServicio = cursoServicio;
    }

    @GetMapping
    public String mostrarCatalogo(Model model) {
        var cursos = cursoServicio.obtenerCursosDisponibles();
        System.out.println("Cursos encontrados: " + cursos.size());
        model.addAttribute("cursos", cursos);
        return "catalogo/lista";
    }
}
