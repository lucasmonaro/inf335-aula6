package br.unicamp.ic.inf335;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ExemploMongoDb {

    public static MongoDatabase conectar(String host,
                                         String database) {
        MongoClient mongoClient = MongoClients.create("mongodb://" + host);
        return mongoClient.getDatabase(database);
    }

    public void listarProdutos(MongoDatabase conexao) {
        MongoCollection<Document> collection = conexao.getCollection("produtos");
        Iterable<Document> produtos = collection.find();

        for (Document produto : produtos) {
            String nome = produto.getString("nome");
            String descricao = produto.getString("descricao");
            String valor = produto.getString("valor");
            String estado = produto.getString("estado");
            System.out.println(nome + "--" + descricao + "--" + valor + "--" + estado);
        }
    }

    public void insereProduto(MongoDatabase conexao,
                              String nome,
                              String descricao,
                              String valor,
                              String estado) {

        MongoCollection<Document> collection = conexao.getCollection("produtos");

        Document produto = new Document();
        produto.append("nome", nome)
                .append("descricao", descricao)
                .append("valor", valor)
                .append("estado", estado);

        collection.insertOne(produto);
    }

    public void alteraValorProduto(MongoDatabase conexao,
                                   String nome,
                                   String valor) {

        MongoCollection<Document> collection = conexao.getCollection("produtos");

        Document filtro = new Document("nome", nome);
        Document atualizacao = new Document("$set", new Document("valor", valor));

        collection.findOneAndUpdate(filtro, atualizacao);
    }

    public void apagaProduto(MongoDatabase conexao,
                             String nome) {
        MongoCollection<Document> collection = conexao.getCollection("produtos");

        Document filtro = new Document("nome", nome);
        collection.deleteOne(filtro);
    }
}
