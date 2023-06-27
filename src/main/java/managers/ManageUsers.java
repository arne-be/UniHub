package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import models.User;
import utils.DB;

/* IMPORTS WE ADDED */
import java.sql.Date;


public class ManageUsers {
	
	private DB db = null ;
	
	public ManageUsers() {
		try {
			db = new DB();
			System.out.println("DB connected");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB connection failed");
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
			System.out.println("DB disconnected");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("DB disconnection failed");
		}
	}
	
	/* Get a user given its PK*/
	public User getUser(Integer id) {
		String query = "SELECT id,username,name,surname,mail,tel,dob,usertype,about FROM User WHERE id = ? ;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setMail(rs.getString("mail"));
				user.setPhone(rs.getString("tel"));
				user.setDatebirth(rs.getDate("dob"));
				user.setUsertype(rs.getString("usertype"));
				user.setAbout(rs.getString("about"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}
	
	// get a user given its username
	public User getUser(String username) {
		String query = "SELECT id,username,name,surname,mail,tel,dob,usertype,about FROM User WHERE username = ?;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,username);
			rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setMail(rs.getString("mail"));
				user.setPhone(rs.getString("tel"));
				user.setDatebirth(rs.getDate("dob"));
				user.setUsertype(rs.getString("usertype"));
				user.setAbout(rs.getString("about"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}
		
	// Add new user
	public void addUser(User user) {
		String query = "INSERT INTO User (username, name, surname, mail, tel, dob, pwd, usertype) " +
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getName());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getMail());
			statement.setString(5, user.getPhone());
			statement.setDate(6, user.getDatebirth());
			statement.setString(7, user.getPwd());
			statement.setString(8, user.getUsertype());
			statement.executeUpdate();
			statement.close();
			System.out.println("User added: "+user.getUsername());
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Follow a user
	public void followUser(Integer uid, Integer fid) {
		String query = "INSERT INTO Following (userid,followedId) VALUES (?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Unfollow a user
	public void unfollowUser(Integer uid, Integer fid) {
		String query = "DELETE FROM Following WHERE userId = ? AND followedId = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,uid);
			statement.setInt(2,fid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Get all the users
	public List<User> getUsers(Integer start, Integer end) {
		 String query = "SELECT id,username FROM User ORDER BY username ASC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,start);
			 statement.setInt(2,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setUsername(rs.getString("username"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getNotFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT id,username FROM User WHERE id NOT IN (SELECT id FROM User,Following WHERE User.id = Following.userId AND Following.userId = ?) AND id <> ? ORDER BY name LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2, id);
			 statement.setInt(3,start);
			 statement.setInt(4,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setUsername(rs.getString("username"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public List<User> getFollowedUsers(Integer id, Integer start, Integer end) {
		 String query = "SELECT User.id,User.username FROM User,Following WHERE User.id = Following.followedId AND Following.userId = ? ORDER BY username LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<User> l = new ArrayList<User>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,id);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 User user = new User();
				 user.setId(rs.getInt("id"));
				 user.setUsername(rs.getString("username"));
				 l.add(user);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	public Pair<Boolean,User> checkLogin(User user) {
		
		String query = "SELECT id,mail from User where username=? AND pwd=?";
		PreparedStatement statement = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getPwd());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setMail(rs.getString("mail"));
				output = true;
			} 
			rs.close();
			statement.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Pair.of(output,user);
		
	}
	
	public boolean checkUser(String user) {
			
			String query = "SELECT username from User where username=?";
			PreparedStatement statement = null;
			ResultSet rs = null;
			boolean output = false;
			try {
				
				statement = db.prepareStatement(query);
				statement.setString(1,user);
				rs = statement.executeQuery();
				if (rs.isBeforeFirst()) {
					output = true;
				}
				rs.close();
				statement.close();
				return output;
				
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("We are facing errors trying to get the username from our database.");
			}
			
			return output;
			
		}
	
	public boolean checkMail(String mail) {
		
		String query = "SELECT mail from User where mail=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,mail);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("We are facing errors trying to get the emails from our database.");
		}
		return output;
		
	}
		
	/*Check if all the fields are filled correctly */
	public boolean isComplete(User user) {
		if(hasValue(user.getName()) &&
			hasValue(user.getSurname()) &&
			hasValue(user.getUsername()) &&
			hasValue(user.getMail()) &&
			hasValue(user.getPhone()) &&
			has_DateValue(user.getDatebirth()) && //modificar
			hasValue(user.getPwd()) &&
			hasValue(user.getPwd2())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isLoginComplete(User user) {
	    return(hasValue(user.getName()) &&
	    	   hasValue(user.getPwd()) );
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
		
	
	// TODO: add other methods
	public boolean checkPhone(String phone) {
		
		String query = "SELECT tel from User where tel=?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		boolean output = false;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,phone);
			rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				output = true;
			}
			rs.close();
			statement.close();
			return output;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("We are facing errors trying to get the phone numbers from our database.");
		}
		return output;
		
	}

	private boolean has_DateValue(Date val) {
		return((val != null));
	}
	

	public boolean loginUser(String username, String pwd) {
		boolean exists = false;
		PreparedStatement ps = null;
	    try {
	        ps = db.prepareStatement("SELECT * FROM User WHERE username = ? AND pwd = ?");
	        ps.setString(1, username);
	        ps.setString(2, pwd);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	        	exists=true;
	        }
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	    	System.out.println("Username or password is incorrect...");
	        e.printStackTrace();
	    }
	    return exists;
	}
	
	/*EDIT PROFILE */
	public boolean editComplete(User user) {
		return(hasValue(user.getUsername()) &&
		    	   hasValue(user.getName()) &&
		    	   hasValue(user.getMail()) &&
		    	   user.getAbout() != null &&
		    	   has_DateValue(user.getDatebirth()));
	}
	public void editProfile(User user) {
		String query = "UPDATE User SET username = ?, name = ?, mail = ?, about = ?, dob = ? WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getName());
			statement.setString(3,user.getMail());
			statement.setString(4,user.getAbout());
			statement.setDate(5,user.getDatebirth());
			statement.setInt(6,user.getId());
			statement.executeUpdate();
			statement.close();
			System.out.println("succesful profile edit with name "+user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Deletes a user given its id */
	public void deleteUser(Integer target_id) {
		String query = "DELETE FROM TweetLikes WHERE userId=?;"; // Delete likes
		String query1 = "DELETE FROM Tweet WHERE userId =?;"; // Delete the tweet
		String query2 = "DELETE FROM Following WHERE userId = ? or followedId = ?;";
		String query3 = "DELETE FROM User WHERE id = ?;"; // Delete first the likes of that tweet(avoid fk errors)
		
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;

		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,target_id);
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			statement1 = db.prepareStatement(query1);
			statement1.setInt(1,target_id);
			statement1.executeUpdate();
			statement1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			statement2 = db.prepareStatement(query2);
			statement2.setInt(1,target_id);
			statement2.setInt(2,target_id);
			statement2.executeUpdate();
			statement2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			statement3 = db.prepareStatement(query3);
			statement3.setInt(1,target_id);
			statement3.executeUpdate();
			statement3.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Check given user of the session and another user, if the first follows the second one */
	public boolean isFollowed(Integer uid, Integer fid) {
		boolean isFollowed = false;
		String query = "SELECT * FROM Following WHERE userId= ? AND followedId=?";
		PreparedStatement statement = null;
		try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,fid);
			 ResultSet rs = statement.executeQuery();
			 if (rs.next()) { // If the query returns something
				 isFollowed = true;
			 } else {
				 isFollowed = false;
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isFollowed;
	}

}