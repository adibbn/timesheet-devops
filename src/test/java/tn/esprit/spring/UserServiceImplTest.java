package tn.esprit.spring;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	IUserService us;
	

	@Test()
	@Order(1)
	public void testRetrieveAllUsers() {
	java.util.List<User> listUsers = us.retrieveAllUsers();
	Assertions.assertEquals(11, listUsers.size());
	}
	
	
	@Test
	@Order(2)
	public void tesAddUser() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User("Adib","Nazih",d,Role.INGENIEUR);
		User userAdded = us.addUser(u);
		Assertions.assertEquals(u.getLastName(), userAdded.getLastName());
		
	}
	
	
	@Test
	@Order(3)
	public void tesModifyUser() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User("Adib","Nazih",d,Role.INGENIEUR);
		User userAdded = us.addUser(u);
		User u2 = new User("Adib","Ben Nour",d,Role.INGENIEUR);
		User userModified = us.updateUser(u2);
		Assertions.assertEquals(userModified.getLastName(), "Ben Nour");
		
	}
	
	@Test
	@Order(4)
	public void tesDeleteUser() throws ParseException {
		us.deleteUser("1");
		Assertions.assertNull(us.retrieveUser("1"));

		
	}
	@Test
	@Order(5)
	public void tesRetriveUser() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User("Adib","Nazih",d,Role.INGENIEUR);
		User userAdded = us.addUser(u);
		Assertions.assertTrue(!us.retrieveAllUsers().contains(userAdded));

		
	}

}
