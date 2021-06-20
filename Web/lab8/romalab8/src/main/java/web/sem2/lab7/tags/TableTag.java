package web.sem2.lab7.tags;

import web.sem2.lab1.models.dao.DrinkDaoInterface;
import web.sem2.lab1.models.entities.Drink;
import web.sem2.lab1.models.entities.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;
import java.util.List;

public class TableTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        DrinkDaoInterface drinkDao = (DrinkDaoInterface) pageContext.getServletContext().getAttribute("drinkDao");
        User user = (User) pageContext.getSession().getAttribute("user");

        try {
            pageContext.getRequest().getRequestDispatcher("/table_header.jsp")
                    .include(pageContext.getRequest(), pageContext.getResponse());
            Collection<Drink> drinks = drinkDao.getAll();
            for (Drink drink : drinks) {
                out.write("\t<tr>\n");
                out.write("\t\t<td>" + drink.getId() + "</td>\n");
                out.write("\t\t<td>" + drink.getName() + "</td>\n");
                out.write("\t\t<td>" + drink.getCost() + "</td>\n");
                if (user.isAdmin()) {
                    out.write("\t\t<td><a href=" + pageContext.getServletContext().getContextPath() + "/main" +
                            "?action=edit_drink&DrinkNo=" + drink.getId() + ">Edit</a></td>\n");
                }
                out.write("\t</tr>\n");
            }
            out.write("\t</tbody>\n</table>\n");
        } catch (Exception e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
