package company.models;

import company.models.Client;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.0.v20170811-rNA", date="2021-03-25T14:03:07")
@StaticMetamodel(Tour.class)
public class Tour_ { 

    public static volatile ListAttribute<Tour, Client> clients;
    public static volatile SingularAttribute<Tour, String> endDate;
    public static volatile SingularAttribute<Tour, Float> price;
    public static volatile SingularAttribute<Tour, String> location;
    public static volatile SingularAttribute<Tour, Integer> id;
    public static volatile SingularAttribute<Tour, String> type;
    public static volatile SingularAttribute<Tour, String> startDate;
    public static volatile SingularAttribute<Tour, Boolean> isHot;

}