package com.bexperr.zuul.filers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class PreTiempoTranscurrido extends ZuulFilter{
	
	
	private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurrido.class);


	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Long tInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tInicio);
		log.info(String.format("%s request enrutado a %s", request.getMethod(),request.getRequestURL().toString()));
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
