package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {


	@PostMapping
	public void regidtraMedico(@RequestBody String parametro){
		System.out.println("El reques llega correcta mente");
		System.out.println(parametro);
		
	}
}
