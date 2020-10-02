package com.bexperr.zuul.filers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurrido extends ZuulFilter{
	
	
	private static final Logger log = LoggerFactory.getLogger(PostTiempoTranscurrido.class);

	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("Entrando a POST Fiilter");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Long tInicio = (Long) request.getAttribute("tiempoInicio");
		Long tFinal = System.currentTimeMillis();
		Long tTranscurrido = tFinal - tInicio;
		log.info(String.format("Tiempo transcurrido en segundos %s seg.", tTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s mlseg.", tTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}


}
