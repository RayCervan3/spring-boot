package med.voll.api.domain.direccion;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DatosDireccionPaciente(

        @NotBlank
        String calle,

        @NotBlank
        String distrito,

        @NotBlank
        String ciudad,

        @NotBlank
        String numero,

        @NotBlank
        String urbanizacion,

        @NotBlank
        @Column(name="codigoPostal")
        String codigoPostal,

        @NotBlank
        String provincia,

        @NotBlank
        String complemento) {

}
