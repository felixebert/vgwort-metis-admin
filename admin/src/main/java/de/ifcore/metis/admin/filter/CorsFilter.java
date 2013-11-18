package de.ifcore.metis.admin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter
{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		response.addHeader("Access-Control-Allow-Origin", "*");
		handlePreflight(request, response);
		filterChain.doFilter(request, response);
	}

	private void handlePreflight(HttpServletRequest request, HttpServletResponse response)
	{
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod()))
		{
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept");
		}
	}
}
