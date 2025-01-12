package med.voll.api.domain.medico;

public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {
		// al agregar el ID ya podemos identificar los datos repetidos como el mismo nombre pero con cedula diferente
	public DatosListadoMedico(Medico medico) {
		this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumentos(), medico.getEmail());
	}
}


