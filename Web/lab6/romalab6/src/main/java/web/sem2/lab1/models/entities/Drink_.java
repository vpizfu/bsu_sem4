package web.sem2.lab1.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Drink.class)
public abstract class Drink_ {

	public static volatile SingularAttribute<Drink, Double> cost;
	public static volatile SingularAttribute<Drink, String> name;
	public static volatile SingularAttribute<Drink, Integer> id;

	public static final String COST = "cost";
	public static final String NAME = "name";
	public static final String ID = "id";

}

