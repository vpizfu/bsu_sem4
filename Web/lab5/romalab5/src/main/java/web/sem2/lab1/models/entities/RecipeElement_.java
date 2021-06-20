package web.sem2.lab1.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecipeElement.class)
public abstract class RecipeElement_ {

	public static volatile SingularAttribute<RecipeElement, Integer> drinkId;
	public static volatile SingularAttribute<RecipeElement, Integer> ingredientId;
	public static volatile SingularAttribute<RecipeElement, Double> ingredientAmount;

	public static final String DRINK_ID = "drinkId";
	public static final String INGREDIENT_ID = "ingredientId";
	public static final String INGREDIENT_AMOUNT = "ingredientAmount";

}

