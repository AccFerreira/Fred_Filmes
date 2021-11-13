package App;

import static spark.Spark.*;

import Service.GeneroPrincipal;

public class GeneroAplicações {
	
	private static GeneroPrincipal generoPrincipal = new GeneroPrincipal();
	
    public static void main(String[] args) {
        port(5432);

        post("/genero", (request, response) -> generoPrincipal.add(request, response));

        get("/genero/:nome", (request, response) -> generoPrincipal.get(request, response));

        get("/genero/update/:nome", (request, response) -> generoPrincipal.update(request, response));

        get("/genero/delete/:nome", (request, response) -> generoPrincipal.remove(request, response));

        get("/genero", (request, response) -> generoPrincipal.getAll(request, response));
               
    }
}