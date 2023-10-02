package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccionPaciente;

public record DatosRespuestaPaciente(Long id, String nombre, String email, String telefono, String documentoIdentidad, DatosDireccionPaciente dirrecion) {
}
