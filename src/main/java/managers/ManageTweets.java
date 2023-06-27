package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	
	public ManageTweets() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO Tweet (userId,username,date,text) VALUES (?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getUid());
			statement.setString(2,tweet.getUname());
			statement.setTimestamp(3,tweet.getPostDateTime());
			statement.setString(4,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id,Integer uid) {
		String query = "DELETE FROM Tweet WHERE id = ? AND userId=?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Change(update) the content of a tweet given its new content and its id */
	public void editTweet(String new_content,Integer id) {
		String query = "UPDATE Tweet SET text = ? WHERE id = ?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1,new_content);
			statement.setInt(2,id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer uid,Integer start, Integer end) {
		 String query = "SELECT Tweet.id,Tweet.userId,Tweet.date,Tweet.text,User.username FROM Tweet INNER JOIN User ON Tweet.userId = User.id where Tweet.userId = ? ORDER BY Tweet.date DESC LIMIT ?,? ;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("userId"));
				 tweet.setPostDateTime(rs.getTimestamp("date"));
				 tweet.setContent(rs.getString("text"));
				 tweet.setUname(rs.getString("username"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	/* Get tweets from followed users*/
	public List<Tweet> getFollowerTweets(Integer uid,Integer start, Integer end) {
		 String query = "SELECT Tweet.id, Tweet.userId, Tweet.date, Tweet.text, User.username "
	             + "FROM Tweet "
	             + "INNER JOIN User ON Tweet.userId = User.id "
	             + "WHERE (Tweet.userId = ? OR Tweet.userId IN (SELECT followedId FROM Following WHERE followedId = ?)) "
	             + "ORDER BY Tweet.date DESC LIMIT ?, ?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("userId"));
				 tweet.setPostDateTime(rs.getTimestamp("date"));
				 tweet.setContent(rs.getString("text"));
				 tweet.setUname(rs.getString("username"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}

	
	
	
}