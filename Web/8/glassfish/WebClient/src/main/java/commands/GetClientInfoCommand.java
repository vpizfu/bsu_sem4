package commands;

import company.DAO.DbController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


/**
 * Command for getting client tours.
 */
public class GetClientInfoCommand implements ICommand {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private int clientId;

    private DbController controller;

    public GetClientInfoCommand(HttpServletResponse response, HttpServletRequest request, DbController controller, int clientId) {
        this.response = response;
        this.request = request;
        this.clientId = clientId;
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {

            List list = Arrays.asList("Ivan", "Kot");

            list = controller.getAllTours(clientId);

//            PrintWriter out = null;
//
//            out = response.getWriter();
//
//            out.println("<html><body>");
//
//            out.println("<h1>" + "All good" + "</h1>");
//
//            out.println("</body></html>");

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("./ToursInfo.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            // Hello
            PrintWriter out = null;
            try {
                out = response.getWriter();

                out.println("<html><body>");

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);

                ex.printStackTrace(pw);

                out.println("<h1>" + sw.toString() + "</h1>");

                out.println("</body></html>");

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
