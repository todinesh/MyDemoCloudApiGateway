package com.dishatech.cloud.api.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;

@Component
public class MyPostFilter implements GlobalFilter, Ordered {
	final Logger logger = LoggerFactory.getLogger(MyPostFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return chain.filter(exchange).then(Mono.fromRunnable(()-> {
			logger.info("My last lobal post-filter executed");
		}));
	}
	
	@Override
	public int getOrder() {
		return 0;
	}

}
