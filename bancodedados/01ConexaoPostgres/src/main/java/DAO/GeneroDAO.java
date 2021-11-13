package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import Modell.Genero;

public class GeneroDAO {
	private List<Genero> generos;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public GeneroDAO(String filename) throws IOException {
		file = new File(filename);
		generos = new ArrayList<Genero>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Genero genero) {
		try {
			generos.add(genero);
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar o genero '" + genero.getDescricao() + "' no BD!");
		}
	}

	public Genero get(String nome) {
		for (Genero genero : generos) {
			if (nome == genero.getNome()) {
				return genero;
			}
		}
		return null;
	}

	public void update(Genero g) {
		int index = generos.indexOf(g);
		if (index != -1) {
			generos.set(index, g);
			this.saveToFile();
		}
	}

	public void remove(Genero g) {
		int index = generos.indexOf(g);
		if (index != -1) {
			generos.remove(index);
			this.saveToFile();
		}
	}

	public List<Genero> getAll() {
		return generos;
	}

	private List<Genero> readFromFile() {
		generos.clear();
		Genero genero = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				genero = (Genero) inputFile.readObject();
				generos.add(genero);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao adicionar genero no BD!");
			e.printStackTrace();
		}
		return generos;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Genero genero : generos) {
				outputFile.writeObject(genero);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar genero no BD!");
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