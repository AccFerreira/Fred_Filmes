package App;

import static spark.Spark.*;

import Service.FilmePrincipal;

public class FilmeAplicações {
	
	private static FilmePrincipal filmePrincipal = new FilmePrincipal();
	
    public static void main(String[] args) {
        port(5432);

        post("/filme", (request, response) -> filmePrincipal.add(request, response));

        get("/filme/:id", (request, response) -> filmePrincipal.get(request, response));

        get("/filme/update/:id", (request, response) -> filmePrincipal.update(request, response));

        get("/filme/delete/:id", (request, response) -> filmePrincipal.remove(request, response));

        get("/filme", (request, response) -> filmePrincipal.getAll(request, response));
               
    }
}