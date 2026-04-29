package com.designPattern.observer.core;

import java.util.Observable;
import java.util.Observer;

public class Cliente implements Assinante {

    private String nome;
    private String email;
    private String ultimaNotificacao;

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public void update(Produto produto) {
        System.out.println("executou");
        this.ultimaNotificacao = "Olá " + this.nome + "! A quantidade em estoque do produto " + produto.getNome() + " foi atualizada para " + produto.getQuantidadeEstoque();
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}
