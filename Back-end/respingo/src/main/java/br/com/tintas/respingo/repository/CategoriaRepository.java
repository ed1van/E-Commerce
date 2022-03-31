package br.com.tintas.respingo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tintas.respingo.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
}
