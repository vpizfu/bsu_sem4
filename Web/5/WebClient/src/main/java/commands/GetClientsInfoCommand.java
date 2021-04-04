package commands;

import company.DAO.TourAgencyDbController;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


/**
 * Command for getting clients info.
 */
public class GetClientsInfoCommand implements ICommand {

    private HttpServletResponse response;
    private HttpServletRequest request;


    private TourAgencyDbController controller;

    public GetClientsInfoCommand(HttpServletResponse response, HttpServletRequest request, TourAgencyDbController controller) {
        this.response = response;
        this.request = request;
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {

            System.out.println("Executing all clients info method");

            List list = Arrays.asList("Ivan", "Kot");

            list = controller.getAllClients();

            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("./ClientsInfo.jsp");
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

                out.println("<h1>Error: " +ex.getMessage()+"\n"+ sw.toString() + "</h1>");

                out.println("</body></html>");

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
