package com.dishatech.cloud.api.gateway.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/* 
 * This class combine multiple pre and post filters and executes in specified order
*/
@Component
public class GlobalFiltersConfiguration {
	
	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

	@Order(1) //specify filter order for execution
	@Bean
	public GlobalFilter secondPreFilter() {
		
		return (exchange, chain)-> {
			logger.info("My second global pre-filter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(()-> {
				logger.info("My third global post-filter executed");
			}));
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPreFilter() {
		
		return (exchange, chain)-> {
			logger.info("My third global pre-filter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(()-> {
				logger.info("My second global post-filter executed");
			}));
		};
	}
	
	@Order(3)
	@Bean
	public GlobalFilter fourthPreFilter() {
		
		return (exchange, chain)-> {
			logger.info("My fourth global pre-filter executed");
			return chain.filter(exchange).then(Mono.fromRunnable(()-> {
				logger.info("My first global post-filter executed");
			}));
		};
	}
}
