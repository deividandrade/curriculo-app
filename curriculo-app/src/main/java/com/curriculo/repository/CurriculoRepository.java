package com.curriculo.repository;

import com.curriculo.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
	Optional<Curriculo> findByEmail(String email);

	boolean existsByEmail(String email);
}
