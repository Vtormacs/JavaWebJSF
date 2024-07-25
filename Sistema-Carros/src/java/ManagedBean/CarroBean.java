package ManagedBean;

import DAO.CarroDAO;
import Entity.Carro;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarroBean extends CrudBean<Carro, CarroDAO> {

    private CarroDAO carroDAO;

    @Override
    public CarroDAO getDao() {
        if (carroDAO == null) { // caso o meu carroDAO estiver vazio eu crio ele dnv
            carroDAO = new CarroDAO();
        }
        return carroDAO;
    }

    @Override
    public Carro criarNovaEntidade() {
        return new Carro();
    }
}
