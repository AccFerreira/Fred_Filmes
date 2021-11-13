package Service;

import java.io.IOException;
import DAO.UsuarioDAO;
import Modell.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioPrincipal {
	private UsuarioDAO usuarioDAO;

	public UsuarioPrincipal() {
		try {
			usuarioDAO = new UsuarioDAO("new usuario");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String login = request.queryParams("login");
		String primeiroNome = request.queryParams("primeiroNome");
		String ultimoNome = request.queryParams("ultimoNome");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		String dataDeNascimento = request.queryParams("dataDeNascimento");
		String generoPreferido1 = request.queryParams("generoPreferido1");
		String generoPreferido2 = request.queryParams("generoPreferido2");
		String generoPreferido3 = request.queryParams("generoPreferido3");
		String descricao = request.queryParams("descricao");

		Usuario usuario = new Usuario(login, primeiroNome, ultimoNome, email, senha, dataDeNascimento,
				                      generoPreferido1, generoPreferido2, generoPreferido3, descricao);

		usuarioDAO.add(usuario);

		response.status(201); // 201 Created
		return usuario;
	}

	public Object get(Request request, Response response) {
		String login = request.queryParams(":login");
		
		Usuario usuario = (Usuario) usuarioDAO.get(login);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<usuario>\n" + 
            		"\t<login>" + usuario.getLogin() + "</login>\n" +
            		"\t<primeiroNome>" + usuario.getPrimeiroNome() + "</primeiroNome>\n" +
            		"\t<ultimoNome>" + usuario.getUltimoNome() + "</ultimoNome>\n" +
            		"\t<email>" + usuario.getEmail() + "</email>\n" +
            		"\t<senha>" + usuario.getSenha() + "</Senha>\n" +
            		"\t<dataDeNascimento>" + usuario.getDataDeNascimento() + "</dataDeNascimento>\n" +
            		"\t<generoPreferido1>" + usuario.getGeneroPreferido1() + "</generoPreferido1>\n" +
            		"\t<generoPreferido2>" + usuario.getGeneroPreferido2() + "</generoPreferido2>\n" +
            		"\t<generoPreferido3>" + usuario.getGeneroPreferido3() + "</generoPreferido3>\n" +
            		"\t<descricao>" + usuario.getDescricao() + "</descricao>\n" +
            		"</usuario>\n";
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + login + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
		String login = request.queryParams(":login");
        
        Usuario usuario = (Usuario) usuarioDAO.get(login);

        if (usuario != null) {
        	usuario.setLogin(request.queryParams("login"));
        	usuario.setPrimeiroNome(request.queryParams("primeiroNome"));
        	usuario.setUltimoNome(request.queryParams("ultimoNome"));
        	usuario.setEmail(request.queryParams("email"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setDataDeNascimento(request.queryParams("dataDeNascimento"));
        	usuario.setGeneroPreferido1(request.queryParams("generoPreferido1"));
        	usuario.setGeneroPreferido2(request.queryParams("generoPreferido2"));
        	usuario.setGeneroPreferido3(request.queryParams("generoPreferido3"));
        	usuario.setDescricao(request.queryParams("descricao"));

        	usuarioDAO.update(usuario);
        	
            return login;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		String login = request.queryParams(":login");

		Usuario usuario = (Usuario) usuarioDAO.get(login);

        if (usuario != null) {

        	usuarioDAO.remove(usuario);

            response.status(200); // success
        	return login;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<usuarios type=\"array\">");
		for (Usuario usuario : usuarioDAO.getAll()) {
			returnValue.append("<usuario>\n" + 
            		"\t<login>" + usuario.getLogin() + "</login>\n" +
            		"\t<primeiroNome>" + usuario.getPrimeiroNome() + "</primeiroNome>\n" +
            		"\t<ultimoNome>" + usuario.getUltimoNome() + "</ultimoNome>\n" +
            		"\t<email>" + usuario.getEmail() + "</email>\n" +
            		"\t<senha>" + usuario.getSenha() + "</Senha>\n" +
            		"\t<dataDeNascimento>" + usuario.getDataDeNascimento() + "</dataDeNascimento>\n" +
            		"\t<generoPreferido1>" + usuario.getGeneroPreferido1() + "</generoPreferido1>\n" +
            		"\t<generoPreferido2>" + usuario.getGeneroPreferido2() + "</generoPreferido2>\n" +
            		"\t<generoPreferido3>" + usuario.getGeneroPreferido3() + "</generoPreferido3>\n" +
            		"\t<descricao>" + usuario.getDescricao() + "</descricao>\n" +
            		"</usuario>\n");
		}
		returnValue.append("</usuarios>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}