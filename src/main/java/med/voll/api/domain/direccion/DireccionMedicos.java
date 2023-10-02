package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionMedicos {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;

    public DireccionMedicos(DatosDireccionMedico direccionMedico) {
        this.calle = direccionMedico.calle();
        this.numero = direccionMedico.numero();
        this.complemento = direccionMedico.complemento();
        this.distrito = direccionMedico.distrito();
        this.ciudad = direccionMedico.ciudad();
    }

    public DireccionMedicos actualizarDatos(DatosDireccionMedico direccionMedico) {
        this.calle = direccionMedico.calle();
        this.numero = direccionMedico.numero();
        this.complemento = direccionMedico.complemento();
        this.distrito = direccionMedico.distrito();
        this.ciudad = direccionMedico.ciudad();
        return this;
    }
}
