package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	/*
	 * Don't really need this one since
	 * i already did it in the delete test
	 * but since it looks like part of the
	 * deliverable
	 */
	
	/*@AfterClass
	public static void DeletepersonInstance() {
		try{
			PersonDAL.deletePerson(person1UUID);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	*/
	
	@Test
	public void addtest(){
		PersonDAL.addPerson(person1);
	}
	
	@Test
	public void gettest(){
		PersonDomainModel persontest = PersonDAL.getPerson(person1.getPersonID());
		assertEquals(persontest.getFirstName(), person1.getFirstName());
		assertEquals(persontest.getMiddleName(), person1.getMiddleName());
		assertEquals(persontest.getLastName(), person1.getLastName());
		assertEquals(persontest.getBirthday(), person1.getBirthday());
		assertEquals(persontest.getCity(), person1.getCity());
		assertEquals(persontest.getClass(), person1.getClass());
		assertEquals(persontest.getPersonID(), person1.getPersonID());
		assertEquals(persontest.getPostalCode(), person1.getPostalCode());
		assertEquals(persontest.getStreet(), person1.getStreet());
	}
	@Test
	public void updatetest(){
		person1.setFirstName("Newname");
		PersonDAL.updatePerson(person1);
		assertEquals(PersonDAL.getPerson(person1UUID).getFirstName(),"Newname");
	}
	@Test
	public void deletetest(){
		//a blank arraylist is equal to another blank arraylist since nothing in data.
		ArrayList testlist = new ArrayList();
		PersonDAL.deletePerson(person1UUID);
		assertEquals(PersonDAL.getPersons(),testlist);
	}
	
	

}
