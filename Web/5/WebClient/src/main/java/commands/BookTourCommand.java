package commands;

import company.DAO.TourAgencyDbController;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * Command for booking tours.
 */
public class BookTourCommand implements ICommand {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private int clientId;
    private int tourId;

    private TourAgencyDbController controller;

    public BookTourCommand(HttpServletResponse response, HttpServletRequest request, TourAgencyDbController controller, int clientId, int tourId) {
        this.response = response;
        this.request = request;
        this.clientId = clientId;
        this.controller = controller;
        this.tourId = tourId;
    }

    @Override
    public void execute() {
        try {

            boolean bookResult = controller.bookTour(tourId, clientId);

            RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
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
