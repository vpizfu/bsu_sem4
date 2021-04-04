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
 * Command for updating clients discount.
 */
public class UpdateDiscountCommand implements ICommand {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private int discountNumber;
    private int ordersNumber;

    private TourAgencyDbController controller;

    public UpdateDiscountCommand(HttpServletResponse response, HttpServletRequest request, TourAgencyDbController controller, int discountNumber, int ordersNumber) {
        this.response = response;
        this.request = request;
        this.discountNumber = discountNumber;
        this.controller = controller;
        this.ordersNumber = ordersNumber;
    }

    @Override
    public void execute() {
        try {

            boolean bookResult = controller.setDiscount(ordersNumber, discountNumber);
            
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
