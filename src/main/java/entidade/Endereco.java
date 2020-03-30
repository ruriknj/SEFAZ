package entidade;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Rurik
 * 
 * Classe Endereço utilizou como referencia a tabela Pessoa, onde faz um para muitos.
 * Nesta classe tem uma coisa em particular, ela tem um ID, onde o mesmo é autoincrmentado
 * pelo proprio JPA com a sequencia S_ENDERECO.Isto para o Banco Oracle.
 * Para usar o banco HSQLDB não utilizaremos esta seuqencia pois o banco faz auto incremento no ID. 
 *
 */

@Entity
@Table(name = "ENDERECO")
public class Endereco {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ENDERECO")
	// @SequenceGenerator(name = "S_ENDERECO", sequenceName = "S_ENDERECO", allocationSize = 1)
	private long id;
	
	@Column(name="numero") 
	private int numero;
	
	@Column(name="rua") 
	private String rua;
	
	@Column(name="Complemento") 
	private String Complemento;

	@ManyToOne
	@JoinColumn(name = "cpf_pessoa", referencedColumnName = "cpf", nullable = false)
	private Pessoa  pessoa;

	
	public long getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}
	
}
