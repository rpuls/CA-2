/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
import enitity.Hobby;
import enitity.InfoEntity;
import enitity.Person;
import enitity.Phone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rasmus
 */
public class Facade {

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Returns the list of all the Persons
     * @return Returns the list of all the Persons
     */
    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = null;
        try {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally {
            em.close();
        }
    }

    /**
     * Returns one person 
     * @param id Id of the person
     * @return A Java object of Person
     */
    public Person getPersonById(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Returns the phonecollection for one person.
     * Be sure that the person is already fully found in the database
     * @param p - Given that the person is already a persisted one
     * @return The phonecollection for that person
     */
    public Collection<Phone> getPhonesByPerson(Person p) {
        return p.getPhoneCollection();
    }

    /**
     * Returns the Adresse for a person
     * @param p Given that the person is already a persisted one
     * @return The Adresse for that person
     */
    public Address getAdressByPerson(Person p) {
        return new Address();
    }

    /**
     * Returns the CityInfo for a person. The Person must already be found in the
     * database before calling this method
     * @param p Given that the person is already a persisted one
     * @return The CityInfo Object for that person
     */
    public CityInfoNew getCityInfoByPerson(Person p) {
        return p.getAdress().getCityinfo();
    }

    /**
     * Get a specfic person based on the phone number
     * @param phone The given phone number
     * @return The person if there is a match.
     */
    public Person getPerson(String phone) {
        EntityManager em = getEntityManager();
        Person p = new Person();
        
        try{
            Query query1 = em.createQuery("SELECT p from Phone p WHERE p.numer =:contact");
            query1.setParameter("contact", phone);
            Phone num = (Phone) query1.getSingleResult();
            InfoEntity en = num.getInfoentity();
            p = em.find(Person.class, en.getId());
        }finally{
            em.close();
        }

        return p;
    }

    /**
     * Returns the Company that mathces the phone number.
     * @param phone The phonenumber to search for.
     * @return The company, that is found.
     */
    public Company getCompanyByPhone(String phone) {
        //in this method we should search for the given parameter in first the
        //phone field, if nothing foud we should search in the cvr field and
        //return null if no company with either matching phone or cvr
        EntityManager em = getEntityManager();
        Company c = new Company();

        try {
            em.getTransaction().begin();
            c = em.find(Company.class, phone); //it should have an access in the phone through the Company
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return c;
    }
    
    /**
     * Returns the Company based on the given cvr
     * @param cvr The cvr to search for
     * @return The found company
     */
    public Company getCompanyByCvr(String cvr) {
     throw new UnsupportedOperationException();
    }

    /**
     * Returns a collection of Persons given by a 
     * Specific Hobby. The Hobby has to be in the database
     * @param hob An Already persisted hobby
     * @return A Collections for Persons.
     */
    public Collection<Person> getPersonsByHobby(Hobby hob) {

        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p,h from Person p, Hobby h WHERE h.name= :hobby");
            query.setParameter("hobby", hob.getName());
            List<Person> persons = query.getResultList();
            return persons;
        } finally {
            em.close();
        }

    }

    /**
     * Returns a Collection of persons that lives in city
     * @param City A string with a City name, NOT A OBJECT OF CITY INFO!
     * @return The Collections for persons that lives in that city
     */
    public Collection<Person> getPersonsByCity(String City) {
        EntityManager em = getEntityManager();
        try {
            Query query;
            if (Character.isDigit(City.charAt(0))) {
                query = em.createQuery("SELECT p,c from Person p, CityInfo c  WHERE c.ZIP= :zip");
                query.setParameter("zip", City);
            } else {
                query = em.createQuery("SELECT p,c from Person p, CityInfo c  WHERE c.CITY= :city");
                query.setParameter("city", City);
            }
            List<Person> persons = query.getResultList();
            return persons;
        } finally {
            em.close();
        }
    }

    /**
     * Returns the amount of persons with that hoobie
     * Given that the hobby is already in the database
     * @param hob Hobby that is already a persisted one!
     * @return The count of persons with that hobby
     */
    public int getCountOfPeopleByHobby(Hobby hob) {
       Collection<Person> personCollection = hob.getPersonCollection();
       int count = personCollection.size();
       return count;
    }

    public List<CityInfoNew> getZipCodes() {
        List<CityInfoNew> zipCodes = new ArrayList();
        EntityManager em = getEntityManager();
        
        try{
            TypedQuery<CityInfoNew> query = em.createNamedQuery("Cityinfo.findAll",CityInfoNew.class);
            zipCodes = query.getResultList();
        }finally{
            em.close();
        }
        return zipCodes;
    }

    /**
     * Returns a Collection of Companies with a given number of
     * employees
     * @param number The number of employees
     * @return A Collection of companies
     */
    public Collection<Company> getCompaniesByEmpAmount(int number) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("Select c from Company c where c.NumEmployees  = :emp", Company.class);
            Collection<Company> collection = q.getResultList();
            return collection;
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        finally{
            em.close();
        }
        return null;
    }

    /**
     * Adds a Person to the database
     * @param p The java object of person
     * @return The same object, after being persisted in the database
     */
    public Person addPerson(Person p) {
         EntityManager em = emf.createEntityManager();
        
        
        try{
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Error" + e);
        }
        finally{
            em.close();
        }
        return p;
    }

    /**
     * Adds a Company to the database
     * @param c The java object of Company
     * @return The same object, after being persisted in the database
     */
    public Company addCompany(Company c) {
        EntityManager em = emf.createEntityManager();
        
        
        try{
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Error" + e);
        }
        finally{
            em.close();
        }
        return c;
    }

     /**
     * Adds a Hobby to the database
     * @param h The java object of Hobby
     * @return The same object, after being persisted in the database
     */
    public Hobby addHobby(Hobby h) {
        
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
            //return h;
        }
        finally
        {
            em.close();
        }
        return h;

    }

    /**
     * Adds the phone number to the Info Entity
     * @param ie Asumeing that the IE is already in the database
     * @param phone phonenumber to be added
     * @return The InfoEntity Object that now contains the new Phone object
     */
    public InfoEntity addPhone(InfoEntity ie, Phone phone) {
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            phone.setInfoentity(ie);
            em.persist(phone);
            Collection<Phone> phonesFromIE = ie.getPhoneCollection(); // have to set it in both directions.
            phonesFromIE.add(phone);
            ie.setPhoneCollection(phonesFromIE);
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        finally
        {
            em.close();
        }
        return ie;
    }

     /**
     * Adds a Address to the database
     * @param adr The java object of Address
     * @return The same object, after being persisted in the database
     */
    public Address addAddress(Address adr) {
        
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(adr);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
        return adr;

    }

    /**
     * Adds a cityInfo to the database
     * @param cityInfo The java object of cityInfo
     * @return The same object, after being persisted in the database
     */
    public CityInfoNew addCityInfo(CityInfoNew cityInfo) {
         EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Error: " +e);
        }
        finally
        {
            em.close();
        }
        return cityInfo;
    }

}
