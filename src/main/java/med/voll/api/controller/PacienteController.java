package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccionPaciente;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity<DatosRespuestaPaciente> registrar(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(),
                new DatosDireccionPaciente(paciente.getDireccion().getCalle(), paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getUrbanizacion(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getProvincia()));

        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado para los pacientes")
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion ){
        return ResponseEntity.ok(pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(),
                new DatosDireccionPaciente(paciente.getDireccion().getCalle(), paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getUrbanizacion(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getProvincia())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un paciente a partir del ID")
    public ResponseEntity eliminarPaciente(@PathVariable Long id ){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtiene los detalles para el paciente con el ID indicado")
    public ResponseEntity<DatosRespuestaPaciente> retornaDatosMedico(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(),
                new DatosDireccionPaciente(paciente.getDireccion().getCalle(), paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getUrbanizacion(),
                        paciente.getDireccion().getCodigoPostal(), paciente.getDireccion().getProvincia()));
        return ResponseEntity.ok(datosPaciente);
    }

}
