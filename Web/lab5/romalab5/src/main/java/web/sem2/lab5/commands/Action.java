package web.sem2.lab5.commands;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    String getName();
    void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx)
            throws ServletException, IOException;
}
