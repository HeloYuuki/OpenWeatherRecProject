package br.com.openWeatherRec.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.openWeatherRec.model.Localidade;

public interface LocalidadeRepository extends JpaRepository<Localidade, Long>{

	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Localidade u where u.id = :id")
	void removerLocalidade(Long id);
}
