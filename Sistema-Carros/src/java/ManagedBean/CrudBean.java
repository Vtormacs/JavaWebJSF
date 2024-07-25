package ManagedBean;

import DAO.CrudDAO;
import Util.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDAO> {

    private String estadoTela = "buscar"; //tipos de estado de tela(inserir, editar, buscar).

    private E entidade;
    private List<E> entidades;

    public void novo() {// zera a entidade e muda o estado para inserir
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }

    public void salvar() {
        try {
            getDao().salvar(entidade);//chamei o salvar passando a entidade como parametro, meu getDao q estende o CrudDAO que tem implementações genericas que implemnta no meu CarroDAO
            entidade = criarNovaEntidade();// zerei a entidade
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();//mudei o estado da tela para buscar q vai ser o padrao da tela
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editar(E entidade) {
        this.entidade = entidade;// recebi um entidade e passei ela para a entidade local dessa classe
        mudarParaEdita();//mudei o estado da tela para editar
    }

    public void deletar(E entidade) {
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);//removi a entidade passanda da lista entidades
            adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void buscar() {
        if (isBusca() == false) {
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDao().buscar();// entidades é do tipo List e estou populando essa lista com o buscar da crudDAO que é uma lista  
            if (entidades == null || entidades.size() < 1) {
                adicionarMensagem("Não temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

    //Responsalvel por criar metodos nas classes bean
    public abstract D getDao();

    public abstract E criarNovaEntidade();

    //Metotos para controle da tela
    public boolean isInseri() {
        return "inserir".equals(estadoTela);
    }

    public boolean isEdita() {
        return "editar".equals(estadoTela);
    }

    public boolean isBusca() {
        return "buscar".equals(estadoTela);
    }

    public void mudarParaInseri() {
        estadoTela = "inserir";
    }

    public void mudarParaEdita() {
        estadoTela = "editar";
    }

    public void mudarParaBusca() {
        estadoTela = "buscar";
    }
}
