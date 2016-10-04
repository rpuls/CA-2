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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author rasmus
 */
public class Facade {
    
    EntityManagerFactory emf ;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    

    //Given phone number
    public Person getPerson(int phone){
        EntityManager em=getEntityManager();
        Person p = new Person();
        
        try{
            em.getTransaction().begin();
            p = em.find(Person.class, phone); //it should have an access in the phone through the Person
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        return p;
    }
    
    public Company getCompany(String phoeOrCvr){
        //in this method we should search for the given parameter in first the
        //phone field, if nothing foud we should search in the cvr field and
        //return null if no company with either matching phone or cvr
        EntityManager em=getEntityManager();
        Company c = new Company();
        
        try{
            em.getTransaction().begin();
            c = em.find(Company.class, phoeOrCvr); //it should have an access in the phone through the Company
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
        return c;
    }
    
    public List<Person> getPersonsByHobby(Hobby hob){
        
        EntityManager em= getEntityManager();
        try{
            Query query = em.createQuery("SELECT p,h from Person p, Hobby h WHERE h.name= :hobby");
            query.setParameter("hobby", hob.getName());
            List<Person> persons = query.getResultList();
            return persons;
        }finally{
            em.close();
        }
        
    }
    
    public List<Person> getPersonsByCity(String zipOrCity){
        //the same as before, we should use one string and search in two fiels,
        //MAYBE do a check wether the string looks like a city name og a zip code,
        //and the only search in the relevent field?
        EntityManager em= getEntityManager();
        try{
            Query query;
            if (Character.isDigit(zipOrCity.charAt(0))) {
                query = em.createQuery("SELECT p,c from Person p, CityInfo c  WHERE c.ZIP= :zip");
                query.setParameter("zip", zipOrCity);
            }else{
                query = em.createQuery("SELECT p,c from Person p, CityInfo c  WHERE c.CITY= :city");
                query.setParameter("city", zipOrCity);
            }
            List<Person> persons = query.getResultList();
            return persons;
        }finally{
            em.close();
        }
    }
    
    public int getCountOfPeopleByHobby(Hobby hob){
        return 0;
    }
    
    public List<CityInfoNew> getZipCodes(){
        return new ArrayList<CityInfoNew>();
    }
    
    public List<Company> getCompaniesByEmpAmount(int number){
        return new ArrayList<Company>();
    }
    
    //ADD we might consider if we want to give a ie. person object, or if we will
    //just give the required parameters to create a new person object
    public void addPerson(Person p){
        
    }
    
    public void addCompany(Company c){
        
    }
    
    public void addHobby(Hobby h){
        
    }
    
    public void addInfoEntity(InfoEntity ie){
        
    }
    
    public void addPhone(InfoEntity ie, String phone){
        
    }
    
    public void addAddress(Address adr){
        
    }
    
    public void addCityInfo(){
        
    }
    
}
