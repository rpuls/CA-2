/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enitity.Address;
import enitity.CityInfo;
import enitity.Company;
import enitity.Hobby;
import enitity.InfoEntity;
import enitity.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmus
 */
public class Facade {
    
    public Person getPerson(int id){
        return new Person();
    }
    
    public Company getCompany(String phoeOrCvr){
        //in this method we should search for the given parameter in first the
        //phone field, if nothing foud we should search in the cvr field and
        //return null if no company with either matching phone or cvr
        return new Company();
    }
    
    public List<Person> getPersonsByHobby(Hobby hob){
        return new ArrayList<Person>();
    }
    
    public List<Person> getPersonsByCity(String zipOrCity){
        //the same as before, we should use one string and search in two fiels,
        //MAYBE do a check wether the string looks like a city name og a zip code,
        //and the only search in the relevent field?
        return new ArrayList<Person>();
    }
    
    public int getCountOfPeopleByHobby(Hobby hob){
        return 0;
    }
    
    public List<CityInfo> getZipCodes(){
        return new ArrayList<CityInfo>();
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
    
    public void addAdresse(Address adr){
        
    }
    
    public void addCitryInfo(){
        
    }
    
}
