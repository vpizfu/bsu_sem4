package company;

import company.DAO.DAO;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/webapi")
public class testApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();

        classes.add(DAO.class);

        return classes;
    }
}
