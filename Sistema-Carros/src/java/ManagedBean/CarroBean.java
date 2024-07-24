package ManagedBean;

import DAO.CarroDAO;
import Entity.Carro;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarroBean {
    
    private Carro carro = new Carro();
    private List<Carro> carros = new ArrayList<>();
    
    public void adicionarCarro() {
        carros.add(carro);
        new CarroDAO().salvar(carro);
        carro = new Carro(); // Limpa o formulário após adicionar
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
