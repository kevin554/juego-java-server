package servlet;

import com.google.gson.Gson;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Datos;

public class Procesador extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String evento = request.getParameter("evento");
        
        try (PrintWriter out = response.getWriter()) {
            switch (evento) {
                case "insertar":
                    crearCuenta(request, out);
                    break;
                
                case "ingresar":
                    ingresar(request, out);
                    break;
                    
                case "agregar_amigo":
                    agregarAmigo(request, out);
                    break;
                    
                case "eliminar_amigo":
                    eliminarAmigo(request, out);
                    break;
                    
                case "amigos":
                    amigosDe(request, out);
                    break;
                    
                case "actualizar":
                    actualizar(request, out);
                    // actualizarRecord(request, out);
                    break;
            }
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void crearCuenta(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        Usuario objUsuario = gson.fromJson(objStr, Usuario.class);
        
        String respuesta = Datos.getInstance().crearCuenta(objUsuario);
        
        out.print(respuesta);
    }

    private void ingresar(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        Usuario objUsuario = gson.fromJson(objStr, Usuario.class);
        
        String respuesta = Datos.getInstance().ingresar(objUsuario);
        
        out.print(respuesta);
    }

    private void agregarAmigo(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        String friendUserName = request.getParameter("friend_user_name");
        
        Usuario objUsuario = gson.fromJson(objStr, Usuario.class);
        Usuario objAmigo = Datos.getInstance().getUser(friendUserName);
        
        if (objUsuario.getUsername().equals(friendUserName)) {
            out.print("cannot_add_itself");
            return;
        }
        
        if (objAmigo == null) {
            out.print("wrong_friend_user_name");
            return;
        }
        
        Datos.getInstance().agregarAmigo(objUsuario, objAmigo);
        out.print("ok");
    }

    private void eliminarAmigo(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objUsuarioStr = request.getParameter("objUsuario");
        String objAmigoStr = request.getParameter("objAmigo");
        
        Usuario objUsuario = gson.fromJson(objUsuarioStr, Usuario.class);
        Usuario objAmigo = gson.fromJson(objAmigoStr, Usuario.class);
        
        Datos.getInstance().eliminarAmigo(objUsuario, objAmigo);
        out.print("ok");
    }

    private void actualizarRecord(HttpServletRequest request, PrintWriter out) {
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void amigosDe(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        Usuario objUsuario = gson.fromJson(objStr, Usuario.class);
        
        List<Usuario> listaAmigos = Datos.getInstance().amigosDe(objUsuario);
        
        out.print(gson.toJson(listaAmigos));
    }

    private void actualizar(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String objStr = request.getParameter("obj");
        Usuario objUsuario = gson.fromJson(objStr, Usuario.class);
        
        Datos.getInstance().actualizarRecord(objUsuario, objUsuario.getRecord());
        
        out.print("ok");
    }

}
