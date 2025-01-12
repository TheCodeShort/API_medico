package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;
//tener encuaenta que el ID no puede ser nulo asi que utilizamos @NotNull por que es de tipo LONG si fuera estring usariamos @NotBlank
public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}
