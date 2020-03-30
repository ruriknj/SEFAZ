package TesteEntityManagerUtil;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import dao.PessoaDAOImp;
import entidade.Pessoa;
import util.EntityManagerUtil;



public class TesteEntityManagerUtil {
	
	@Test
	public void connectionTest() {
		
		EntityManagerUtil.getEntityManager();
		
	}		

}