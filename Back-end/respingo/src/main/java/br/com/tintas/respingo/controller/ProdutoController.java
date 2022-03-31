package br.com.tintas.respingo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tintas.respingo.model.Produto;
import br.com.tintas.respingo.repository.ProdutoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<List<Produto>> getNome (@PathVariable String nome)
	{
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("cor/{cor}")
	public ResponseEntity<List<Produto>> getCor (@PathVariable String cor)
	{
		return ResponseEntity.ok(repository.findAllByCorContainingIgnoreCase(cor));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postCategoria (@Valid @RequestBody Produto produto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> putCategoria (@Valid @RequestBody Produto produto)
	{
		return repository.findById(produto.getId())
				.map(resp -> ResponseEntity.ok().body(repository.save(produto)))
						.orElse(ResponseEntity.notFound().build());	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> {repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			})
					.orElse(ResponseEntity.notFound().build());
		
	}
	
}
