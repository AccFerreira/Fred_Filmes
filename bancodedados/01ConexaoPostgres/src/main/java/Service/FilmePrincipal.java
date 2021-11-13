package Service;

import java.io.IOException;
import DAO.FilmeDAO;
import Modell.Filme;
import spark.Request;
import spark.Response;

public class FilmePrincipal {
	private FilmeDAO filmeDAO;

	public FilmePrincipal() {
		try {
			filmeDAO = new FilmeDAO("new filme");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		int id = Integer.parseInt(request.queryParams("id"));
		String nome = request.queryParams("nome");
		String dataDeLancamento = request.queryParams("dataDeLancamento");
		String link = request.queryParams("link");
		String diretor = request.queryParams("diretor");
		String descricao = request.queryParams("descricao");

		Filme filme = new Filme(id, nome, dataDeLancamento, link, diretor, descricao);

		filmeDAO.add(filme);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Filme filme = (Filme) filmeDAO.get(id);
		
		if (filme != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<filme>\n" + 
            		"\t<id>" + filme.getId() + "</id>\n" +
            		"\t<nome>" + filme.getNome() + "</nome>\n" +
            		"\t<dataDelancamento>" + filme.getDataDeLancamento() + "</dataDelancamento>\n" +
            		"\t<link>" + filme.getLink() + "</link>\n" +
            		"\t<diretor>" + filme.getDiretor() + "</diretor>\n" +
            		"\t<descricao>" + filme.getDescricao() + "</descricao>\n" +
            		"</filme>\n";
        } else {
            response.status(404); // 404 Not found
            return "Filme " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
        Filme filme = (Filme) filmeDAO.get(id);

        if (filme != null) {
        	filme.setNome(request.queryParams("nome"));
        	filme.setDataDeLancamento(request.queryParams("dataDeLancamento"));
        	filme.setLink(request.queryParams("link"));
        	filme.setDiretor(request.queryParams("diretor"));
        	filme.setDescricao(request.queryParams("descricao"));

        	filmeDAO.update(filme);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Filme não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Filme filme = (Filme) filmeDAO.get(id);

        if (filme != null) {

        	filmeDAO.remove(filme);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Filme não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<filmes type=\"array\">");
		for (Filme filme : filmeDAO.getAll()) {
			returnValue.append("<filme>\n" + 
            		"\t<id>" + filme.getId() + "</id>\n" +
            		"\t<nome>" + filme.getNome() + "</nome>\n" +
            		"\t<dataDelancamento>" + filme.getDataDeLancamento() + "</dataDelancamento>\n" +
            		"\t<link>" + filme.getLink() + "</link>\n" +
            		"\t<diretor>" + filme.getDiretor() + "</diretor>\n" +
            		"\t<descricao>" + filme.getDescricao() + "</descricao>\n" +
            		"</filme>\n");
		}
		returnValue.append("</filmes>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}