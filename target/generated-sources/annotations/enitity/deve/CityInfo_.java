package enitity.deve;

import enitity.Address;
import enitity.CityInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T16:26:27")
@StaticMetamodel(CityInfo.class)
public class CityInfo_ { 

    public static volatile SingularAttribute<CityInfo, String> zipCode;
    public static volatile CollectionAttribute<CityInfo, Address> addressCollection;
    public static volatile SingularAttribute<CityInfo, String> city;

}