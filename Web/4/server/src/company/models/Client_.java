package company.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, Integer> discount;
	public static volatile SingularAttribute<Client, Integer> id;
	public static volatile ListAttribute<Client, Tour> tours;

	public static final String NAME = "name";
	public static final String DISCOUNT = "discount";
	public static final String ID = "id";
	public static final String TOURS = "tours";

}

