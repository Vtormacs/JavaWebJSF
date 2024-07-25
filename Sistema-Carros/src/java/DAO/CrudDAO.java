package DAO;

import Util.ErroSistema;
import java.util.List;

//Crug generico

public interface CrudDAO<E> { // O <E> Representa a minha entidade

    public void salvar(E entidade) throws ErroSistema;

    public void deletar(E entidade) throws ErroSistema;

    public List<E> buscar() throws ErroSistema;

}
