package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DireccionPacientes;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    private Boolean activo;

    @Embedded
    private DireccionPacientes direccion;

    public Paciente(DatosRegistroPaciente datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new DireccionPacientes(datos.direccion());
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null)
            this.nombre = datosActualizarPaciente.nombre();

        if (datosActualizarPaciente.telefono() != null)
            this.telefono = datosActualizarPaciente.telefono();

        if (datosActualizarPaciente.dirrecion() != null)
            direccion.atualizarInformacion(datosActualizarPaciente.dirrecion());
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
