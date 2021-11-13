package App;

import static spark.Spark.*;

import Service.UsuarioPrincipal;

public class UsuarioAplicações {
	
	private static UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal();
	
    public static void main(String[] args) {
        port(5432);

        post("/usuario", (request, response) -> usuarioPrincipal.add(request, response));

        get("/usuario/:login", (request, response) -> usuarioPrincipal.get(request, response));

        get("/usuario/update/:login", (request, response) -> usuarioPrincipal.update(request, response));

        get("/usuario/delete/:login", (request, response) -> usuarioPrincipal.remove(request, response));

        get("/usuario", (request, response) -> usuarioPrincipal.getAll(request, response));
               
    }
}