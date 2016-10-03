package enitity.deve;

import enitity.Address;
import enitity.Company;
import enitity.InfoEntity;
import enitity.Person;
import enitity.Phone;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T16:26:27")
@StaticMetamodel(InfoEntity.class)
public class InfoEntity_ { 

    public static volatile ListAttribute<InfoEntity, Person> persons;
    public static volatile ListAttribute<InfoEntity, Company> companies;
    public static volatile CollectionAttribute<InfoEntity, Phone> phoneCollection;
    public static volatile SingularAttribute<InfoEntity, Address> adress;
    public static volatile SingularAttribute<InfoEntity, Integer> id;
    public static volatile SingularAttribute<InfoEntity, String> email;

}