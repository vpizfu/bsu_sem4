package company.models;

import company.models.Tour;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2021-03-25T14:03:07")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> name;
    public static volatile SingularAttribute<Client, Integer> discount;
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile ListAttribute<Client, Tour> tours;

}