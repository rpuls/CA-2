package enitity.deve;

import enitity.Company;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T16:26:27")
@StaticMetamodel(Company.class)
public class Company_ extends InfoEntity_ {

    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, Integer> NumEmployees;
    public static volatile SingularAttribute<Company, String> description;
    public static volatile SingularAttribute<Company, String> marketValue;
    public static volatile SingularAttribute<Company, String> cvr;

}