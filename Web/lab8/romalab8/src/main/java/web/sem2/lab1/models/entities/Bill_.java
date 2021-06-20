package web.sem2.lab1.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bill.class)
public abstract class Bill_ {

	public static volatile SingularAttribute<Bill, String> userLogin;
	public static volatile SingularAttribute<Bill, Boolean> isPaid;
	public static volatile SingularAttribute<Bill, Integer> id;

	public static final String USER_LOGIN = "userLogin";
	public static final String IS_PAID = "isPaid";
	public static final String ID = "id";

}

