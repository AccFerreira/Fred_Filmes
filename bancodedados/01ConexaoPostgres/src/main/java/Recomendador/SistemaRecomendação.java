package Recomendador;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;
//nesta classe está a relação dos modelos de filmes a serem recomendados
public class SistemaRecomendação {

    private DataModel getModelo(String path) throws IOException {
        File file = new File("src/main/resources/" + path);
        return new FileDataModel(file);
    }

    public DataModel getModeloDeFilmes() throws IOException {
        //arquivo usado com a base de usuários, os códigos dos filmes e os generos
       return getModelo("filmes.csv");
    }

}