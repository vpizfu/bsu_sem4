package web.sem2.lab5.commands;

import web.sem2.lab1.models.dao.*;
import web.sem2.lab1.models.entities.Bill;
import web.sem2.lab1.models.output.BillElementModel;
import web.sem2.lab1.models.output.BillOutputModel;
import web.sem2.lab5.ActionNames;
import web.sem2.lab5.JspNavigation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BillAction implements Action {
    @Override
    public String getName() {
        return ActionNames.BILL;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, ServletContext ctx) throws ServletException, IOException {
        RequestDispatcher disp = req.getRequestDispatcher(JspNavigation.BILL_PAGE);
        String login = req.getParameter("Login");
        if (login == null) {
            disp.forward(req, resp);
            return;
        }
        BillDaoInterface billDao = (BillDaoInterface) ctx.getAttribute("billDao");
        BillElementDaoInterface billElementDao = (BillElementDaoInterface) ctx.getAttribute("billElementDao");
        DrinkDaoInterface drinkDao = (DrinkDaoInterface) ctx.getAttribute("drinkDao");
        try {
            Optional<Bill> userBill = billDao.getAll().stream()
                    .filter(bill -> bill.getUserLogin().equals(login) && !bill.isPaid())
                    .findFirst();
            if (!userBill.isPresent()) {
                disp.forward(req, resp);
                return;
            }

            BillOutputModel outputModel = new BillOutputModel();
            outputModel.setBill(userBill.get());
            List<BillElementModel> billElements = billElementDao.getByBill(userBill.get().getId()).stream()
                    .map(billElement -> {
                        BillElementModel model = new BillElementModel();
                        model.setBillElement(billElement);
                        try {
                            model.setDrink(Optional.ofNullable(drinkDao.get(billElement.getDrinkId())).get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return model;
                    })
                    .collect(Collectors.toList());
            outputModel.setBillElements(billElements);

            req.setAttribute("billModel", outputModel);
            disp.forward(req, resp);
        } catch (Exception e) {
            disp.forward(req, resp);
        }
    }
}
