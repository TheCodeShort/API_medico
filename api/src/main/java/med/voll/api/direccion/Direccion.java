package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //libreria de lombo
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Direccion {
	private String calle;
	private String numero;
	private String complemento;
	private String distrito;
	private String ciudad;



}
