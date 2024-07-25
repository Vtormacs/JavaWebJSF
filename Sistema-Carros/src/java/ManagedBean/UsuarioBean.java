package ManagedBean;

import DAO.UsuarioDAO;
import Entity.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioBean extends CrudBean<Usuario, UsuarioDAO> {

    private UsuarioDAO usuarioDAO;

    @Override
    public UsuarioDAO getDao() {
        if (usuarioDAO == null) { // caso o meu usuarioDAO estiver vazio eu crio ele dnv
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();
    }

}
