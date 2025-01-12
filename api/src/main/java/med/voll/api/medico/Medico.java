package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "medicos")//persistencia de datos JPA
@Entity(name = "Medico")//JPA
@Getter //libreria de lombo
@NoArgsConstructor//LOMBO
@AllArgsConstructor//LOMBO
@EqualsAndHashCode(of = "id")//LOMBO

public class Medico {
	@Id//JPA
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String telefono;
	private String documentos;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	@Embedded
	private Direccion direccion;

	public Medico(DatosRegistrosMedicos datosRegistrosMedicos) {
		this.nombre = datosRegistrosMedicos.nombre();
		this.email = datosRegistrosMedicos.email();
		this.telefono = datosRegistrosMedicos.telefono();
		this.documentos= datosRegistrosMedicos.documento();
		this.especialidad = datosRegistrosMedicos.especialidad();
		this.direccion =  new Direccion(datosRegistrosMedicos.direccion());
	}

}
