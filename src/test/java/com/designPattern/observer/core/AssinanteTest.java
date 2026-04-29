package com.designPattern.observer.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssinanteTest {

    Produto produto;
    Cliente cliente;
    Fornecedor fornecedor;

    @BeforeEach
    void setUp() {
        produto = new Produto("Tênis de corrida", 300.0, 50, 20);
        cliente = new Cliente("Lucas", "lucas@gmail.com");
        fornecedor = new Fornecedor("João", "joao@gmail.com");
    }

    @Test
    @DisplayName("Deve tornar o cliente um assinante do produto")
    void deveTornarClienteAssinanteDoProduto() {
        assertTrue(produto.subscribe(cliente));
    }

    @Test
    @DisplayName("Deve notificar o cliente quando a quantidade do produto for atualizada")
    void deveNotificarCliente() {
        produto.subscribe(cliente);
        produto.setQuantidadeEstoque(49);
        String expected = "Olá Lucas! A quantidade em estoque do produto Tênis de corrida foi atualizada para 49";
        assertEquals(expected, cliente.getUltimaNotificacao());
    }

    @Test
    @DisplayName("Deve notificar um cliente e um fornecedor quando a quantidade do produto for atualizada e atender a regra de notificação do fornecedor")
    void deveNotificarClienteEFornecedor() {
        produto.subscribe(cliente);
        produto.subscribe(fornecedor);
        produto.setQuantidadeEstoque(19);
        String expectedCliente1 = "Olá Lucas! A quantidade em estoque do produto Tênis de corrida foi atualizada para 19";
        String expectedCliente2 = "Alerta! Olá fornecedor João! A quantidade em estoque do produto Tênis de corrida foi atualizada para 19 unidades e está abaixo do limiar de 20 unidades.";

        assertEquals(expectedCliente1, cliente.getUltimaNotificacao());
        assertEquals(expectedCliente2, fornecedor.getUltimaNotificacao());
    }

    @Test
    @DisplayName("Deve remover o cliente como assinante")
    void deveRemoverClienteAssinante() {
        produto.subscribe(cliente);
        assertTrue(produto.unsubscribe(cliente));
    }

    @Test
    @DisplayName("Deve tornar o fornecedor um assinante do produto")
    void deveTornarFornecedorAssinanteDoProduto() {
        assertTrue(produto.subscribe(fornecedor));
    }

    @Test
    @DisplayName("Deve notificar o fornecedor quando a quantidade do produto for atualizada e estiver abaixo do limiar de estoque")
    void deveNotificarFornecedorEstoqueAbaixoLimiar() {
        produto.subscribe(fornecedor);
        produto.setQuantidadeEstoque(19);
        String expected = "Alerta! Olá fornecedor João! A quantidade em estoque do produto Tênis de corrida foi atualizada para 19 unidades e está abaixo do limiar de 20 unidades.";
        assertEquals(expected, fornecedor.getUltimaNotificacao());
    }

    @Test
    @DisplayName("Não deve notificar o fornecedor quando a quantidade do produto for atualizada, mas estiver acima do limiar de estoque")
    void naoDeveNotificarFornecedorEstoqueAcimaLimiar() {
        produto.subscribe(fornecedor);
        produto.setQuantidadeEstoque(21);
        assertNull(fornecedor.getUltimaNotificacao());
    }

    @Test
    @DisplayName("Deve remover o fornecedor como assinante")
    void deveRemoverFornecedorAssinante() {
        produto.subscribe(fornecedor);
        assertTrue(produto.unsubscribe(fornecedor));
    }
}