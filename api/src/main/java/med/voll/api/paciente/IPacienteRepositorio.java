package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepositorio extends JpaRepository<Paciente, Long> {
}
