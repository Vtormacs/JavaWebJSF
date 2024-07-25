package ManagedBean;

import DAO.CarroDAO;
import Entity.Carro;
import Util.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CarroBean {

    private Carro carro = new Carro();
    private List<Carro> carros = new ArrayList<>();
    private CarroDAO carroDAO = new CarroDAO();

    public void salvarCarro() {
        try {
            carroDAO.salvar(carro);
            carro = new Carro(); // Limpa o formulário após adicionar
            carros = carroDAO.listar();
            adicionarMensagem("Salvo!", "Carro salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void listarCarro() {
        try {
            carros = carroDAO.listar();
            if(carros ==  null || carros.size() == 0){
             adicionarMensagem("Nenhum Dado Encontrado!", "Sua Buscar Não Retornou Nenhum Carro", FacesMessage.SEVERITY_WARN);
            }
            adicionarMensagem("Lista!", "Lista criada com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void deletar(Carro c) {
        try {
            carroDAO.deletar(c.getId());
            carros = carroDAO.listar();
            adicionarMensagem("Deletado!", "Carro deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editarCarro(Carro c) {
        carro = c;
    }

    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
