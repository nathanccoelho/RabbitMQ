package com.microservico.estoque.conections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.microservico.estoque.constantes.RabbitmqConstantes;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
        inicializar();
    }

    public void inicializar() {
        Queue filaEstoque = fila(RabbitmqConstantes.FILA_ESTOQUE);
        Queue filaPreco = fila(RabbitmqConstantes.FILA_PRECO);

        DirectExchange troca = trocaDireta();

        Binding ligacaoEstoque = relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = relacionamento(filaPreco, troca);

        amqpAdmin.declareExchange(troca);

        amqpAdmin.declareQueue(filaEstoque);
        amqpAdmin.declareQueue(filaPreco);

        amqpAdmin.declareBinding(ligacaoEstoque);
        amqpAdmin.declareBinding(ligacaoPreco);
    }

    public Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    public DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    public Binding relacionamento(Queue fila, DirectExchange troca) {
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }
}