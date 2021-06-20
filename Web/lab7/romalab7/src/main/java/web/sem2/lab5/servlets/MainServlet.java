package web.sem2.lab5.servlets;

import web.sem2.lab5.ActionNames;
import web.sem2.lab5.actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {
    private Map<String, Action> actions = new HashMap<>();

    @Override
    public void init() {
        List<Action> actions = Arrays.asList(
            new IndexAction(),
            new BillAction(),
            new BuyDrinkAction(),
            new DrinksAction(),
            new IngredientsAction(),
            new FillAction(),
            new IndexNoLoginAction(),
            new LoginAction(),
            new LogoutAction(),
            new SignUpAction(),
            new EditDrinkAction()
        );
        for (Action action : actions) {
            this.actions.put(action.getName(), action);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = ActionNames.INDEX;
        }
        if (actions.containsKey(action)) {
            actions.get(action).execute(req, resp, this.getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
    }
}
