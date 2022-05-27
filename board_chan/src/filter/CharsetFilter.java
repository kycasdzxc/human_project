package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CharsetFilter implements Filter{
	private String charset = "utf-8";
	private FilterConfig fc;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		fc = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		request.setCharacterEncoding(charset);
		request.setAttribute("cp", request.getServletContext().getContextPath() + "/");
		chain.doFilter(request, response); // 실제 서블릿이 수행할 일
	}

	@Override
	public void destroy() {
		
	}
}
