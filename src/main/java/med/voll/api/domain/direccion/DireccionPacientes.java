package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionPacientes {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;
    private String urbanizacion;
    private String codigoPostal;
    private String provincia;

    public DireccionPacientes(DatosDireccionPaciente direccionPaciente) {
        this.calle = direccionPaciente.calle();
        this.numero = direccionPaciente.numero();
        this.complemento = direccionPaciente.complemento();
        this.distrito = direccionPaciente.distrito();
        this.ciudad = direccionPaciente.ciudad();
        this.urbanizacion = direccionPaciente.urbanizacion();
        this.codigoPostal = direccionPaciente.codigoPostal();
        this.provincia = direccionPaciente.provincia();
    }

    public DireccionPacientes atualizarInformacion(DatosDireccionPaciente direccionPaciente) {
        this.calle = direccionPaciente.calle();
        this.numero = direccionPaciente.numero();
        this.complemento = direccionPaciente.complemento();
        this.distrito = direccionPaciente.distrito();
        this.ciudad = direccionPaciente.ciudad();
        this.urbanizacion = direccionPaciente.urbanizacion();
        this.codigoPostal = direccionPaciente.codigoPostal();
        this.provincia = direccionPaciente.provincia();
        return this;
    }
}
