/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author Ailton Jr
 */
@Path("app")
public class WaypointsSW {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WaypointsSW
     */
    public WaypointsSW() {
    }

    /**
     * Retrieves representation of an instance of ws.WaypointsSW
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("Usuario/login/{login}/{senha}")
    public String getUsuario (@PathParam("login") String usuario, @PathParam("senha") String senha){

        Usuario us = new Usuario();
        us.setUsuario(usuario);
        UsuarioDAO usDAO = new UsuarioDAO();
        us = usDAO.buscar(us);
        
        if (us.getSenha().equals(senha)) {
            return "{\"Login\": \"True\"}";
        }else{
            return "{\"Login\": \"False\"}";
        }
    }
    
    @GET
    @Produces("application/json")
    @Path("Usuario/list")
    public String listUsuario (){
        List<Usuario> lista;
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        lista = usuarioDAO.listar();
        Gson gson = new Gson();
        gson.toString();
        return gson.toJson(lista);
    }

    /**
     * PUT method for updating or creating an instance of WaypointsSW
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
