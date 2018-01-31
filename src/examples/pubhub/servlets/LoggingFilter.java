package examples.pubhub.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * implementation class LoggingFilter This is a simple HTTP SERVLET filter to
 * log what resources users are requesting and to save records of application
 * activity
 */

public class LoggingFilter implements Filter {
	final static Logger log = LogManager.getLogger(LoggingFilter.class);
	protected FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		// String ua = req.getHeader( "User-Agent" );

		try {

			String uri = req.getRequestURI();
			String username = req.getRemoteUser();

			log.debug("Requested Resource::" + uri + " by " + username);

			Enumeration<String> enumeration = req.getParameterNames();

			while (enumeration.hasMoreElements()) {
				String parametername = enumeration.nextElement();
				log.debug(parametername + " : " + req.getParameter(parametername));
			}
		} catch (Exception e) {
			log.error(e, e);
		}

		chain.doFilter(request, response);
		// log.info(ua);
	}

	@Override
	public void destroy() {
		this.filterConfig = null;

	}
}