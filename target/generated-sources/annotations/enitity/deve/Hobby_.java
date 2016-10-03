package enitity.deve;

import enitity.Hobby;
import enitity.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T16:26:27")
@StaticMetamodel(Hobby.class)
public class Hobby_ { 

    public static volatile CollectionAttribute<Hobby, Person> personCollection;
    public static volatile SingularAttribute<Hobby, String> name;
    public static volatile SingularAttribute<Hobby, String> description;
    public static volatile SingularAttribute<Hobby, Integer> id;

}