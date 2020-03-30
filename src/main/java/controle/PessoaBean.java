package controle;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import util.EntityManagerUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.IIOException;

import dao.PessoaDAO;
import dao.PessoaDAOImp;
import entidade.Endereco;
import entidade.Pessoa;
/*
 * @author: Rurik Nicolau Janiszewski.
 *         Classe responsavel por controlar as telas de consultar e guardar.
 */

@ManagedBean(name = "PessoaBean")
@SessionScoped
public class PessoaBean {

	private Pessoa pessoa;
	private Endereco endereco;
	private List<Pessoa> listaPessoa;
	private String cpfSelecionado;

	// Interfase do usuarioDAO
	private PessoaDAO pessoaDAO;

	private static final String GUARDAR = "guardarPessoa.xhtml";
	private static final String PESQUISAR = "pesquisarPessoa.xhtml";
	
	public PessoaBean() {
		
		this.pessoa = new Pessoa();
		this.pessoa.setEnderecos(new ArrayList<Endereco>());

		this.endereco = new Endereco();
		this.listaPessoa = new ArrayList<Pessoa>();
		
		this.pessoaDAO = new PessoaDAOImp(EntityManagerUtil.getEntityManager());
		
		this.listaPessoa = this.pessoaDAO.listarTodos();
		
		System.out.println(this.listaPessoa);
		
	}
		
		public void pesquisar() {
			
			this.listaPessoa = this.pessoaDAO.listarTodos();
			System.out.println("Entrou Pesquisar ====");
		}
		
		public void salvar( ) throws IOException {
			
			if (this.pessoaDAO.inserir(this.pessoa)) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO,"","Sucesso !!!")); 
				
					abrirPesquisarPessoa();
					
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));	
			}
			
		}
		
		public void adicionarEndereco() {
			
			if(!this. existeEndereco(endereco))  {
				
				Endereco enderecoNova = new Endereco();
				
				enderecoNova.setRua(this.endereco.getRua());
				enderecoNova.setNumero(this.endereco.getNumero());
				enderecoNova.setComplemento(this.endereco.getComplemento());
				enderecoNova.setPessoa(pessoa);
				
				this.pessoa.getEnderecos().add(enderecoNova);
				
				this.endereco = new Endereco();
			}
			
			else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "cpf já existe !!!"));
				
			}
		}
/*		
*/
		private boolean existeEndereco(Endereco endereco) {
			
			boolean retorno = false;
		
			for(Endereco endLista : this.pessoa.getEnderecos()) {
				
				if(endLista.getNumero() == endereco.getNumero() &&
				endLista.getRua().equals(endereco.getRua())) {
					
					return true;
				}
			}
			return retorno;
		}
	
		public void abrirGuardarPessoa() throws IOException {
			FacesContext.getCurrentInstance().getExternalContext().redirect(GUARDAR);
			}
			
		public void abrirPesquisarPessoa() throws IOException {
				FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
			}

		public void editar() throws IOException {
			Pessoa pessoaEdicao = this.pessoaDAO.pesquisar(cpfSelecionado);
			this.pessoa = pessoaEdicao;
			
			abrirGuardarPessoa();
		}
		
		public String remover() {
			
			Pessoa pessoaRemocao = this.pessoaDAO.pesquisar(cpfSelecionado);
			this.pessoaDAO.remover(pessoaRemocao);
			this.listaPessoa = this.pessoaDAO.listarTodos();
			return "";
		}
		
		public void limpar() {
			this.listaPessoa = null;
			this.listaPessoa = new ArrayList<Pessoa>();
	
			this.listaPessoa.clear();
			}
		
		
		public void clear() {
			
			Endereco endClear = new Endereco();
			Pessoa pesClear = new Pessoa();
			
			
			endClear.setNumero(0);
			endClear.setRua(null);
			endClear.setComplemento(null);
			
			pesClear.setNome(null);
			pesClear.setCpf(null);
			pesClear.setSexo(null);
			pesClear.setIdade(0);
			
		}

		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}

		public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

		public List<Pessoa> getListaPessoa() {
			return listaPessoa;
		}

		public void setListaPessoa(List<Pessoa> listaPessoa) {
			this.listaPessoa = listaPessoa;
		}

		public String getCpfSelecionado() {
			return cpfSelecionado;
		}

		public void setCpfSelecionado(String cpfSelecionado) {
			this.cpfSelecionado = cpfSelecionado;
		}

}
		

