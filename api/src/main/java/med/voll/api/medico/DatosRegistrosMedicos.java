package med.voll.api.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

import javax.validation.Valid;

public record DatosRegistrosMedicos(@NotBlank String nombre,
                                    @NotBlank @Email String email,
                                    @NotBlank @Pattern(regexp = "\\d{4,6}") String documento,
                                    @NotBlank Especialidad especialidad,
                                    @NotNull @Valid  DatosDireccion direccion) {
}
