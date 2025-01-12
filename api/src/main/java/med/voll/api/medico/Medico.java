package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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
	private  Boolean activo;

	public Medico(DatosRegistrosMedicos datosRegistrosMedicos) {
		this.nombre = datosRegistrosMedicos.nombre();
		this.email = datosRegistrosMedicos.email();
		this.telefono = datosRegistrosMedicos.telefono();
		this.documentos= datosRegistrosMedicos.documento();
		this.especialidad = datosRegistrosMedicos.especialidad();
		this.direccion =  new Direccion(datosRegistrosMedicos.direccion());
		this.activo = true;
	}

	public void actualizarDatos(@RequestBody  @Valid DatosActualizarMedico datosActualizarMedico) {
		if (datosActualizarMedico.nombre() != null) {
			this.nombre = datosActualizarMedico.nombre();
		}
		if (datosActualizarMedico.documento() != null) {
			this.documentos = datosActualizarMedico.documento();
		}
		if (datosActualizarMedico.direccion() != null) {
			this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
		}
	}

	public void desactivarMedico(Medico medico) {
		this.activo = false;
	}
}
