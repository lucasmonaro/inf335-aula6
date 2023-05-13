package br.unicamp.ic.inf335;

import com.mongodb.client.MongoDatabase;

import static br.unicamp.ic.inf335.ExemploMongoDb.conectar;


public class Main {


    public static void main(String[] args) {
        ExemploMongoDb loja = new ExemploMongoDb();
        MongoDatabase conexao = conectar("localhost", "test");

        if (conexao != null) {
            System.out.println("\nLista original de produtos");
            loja.listarProdutos(conexao);

            loja.insereProduto(conexao,
                    "Prod7",
                    "Bla Bla",
                    "500",
                    "Bla bla");

            System.out.println("\nLista com novo produto");
            loja.listarProdutos(conexao);

            loja.alteraValorProduto(conexao, "Prod7", "400");

            System.out.println("\nLista com valor do produto alterado");
            loja.listarProdutos(conexao);

            System.out.println("\nApaga o produto Prod7");
            loja.apagaProduto(conexao, "Prod7");

            System.out.println("\nVolta a lista original de produtos");
            loja.listarProdutos(conexao);
        }
    }

}