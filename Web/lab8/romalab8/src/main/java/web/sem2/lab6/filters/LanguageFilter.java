package web.sem2.lab6.filters;

import web.sem2.lab1.models.entities.User;
import web.sem2.lab5.ActionNames;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "languageFilter", urlPatterns = "/main")
public class LanguageFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession sess = httpReq.getSession();

        String language = req.getParameter("language");
        String sessLang = (String)httpReq.getSession().getAttribute("language");
        if (language == null && sessLang == null) {
            language = "en";
        }
        if (sessLang == null || (language != null && !sessLang.equals(language))) {
            setLanguage(httpReq, httpResp, language);
        }

        resp.setCharacterEncoding("UTF-8");

        chain.doFilter(req, resp);
    }

    private void setLanguage(HttpServletRequest req, HttpServletResponse resp, String language) {
        req.getSession().setAttribute("language", language);
    }

    @Override
    public void destroy() {

    }
}
