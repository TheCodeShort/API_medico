package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/medicos")


public class MedicoController {
	@Autowired//no es bueno en el testing buscar
	private IMedicoRepositori iMedicoRepositori;

	@PostMapping
	public void regidtraMedico(@RequestBody @Valid  DatosRegistrosMedicos datosRegistrosMedicos){// para que reconosca el body
		iMedicoRepositori.save(new Medico(datosRegistrosMedicos));
	}

	//con esto podemo organiar la inforacion el JSON para no mostrar infroacion
	//y si juegamos con la URL nos ayudamos para mostrar cierta inforacion lo bueno de usar (Pageable)
	//@PageableDefault sobreescribe los datos de spring en el JSON size es uno datos que nos da spring (Pageable)
	@GetMapping
	public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
		return iMedicoRepositori.findAll(paginacion).map(DatosListadoMedico::new);
	}
	//
	@PutMapping
	@Transactional//cuando se termine el metodo la transaccion se va  aliberer, lo que hace es un commit a nivel de base de datos y se guardan los datos
	public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
		//con esto decimos que el cliente me envia el id y despues lo busca en la base de datos
		Medico medico = iMedicoRepositori.getReferenceById(datosActualizarMedico.id());
		medico.actualizarDatos(datosActualizarMedico);
	}

	// DELETE LOGICO
	@DeleteMapping("/{id}")//de esta manera lo hacemos mas dinamico, osea podemos eliminar cual quier ID
	@Transactional
	public void eliminarMedico(@PathVariable Long id) {
		Medico medico = iMedicoRepositori.getReferenceById(id);
		medico.desactivarMedico();
	}

//    DELETE EN BASE DE DATOD
//    public void eliminarMedico(@PathVariable Long id) {
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }
}
