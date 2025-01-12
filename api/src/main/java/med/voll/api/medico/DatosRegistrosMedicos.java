package med.voll.api.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

import javax.validation.Valid;
//estas anotaciones pertenecen a la independencia (VALIDATION)
public record DatosRegistrosMedicos(@NotBlank String nombre,//valida que nombre no llegue blanco on en el caso de NotNull que no llegue nulo
                                    @NotBlank @Email String email,//valida que sea un correo no blanco
                                    @NotBlank String telefono,
                                    @NotBlank @Pattern(regexp = "\\d{4,6}") String documento,
                                    @NotNull Especialidad especialidad,
                                    @NotNull @Valid  DatosDireccion direccion) {
}
