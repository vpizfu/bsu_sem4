package web.sem2.lab1.models.entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Boolean> isAdmin;
	public static volatile SingularAttribute<User, String> login;

	public static final String PASSWORD = "password";
	public static final String IS_ADMIN = "isAdmin";
	public static final String LOGIN = "login";

}

