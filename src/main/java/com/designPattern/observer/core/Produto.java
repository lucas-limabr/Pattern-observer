package com.designPattern.observer.core;

import java.util.ArrayList;

public class Produto {

    private ArrayList<Assinante> observers = new ArrayList<>();
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private int limiarEstoque = 20;

    public Produto(String nome, double preco, int quantidadeEstoque, int limiarEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.limiarEstoque = limiarEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;

        for(Assinante observer : observers)
        {
            observer.update(this);
        }
    }

    public boolean subscribe(Assinante assinante)
    {
        return observers.add(assinante);
    }

    public boolean unsubscribe(Assinante assinante)
    {
        return observers.remove(assinante);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public int getLimiarEstoque() {
        return limiarEstoque;
    }

    public ArrayList<Assinante> getObservers() {
        return observers;
    }
}
