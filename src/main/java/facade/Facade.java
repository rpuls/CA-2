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
     *
     * @return Returns the list of all the Persons
     */
    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();

        try {
            List<Person> persons = em.createQuery("Select p from Person p").getResultList();
            return persons;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns one person
     *
     * @param id Id of the person
     * @return A Java object of Person
     */
    public Person getPersonById(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Person.class, id);

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns a String[] of phone numbers for one person. Be sure that the
     * person is already fully found in the database
     *
     * @param p - Given that the person is already a persisted one
     * @return The phoneNumbers[] for that person
     */
    public String[] getPhonesByPerson(Person p) {
        try {
            List<String> na = new ArrayList<>();
            String[] numbers;
            int i = 0;
            for (Phone ph : p.getPhoneCollection()) {
                na.add(ph.getNumer());
                i++;
            }
            numbers = na.toArray(new String[i]);
            return numbers;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    /**
     * Returns the Adresse for a person
     *
     * @param p Given that the person is already a persisted one
     * @return The Adresse for that person
     */
    public Address getAdressByPerson(Person p) {
        return p.getAdress();
    }

    /**
     * Returns the CityInfo for a person. The Person must already be found in
     * the database before calling this method
     *
     * @param p Given that the person is already a persisted one
     * @return The CityInfo Object for that person
     */
    public CityInfoNew getCityInfoByPerson(Person p) {
        return p.getAdress().getCityinfo();
    }

    /**
     * Get a specfic person based on the phone number
     *
     * @param phone The given phone number
     * @return The person if there is a match.
     */
    public Person getPersonByPhone(String phone) {
        EntityManager em = getEntityManager();
        Person p = new Person();

        try {
            Query query1 = em.createQuery("SELECT p from Phone p WHERE p.numer =:contact");
            query1.setParameter("contact", phone);
            Phone num = (Phone) query1.getSingleResult();
            InfoEntity en = num.getInfoentity();
            p = em.find(Person.class, en.getId());
            return p;

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Returns the Company that mathces the phone number.
     *
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
            return c;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Returns the Company based on the given cvr
     *
     * @param cvr The cvr to search for
     * @return The found company
     */
    public Company getCompanyByCvr(String cvr) {
        EntityManager em = getEntityManager();

        try {
            Query q = em.createQuery("SELECT c from Company c where c.cvr = :cvrnum", Company.class);
            q.setParameter("cvrnum", cvr);
            Company company = (Company) q.getSingleResult();
            return company;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns a collection of Persons given by a Specific Hobby. The Hobby has
     * to be in the database
     *
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
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Returns a Collection of persons that lives in city
     *
     * @param city A string with a City name, NOT A OBJECT OF CITY INFO!
     * @return The Collections for persons that lives in that city
     */
    public Collection<Person> getPersonsByCity(String city) {
        EntityManager em = getEntityManager();

        try {
            Query query1 = em.createQuery("SELECT p FROM Person p WHERE p.adress.cityInfoNew.city =:city");
            query1.setParameter("city", city);
            Collection<Person> pList = (List<Person>) query1.getResultList();
            return pList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns the amount of persons with that hoobie Given that the hobby is
     * already in the database
     *
     * @param hob Hobby that is already a persisted one!
     * @return The count of persons with that hobby
     */
    public int getCountOfPeopleByHobby(Hobby hob) {
        Collection<Person> personCollection = hob.getPersonCollection();
        int count = personCollection.size();
        return count;
    }

    public List<CityInfoNew> getZipCodes() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<CityInfoNew> query = em.createNamedQuery("Cityinfo.findAll", CityInfoNew.class);
            List<CityInfoNew> zipCodes = query.getResultList();
            return zipCodes;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns a Collection of Companies with a given number of employees
     *
     * @param number The number of employees
     * @return A Collection of companies
     */
    public Collection<Company> getCompaniesByEmpAmount(int number) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("Select c from Company c where c.NumEmployees  = :emp", Company.class);
            q.setParameter("emp", number);
            Collection<Company> collection = q.getResultList();
            return collection;
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Adds a Person to the database
     *
     * @param p The java object of person
     * @return The same object, after being persisted in the database
     */
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Adds a Company to the database
     *
     * @param c The java object of Company
     * @return The same object, after being persisted in the database
     */
    public Company addCompany(Company c) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Adds a Hobby to the database
     *
     * @param h The java object of Hobby
     * @return The same object, after being persisted in the database
     */
    public Hobby addHobby(Hobby h) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
            return h;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Adds the phone number to the Info Entity
     *
     * @param ie Asumeing that the IE is already in the database
     * @param phone phonenumber to be added
     * @return The InfoEntity Object that now contains the new Phone object
     */
    public InfoEntity addPhone(InfoEntity ie, Phone phone) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            phone.setInfoentity(ie);
            em.persist(phone);
            Collection<Phone> phonesFromIE = ie.getPhoneCollection(); // have to set it in both directions.
            phonesFromIE.add(phone);
            ie.setPhoneCollection(phonesFromIE);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }
        return ie;
    }

    /**
     * Adds a Address to the database
     *
     * @param adr The java object of Address
     * @return The same object, after being persisted in the database
     */
    public Address addAddress(Address adr) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(adr);
            em.getTransaction().commit();
            return adr;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Adds a cityInfo to the database
     *
     * @param cityInfo The java object of cityInfo
     * @return The same object, after being persisted in the database
     */
    public CityInfoNew addCityInfo(CityInfoNew cityInfo) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
            return cityInfo;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Gets the list of Companies
     *
     * @return listOfCompanies
     */
    public List<Company> getCompanies() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("Select c from Company c");
            List<Company> collection = q.getResultList();
            return collection;
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Returns an Adresse object for a Company. The Company Objects must
     * allready be in the database
     *
     * @param c The Company object that must be an existing one
     * @return The Adresse.
     */
    public Address getAdressByCompany(Company c) {
        return c.getAdress();
    }

    /**
     * Gets the city by the given company
     *
     * @param c given company
     * @return cityInfoNew
     */
    public CityInfoNew getCityInfoByCompany(Company c) {
        return c.getAdress().getCityinfo();
    }

    /**
     * Returns the phone numbers for that company as an String[]
     *
     * @param c The company
     * @return
     */
    public String[] getPhonesByCompany(Company c) {
        Collection<Phone> phoneListObjects = c.getPhoneCollection();
        List<String> phoneNumbers = new ArrayList<>();
        for (Phone obj : phoneListObjects) {
            phoneNumbers.add(obj.getNumer());
        }
        String[] array = phoneNumbers.toArray(new String[0]);
        return array;
    }

    /**
     * Retunrs the Company by id
     *
     * @param id The id for the given company
     * @return The company Obejct
     */
    public Company getCompanyById(Integer id) {
        EntityManager em = getEntityManager();
        return em.find(Company.class, id);
    }

    public List<Hobby> getHobbies() {
 EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("Select h from Hobby h");
            List<Hobby> collection = q.getResultList();
            return collection;
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        } finally {
            em.close();
        }
       

    }

}
