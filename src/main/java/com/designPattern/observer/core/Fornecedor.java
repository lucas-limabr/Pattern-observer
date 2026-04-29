package com.designPattern.observer.core;

public class Fornecedor implements Assinante {

    private String nome;
    private String email;
    private String ultimaNotificacao;

    public Fornecedor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public void update(Produto produto) {
        if(produto.getQuantidadeEstoque() < produto.getLimiarEstoque())
        {
            this.ultimaNotificacao = "Alerta! Olá fornecedor " + this.nome + "! A quantidade em estoque do produto " + produto.getNome() + " foi atualizada para " + produto.getQuantidadeEstoque() + " unidades e está abaixo do limiar de " + produto.getLimiarEstoque() + " unidades.";
        }
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}
