package web.sem2.lab5.servlets;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import web.sem2.lab1.models.dao.*;

@WebListener
public class ContextListener implements ServletContextListener {
//    @EJB
//    private BillDaoInterface billDao;
//    @EJB
//    private BillElementDaoInterface billElementDao;
//    @EJB
//    private DrinkDaoInterface drinkDao;
//    @EJB
//    private IngredientDaoInterface ingredientDao;
//    @EJB
//    private RecipeDaoInterface recipeDao;
//    @EJB
//    private UserDaoInterface userDao;

    public ContextListener() { }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
//        ctx.setAttribute("billDao", billDao);
//        ctx.setAttribute("billElementDao", billElementDao);
//        ctx.setAttribute("drinkDao", drinkDao);
//        ctx.setAttribute("ingredientDao", ingredientDao);
//        ctx.setAttribute("recipeDao", recipeDao);
//        ctx.setAttribute("userDao", userDao);
        ctx.setAttribute("billDao", new BillDaoConsumer());
        ctx.setAttribute("billElementDao", new BillElementDaoConsumer());
        ctx.setAttribute("drinkDao", new DrinkDaoConsumer());
        ctx.setAttribute("ingredientDao", new IngredientDaoConsumer());
        ctx.setAttribute("recipeDao", new RecipeDaoConsumer());
        ctx.setAttribute("userDao", new UserDaoConsumer());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
