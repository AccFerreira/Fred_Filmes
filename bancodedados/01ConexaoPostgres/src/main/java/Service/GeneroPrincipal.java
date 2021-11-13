package Service;

import java.io.IOException;
import DAO.GeneroDAO;
import Modell.Genero;
import spark.Request;
import spark.Response;

public class GeneroPrincipal {
	private GeneroDAO generoDAO;

	public GeneroPrincipal() {
		try {
			generoDAO = new GeneroDAO("new genero");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String descricao = request.queryParams("descricao");

		Genero genero = new Genero(nome, descricao);

		generoDAO.add(genero);

		response.status(201); // 201 Created
		return nome;
	}

	public Object get(Request request, Response response) {
		String nome = request.queryParams(":nome");
		
		Genero genero = (Genero) generoDAO.get(nome);
		
		if (genero != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<genero>\n" + 
            		"\t<nome>" + genero.getNome() + "</nome>\n" +
            		"\t<descricao>" + genero.getDescricao() + "</descricao>\n" +
            		"</genero>\n";
        } else {
            response.status(404); // 404 Not found
            return "Genero " + nome + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
		String nome = request.queryParams(":nome");
        
        Genero genero = (Genero) generoDAO.get(nome);

        if (genero != null) {
        	genero.setDescricao(request.queryParams("descricao"));

        	generoDAO.update(genero);
        	
            return nome;
        } else {
            response.status(404); // 404 Not found
            return "Genero não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		String nome = request.queryParams(":nome");

        Genero genero = (Genero) generoDAO.get(nome);

        if (genero != null) {

        	generoDAO.remove(genero);

            response.status(200); // success
        	return nome;
        } else {
            response.status(404); // 404 Not found
            return "Genero não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<generos type=\"array\">");
		for (Genero genero : generoDAO.getAll()) {
			returnValue.append("<genero>\n" + 
            		"\t<nome>" + genero.getNome() + "</nome>\n" +
            		"\t<descricao>" + genero.getDescricao() + "</descricao>\n" +
            		"</filme>\n");
		}
		returnValue.append("</generos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}