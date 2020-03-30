package dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Pessoa;


public class  PessoaDAOImp implements PessoaDAO {
	
	private EntityManager ent;
	
	public PessoaDAOImp(EntityManager ent ) {
		
		this.ent = ent;
	}

	//@Override
	public boolean inserir(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(pessoa);
		tx.commit();
		ent.getFlushMode();
	

		return true;
		
	}

	//@Override
	public void alterar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(pessoa);
		tx.commit();
	
		
	}

	//@Override
	public void remover(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(pessoa);
		tx.commit();
		
		
	}

	//verride
	public Pessoa pesquisar (String cpf) {
		// TODO Auto-generated method stub
		
		Pessoa pessoa = ent.find(Pessoa.class, cpf);
		
		return pessoa;
	}

	//@Override
	public List<Pessoa> listarTodos() {
		// TODO Auto-generated method stub
		
		Query query = ent.createQuery("from Pessoa p");
		
		List<Pessoa> pessoas = query.getResultList();
	
		return pessoas;
	}

}
