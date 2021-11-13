package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import Modell.Filme;

public class FilmeDAO {
	private List<Filme> filmes;
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public FilmeDAO(String filename) throws IOException {
		file = new File(filename);
		filmes = new ArrayList<Filme>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Filme filme) {
		try {
			filmes.add(filme);
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar o filme '" + filme.getDescricao() + "' no BD!");
		}
	}

	public Filme get(int id) {
		for (Filme filme : filmes) {
			if (id == filme.getId()) {
				return filme;
			}
		}
		return null;
	}

	public void update(Filme f) {
		int index = filmes.indexOf(f);
		if (index != -1) {
			filmes.set(index, f);
			this.saveToFile();
		}
	}

	public void remove(Filme f) {
		int index = filmes.indexOf(f);
		if (index != -1) {
			filmes.remove(index);
			this.saveToFile();
		}
	}

	public List<Filme> getAll() {
		return filmes;
	}

	private List<Filme> readFromFile() {
		filmes.clear();
		Filme filme = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				filme = (Filme) inputFile.readObject();
				filmes.add(filme);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar filme no BD!");
			e.printStackTrace();
		}
		return filmes;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Filme filme : filmes) {
				outputFile.writeObject(filme);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar filme no BD!");
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