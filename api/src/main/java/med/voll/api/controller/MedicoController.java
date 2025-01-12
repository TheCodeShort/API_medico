package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")


public class MedicoController {
	@Autowired//no es bueno en el testing buscar
	private IMedicoRepositori iMedicoRepositori;

	@PostMapping
	//UriComponentsBuilder nos permite crear uris de manera dinamica y ensegida se le pone el nombre de la variable
	public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistrosMedicos datosRegistroMedico,
	                                                            UriComponentsBuilder uriComponentsBuilder) {
		Medico medico = iMedicoRepositori.save(new Medico(datosRegistroMedico));
		DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento()));
		//Este método se utiliza para iniciar la construcción de una URI
		// buildAndExpand: Este método se utiliza para reemplazar los marcadores de posición en el path con valores reales
		//toUri: Finalmente, este método convierte el objeto UriComponentsBuilder en una instancia de URI, que es el formato que necesitas para devolver en la respuesta de tu API.
		//
		URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(url).body(datosRespuestaMedico);

	}

	//con esto podemo organiar la inforacion el JSON para no mostrar infroacion
	//y si juegamos con la URL nos ayudamos para mostrar cierta inforacion lo bueno de usar (Pageable)
	//@PageableDefault sobreescribe los datos de spring en el JSON size es uno datos que nos da spring (Pageable)
	@GetMapping
	public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
		//return iMedicoRepositori.findAll(paginacion).map(DatosListadoMedico::new);
		return ResponseEntity.ok(iMedicoRepositori.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
	}
	//
	@PutMapping
	@Transactional//cuando se termine el metodo la transaccion se va  aliberer, lo que hace es un commit a nivel de base de datos y se guardan los datos
	public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		//con esto decimos que el cliente me envia el id y despues lo busca en la base de datos
		Medico medico = iMedicoRepositori.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
		return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento())));
	}

	// DELETE LOGICO
	@DeleteMapping("/{id}")//de esta manera lo hacemos mas dinamico, osea podemos eliminar cual quier ID
	@Transactional
	public ResponseEntity eliminarMedico(@PathVariable Long id) {/*Cuando utilizas @PathVariable en tu método,
														 lo que estás haciendo es capturar el valor que se pasa en la
														 URL y usarlo como un parámetro en tu método*/
		Medico medico = iMedicoRepositori.getReferenceById(id);
		medico.desactivarMedico();
		return ResponseEntity.noContent().build();//con esto retornamos codigo HTTP y para que retorne hay que poner un build()
													//ResponseEntity me ayuda a personalizar las respuesta HTTP
	}

	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
		Medico medico = iMedicoRepositori.getReferenceById(id);
		var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
				medico.getTelefono(), medico.getEspecialidad().toString(),
				new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
						medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
						medico.getDireccion().getComplemento()));
		return ResponseEntity.ok(datosMedico);
	}

//    DELETE EN BASE DE DATOD
//    public void eliminarMedico(@PathVariable Long id) {
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }
}
