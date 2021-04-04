package company.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tour.class)
public abstract class Tour_ {

	public static volatile ListAttribute<Tour, Client> clients;
	public static volatile SingularAttribute<Tour, String> endDate;
	public static volatile SingularAttribute<Tour, Float> price;
	public static volatile SingularAttribute<Tour, String> location;
	public static volatile SingularAttribute<Tour, Integer> id;
	public static volatile SingularAttribute<Tour, String> type;
	public static volatile SingularAttribute<Tour, String> startDate;
	public static volatile SingularAttribute<Tour, Boolean> isHot;

	public static final String CLIENTS = "clients";
	public static final String END_DATE = "endDate";
	public static final String PRICE = "price";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String START_DATE = "startDate";
	public static final String IS_HOT = "isHot";

}

