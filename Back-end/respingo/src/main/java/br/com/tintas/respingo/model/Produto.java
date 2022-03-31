package br.com.tintas.respingo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Esse campo não pode ficar em branco")
	@Size(max = 100, message = "O máximo de 100 caracteres")
	private String nome;

	@NotBlank(message = "Esse campo não pode ficar em branco")
	@Size(max = 1000, message = "O máximo de 1000 caracteres")
	private String descricao;

	@NotBlank
	private String cor;

	/**
	 * A anotação @JsonFormat(shape = JsonFormat.Shape.STRING) é utilizada para
	 * formatar o valor do preço do produto como uma String. Desta forma,
	 * conseguiremos visualizar a parte decimal do preço mesmo sendo 00.
	 */

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "Digite um valor maior do que zero")
	private BigDecimal preco;

	@NotNull //int, long e bigDecimal só aceita @notnull e nao notBlank
	private int quantidade;

	@NotBlank
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Usuario usuario;
	
}
