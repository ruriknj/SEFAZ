package controle;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.PessoaDAO;
import dao.PessoaDAOImp;
import entidade.Pessoa;
import util.EntityManagerUtil;
/**
 * 
 * @author Rurik
 *	LoginBean, classe responsavel pela logica de logar no sistema
 *
 */

@ManagedBean(name="LoginBean")
@RequestScoped
public class LoginBean {

	//Essas variaveis são responsaveis para o acesso geral, o admin
	private String usarioAdmin = "admin";
	private String senhaAdmin = "admin";
	
	private String usuarioTXT;
	private String senhaTXT;
	
	private static final String PESQUISAR = "paginas/pessoa/pesquisarPessoa.xhtml"; 
	private PessoaDAO pessoaDAO;
	private String mensagem;
	
	public LoginBean() { 
	
		this.pessoaDAO = new PessoaDAOImp(EntityManagerUtil.getEntityManager());
	} 

	public void entrar() throws IOException {
		if(this.usuarioTXT.equals(this.usarioAdmin)
		  && this.senhaTXT.equals(this.senhaAdmin)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);
		} else { 
			Pessoa pessoaPesquisa = this.pessoaDAO.pesquisar(usuarioTXT);
			if(pessoaPesquisa != null) {
				}else { 
					this.mensagem = "Usuario, senha errada";
				}

				this.mensagem = "Pessoa ou Senha errada";
			
			}
		}	

	public String getUsuarioTXT() {
		return usuarioTXT;
	}

	public void setUsuarioTXT(String usuarioTXT) {
		this.usuarioTXT = usuarioTXT;
	}

	public String getSenhaTXT() {
		return senhaTXT;
	}

	public void setSenhaTXT(String senhaTXT) {
		this.senhaTXT = senhaTXT;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	} 
}
