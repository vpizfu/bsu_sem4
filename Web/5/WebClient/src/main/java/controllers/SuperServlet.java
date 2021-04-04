package controllers;

import commands.BookTourCommand;
import commands.GetClientInfoCommand;
import commands.GetClientsInfoCommand;
import commands.UpdateDiscountCommand;
import company.DAO.TourAgencyDbController;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Main servlet. Handles all requests.
 */
@WebServlet(value = "/superServ")
public class SuperServlet extends HttpServlet {

    @EJB
    private TourAgencyDbController controller;

    public void init() {


        try {
            if (controller == null) {
                throw new Exception("Failed to get controller");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Ready to work");

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        executeCommand(req, resp);

    }

    public void executeCommand(HttpServletRequest request, HttpServletResponse response) {

        try {
            String commandId = request.getParameter("commandId").toString();

            System.out.println("commandId: " + commandId);

            int clientId = 0;

            switch (commandId) {
                case "clientInfo":
                    clientId = Integer.parseInt(request.getParameter("clientId"));
                    new GetClientInfoCommand(response, request, controller, clientId).execute();
                    break;
                case "clientsInfo":
                    new GetClientsInfoCommand(response, request, controller).execute();
                    break;
                case "bookTour":
                    clientId = Integer.parseInt(request.getParameter("clientId"));
                    int tourId = Integer.parseInt(request.getParameter("tourId"));
                    new BookTourCommand(response, request, controller, clientId, tourId).execute();
                    break;
                case "updateDiscount":
                    int discountNumber = Integer.parseInt(request.getParameter("discountId"));
                    int ordersCount = Integer.parseInt(request.getParameter("ordersId"));
                    new UpdateDiscountCommand(response, request, controller, discountNumber, ordersCount).execute();
                    break;

            }
        } catch (Exception ex) {

            PrintWriter out = null;

            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.println("<html><body>");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            ex.printStackTrace(pw);

            out.println("<h1>" + sw.toString() + "</h1>");

            out.println("</body></html>");
        }
    }
}
