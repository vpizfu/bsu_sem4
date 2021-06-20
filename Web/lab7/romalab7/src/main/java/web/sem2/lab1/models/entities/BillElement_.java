package web.sem2.lab1.models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillElement.class)
public abstract class BillElement_ {

	public static volatile SingularAttribute<BillElement, Integer> drinkId;
	public static volatile SingularAttribute<BillElement, Integer> billId;
	public static volatile SingularAttribute<BillElement, Integer> drinkAmount;

	public static final String DRINK_ID = "drinkId";
	public static final String BILL_ID = "billId";
	public static final String DRINK_AMOUNT = "drinkAmount";

}

