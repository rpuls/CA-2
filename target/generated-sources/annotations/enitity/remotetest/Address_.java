package enitity.remotetest;

import enitity.Address;
import enitity.CityInfo;
import enitity.InfoEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T16:26:27")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile CollectionAttribute<Address, InfoEntity> infoEntities;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, CityInfo> cityinfo;
    public static volatile SingularAttribute<Address, String> additionalInfo;
    public static volatile SingularAttribute<Address, Integer> id;

}