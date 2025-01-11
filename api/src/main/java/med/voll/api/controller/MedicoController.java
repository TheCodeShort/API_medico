package med.voll.api.controller;

import med.voll.api.medico.DatosRegistrosMedicos;
import med.voll.api.medico.IMedicoRepositori;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicos")


public class MedicoController {
	@Autowired//no es bueno en el testing buscar
	private IMedicoRepositori iMedicoRepositori;

	@PostMapping
	public void regidtraMedico(@RequestBody @Valid  DatosRegistrosMedicos datosRegistrosMedicos){// para que reconosca el body
		iMedicoRepositori.save(new Medico(datosRegistrosMedicos));

		
	}
}
