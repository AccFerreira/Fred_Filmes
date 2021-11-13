package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import Modell.Usuario;

public class UsuarioDAO {
	private List<Usuario> usuarios;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public UsuarioDAO(String filename) throws IOException {
		file = new File(filename);
		usuarios = new ArrayList<Usuario>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Usuario usuario) {
		try {
			usuarios.add(usuario);
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar o usuario '" + usuario.getDescricao() + "' no BD!");
		}
	}

	public Usuario get(String login) {
		for (Usuario usuario : usuarios) {
			if (login == usuario.getLogin()) {
				return usuario;
			}
		}
		return null;
	}

	public void update(Usuario u) {
		int index = usuarios.indexOf(u);
		if (index != -1) {
			usuarios.set(index, u);
			this.saveToFile();
		}
	}

	public void remove(Usuario u) {
		int index = usuarios.indexOf(u);
		if (index != -1) {
			usuarios.remove(index);
			this.saveToFile();
		}
	}

	public List<Usuario> getAll() {
		return usuarios;
	}

	private List<Usuario> readFromFile() {
		usuarios.clear();
		Usuario usuario = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				usuario = (Usuario) inputFile.readObject();
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar usuario no BD!");
			e.printStackTrace();
		}
		return usuarios;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Usuario usuario : usuarios) {
				outputFile.writeObject(usuario);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar usuario no BD!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}