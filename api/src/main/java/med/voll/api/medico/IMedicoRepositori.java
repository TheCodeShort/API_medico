package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface IMedicoRepositori extends JpaRepository <Medico, Long> {

	static Optional<Object> findAll(Pageable paginacion) {
	}
}
