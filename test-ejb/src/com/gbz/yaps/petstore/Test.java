package com.gbz.yaps.petstore;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.gbz.yaps.petstore.ejb.stateless.customer.CustomerServiceRemote;

public class Test {

	public static void main(String[] args){
		try{
			 InitialContext context = new InitialContext();

		      // requ�te sur le nom de la ressource que l'on veut, ici notre EJB
		      CustomerServiceRemote customerService = (CustomerServiceRemote)context.lookup("ejb/stateless/Customer") ;

		      // invocation d'une m�thode
		      //long id = customerService.createCustomer("test1", "test1", "firstNameTest1", "lastNameTest1") ; 
		      long id = 1; 
		      System.out.println("Id :  " + id) ;

		      } catch (NamingException e) {
		         e.printStackTrace();
		      }
	}
}
