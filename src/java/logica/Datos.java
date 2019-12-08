package logica;

import com.google.gson.Gson;
import dto.Relacion;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Datos {

    private static Datos instance;
    private List<Usuario> usersList;
    private List<Relacion> relationsList;
    private int currentId;

    public static Datos getInstance() {
        if (instance == null)
            instance = new Datos();
        
        return instance;
    }

    private Datos() {
        usersList = new ArrayList<>();
        relationsList = new ArrayList<>();
        currentId = 0;
        
        Usuario tefor = new Usuario(getNextId(), "Nico Duran", "tefor", "nico", 50, "token");
        Usuario ricky = new Usuario(getNextId(), "Ricardo Paz", "ricky", "123", 50, "token");
        Usuario belu = new Usuario(getNextId(), "Sofia Duran", "belu", "123", 50, "token");
        Usuario andy = new Usuario(getNextId(), "Andrea Zarco", "andy", "123", 230, "token");
        
        usersList.add(tefor);
        usersList.add(ricky);
        usersList.add(belu);
        usersList.add(andy);
        
        agregarAmigo(tefor, ricky);
        agregarAmigo(tefor, belu);
    }
    
    public int getNextId() {
        return ++currentId;
    }

    public String crearCuenta(Usuario usuario) {
        // si se encontro un usuario con ese username
        if (getUser(usuario.getUsername()) != null)
            return "0";
        
        usuario.setId(getNextId());
        usersList.add(usuario);
        
        return new Gson().toJson(usuario);
    }
    
    public String ingresar(Usuario usuario) {
        Usuario objUser = getUser(usuario.getUsername());
        
        if (objUser == null)
            return "wrong_username";
        
        if ( !objUser.getPassword().equals(usuario.getPassword()) )
            return "wrong_password";
        
        return new Gson().toJson(objUser);
    }
    
    public Usuario getUser(String username) {
        for (Usuario user : usersList) {
            if (user.getUsername().equals(username))
                return user;
            
        }
        
        return null;
    }
    
    public void agregarAmigo(Usuario userSend, Usuario userReceiver) {
        Usuario objUserReceiver = getUser(userReceiver.getUsername());
        if (objUserReceiver == null) {
            return;
        }
        
        relationsList.add(new Relacion(0, userSend.getUsername(), userReceiver.getUsername()));
        relationsList.add(new Relacion(0, userReceiver.getUsername(), userSend.getUsername()));
    }
    
    public void eliminarAmigo(Usuario user, Usuario friendToRemove) {
        String userName = user.getUsername();
        String friendUserName = friendToRemove.getUsername();
        
        for (int i = 0; i < relationsList.size(); i++) {
            Relacion relation = relationsList.get(i);
            
            if (relation.getUserName().equals(userName) &&
                    relation.getFriendUserName().equals(friendUserName)) {
                relationsList.remove(i);
            }
            
            if (relation.getUserName().equals(friendUserName) &&
                    relation.getFriendUserName().equals(userName)) {
                relationsList.remove(i);
            }
        }
    }
    
    public void actualizarRecord(Usuario usuario, int newRecord) {
        Usuario objUser = getUser(usuario.getUsername());
        objUser.setRecord(newRecord);
    }

    public List<Usuario> amigosDe(Usuario objUsuario) {
        List<Usuario> friendList = new ArrayList<>();
        
        for (Relacion relation : relationsList) {
            if ( relation.getUserName().equals(objUsuario.getUsername()) ) {
                Usuario friend = getUser(relation.getFriendUserName());
                friendList.add(friend);
            }
            
        }
        
        return friendList;
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public List<Usuario> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Usuario> usersList) {
        this.usersList = usersList;
    }

    public List<Relacion> getRelationsList() {
        return relationsList;
    }

    public void setRelationsList(List<Relacion> relationsList) {
        this.relationsList = relationsList;
    }
    
    // </editor-fold>

}