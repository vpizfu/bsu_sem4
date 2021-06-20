package web.sem2.lab1.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ingredient.class)
public abstract class Ingredient_ {

	public static volatile SingularAttribute<Ingredient, String> name;
	public static volatile SingularAttribute<Ingredient, Integer> id;
	public static volatile SingularAttribute<Ingredient, Double> units;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String UNITS = "units";

}

