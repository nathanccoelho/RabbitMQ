package com.microservico.consumidorestoque.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.microservico.estoque.constantes.RabbitmqConstantes;
import com.microservico.estoque.dto.EstoqueDto;

@Component
public class EstoqueConsumer {
	
	@RabbitListener(queues = RabbitmqConstantes.FILA_ESTOQUE)
	private void consumidor(EstoqueDto estoqueDto) {
		
	}

}
