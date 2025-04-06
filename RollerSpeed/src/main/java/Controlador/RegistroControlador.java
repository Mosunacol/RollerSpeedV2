package Iudigital.RollerSpeed.Controlador;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Iudigital.RollerSpeed.Servicio.UsuarioServicio;
import Iudigital.RollerSpeed.Controlador.dto.UsuarioRegistroDTO;

// Importaciones de Swagger (Springdoc OpenAPI)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Controller
@Tag(name = "Controlador de Registro y Autenticación",
        description = "Gestiona el registro de usuarios y el acceso al sistema")

public class RegistroControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public RegistroControlador(UsuarioServicio usuarioServicio)
    {
        this.usuarioServicio = usuarioServicio;
    }

    @Operation(summary = "Obtener mensaje de inicio de sesión")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensaje de login exitoso"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })


    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }


    @Operation(summary = "Registrar nuevo usuario",
            description = "Crea un nuevo usuario a partir del DTO enviado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })


    @GetMapping({"/index"})
    public String verPaginaDeInicio(Model modelo) {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute("usuario", new UsuarioRegistroDTO());
        return "/registro";
    }

    @PostMapping(("/registro"))
    public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioServicio.guardar(registroDTO);
        return "redirect:/registro?exito";
    }
}