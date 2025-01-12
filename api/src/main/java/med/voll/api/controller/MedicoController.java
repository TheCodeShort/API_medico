package med.voll.api.controller;

import med.voll.api.medico.DatosListadoMedico;
import med.voll.api.medico.DatosRegistrosMedicos;
import med.voll.api.medico.IMedicoRepositori;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;

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
		return IMedicoRepositori.findAll(paginacion).map(DatosListadoMedico::new);
	}
}
