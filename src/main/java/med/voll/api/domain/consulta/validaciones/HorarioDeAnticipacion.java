package med.voll.api.domain.consulta.validaciones;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta dato){

        var ahora = LocalDateTime.now();
        var horaDeConsulta = dato.fecha();

        var diferenciaDe30min = Duration.between(ahora, horaDeConsulta).toMinutes()<30;

        if(diferenciaDe30min){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación");

        }
    }
}
