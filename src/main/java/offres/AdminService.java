package offres;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


	



public class AdminService {
	public static Map<String, User> users = Database.getUsers(); 
	private AtomicInteger idCounter = new AtomicInteger(1);
public  String UpdatePass(String password) {
		
		Admin.setPassword(password);
		return "password change it";
	
	
}
	public String addUser(User user) {
	    String generatedId;
	    boolean idExists;

	    do {
	        generatedId = String.valueOf(idCounter.getAndIncrement());
	        idExists = users.containsKey(generatedId);
	    } while (idExists);

	    user.setId(generatedId);
	    users.put(user.getId(), user);

	    return "User with ID " + user.getId() + " was added successfully";
	}


	public List<User> allUsers() { 
		  return new ArrayList<User>(users.values()); 
		 } 
	 
	
	 
		 public User getUser(String id){ 
			 User c = users.get(id); 
		  return c;  
		 } 

		 public String deleteUser(String id){ 
			 User c = users.remove(id); 
			  return c.getUsername()+" Deleted successfully"; 
			 }
		 
}
