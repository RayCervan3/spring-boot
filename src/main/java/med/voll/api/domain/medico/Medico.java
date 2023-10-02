package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DireccionMedicos;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private DireccionMedicos direccion;
    private Boolean activo;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new DireccionMedicos(datosRegistroMedico.direccion());
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.email() != null) {
            this.email = datosActualizarMedico.email();
        }
        if (datosActualizarMedico.dirrecion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.dirrecion());
        }

    }

    public void desactivarMedico() {
        this.activo =  false;
    }
}
