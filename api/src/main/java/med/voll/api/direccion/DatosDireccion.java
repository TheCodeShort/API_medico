package med.voll.api.direccion;
import javax.validation.constraints.NotBlank;
public record DatosDireccion(@NotBlank String calle,
                             @NotBlank String distrito,
                             @NotBlank String ciudad,
                             @NotBlank String numero,
                             @NotBlank String complemento ) {
}
