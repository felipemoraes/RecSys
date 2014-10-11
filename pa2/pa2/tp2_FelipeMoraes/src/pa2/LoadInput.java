package pa2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// This class is used to load input data from command line

public class LoadInput {
	// Load ratings file
	public static HashMap<Integer, HashMap<Integer,Integer>> loadRatings(String RATING_FILE) throws IOException{
		// This is the R matrix from the User-based recommender
		HashMap<Integer, HashMap<Integer,Integer>> ratings = new HashMap<Integer, HashMap<Integer,Integer>>();
		String s;
		//Read file and process each line putting in the R matrix
		Integer userId, itemId, rating;
		BufferedReader in = new BufferedReader(new FileReader(RATING_FILE));
        while((s = in.readLine()) != null){
            String[] var = s.split("	");
            userId = Integer.parseInt(var[0]);
            itemId = Integer.parseInt(var[1]);
            rating = Integer.parseInt(var[2]);
          if (ratings.containsKey(userId)){
            	ratings.get(userId).put(itemId, rating);
            } else {
            	ratings.put(userId, new HashMap<Integer,Integer>());
            	ratings.get(userId).put(itemId, rating);
            }
        }
        in.close();
		return ratings;
	}
	
	// Method to load users information to a hashmap
	public static HashMap<Integer, User> loadUsers(String USER_FILE) throws IOException{
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		String s, gender, profession;
		Integer userId;
		BufferedReader in = new BufferedReader(new FileReader(USER_FILE));
        while((s = in.readLine()) != null){
            String[] var = s.split("\\|");
            userId = Integer.parseInt(var[0]);
            gender = var[1];
            profession = var[2];
          if (!users.containsKey(userId)){
        	  	User user = new User(userId, gender, profession);
            	users.put(userId, user);
            } 
        }
        in.close();
		return users;
	}
	// Method to load items information to a hashmap
	public static HashMap<Integer, Item> loadItems(String ITEM_FILE) throws IOException{
		HashMap<Integer, Item> items = new HashMap<Integer, Item>();
		String s, name;
		Integer itemId;
		BufferedReader in = new BufferedReader(new FileReader(ITEM_FILE));
        while((s = in.readLine()) != null){
            String[] var = s.split("\\|");
            itemId = Integer.parseInt(var[0]);
            name = var[1];
          if (!items.containsKey(itemId)){
        	  	Item item = new Item(itemId, name);
            	items.put(itemId, item);
            } 
        }
        in.close();
		return items;
	}

	public static HashMap<Integer, ArrayList<KeyValue>> loadResultSet(String nameFile) throws NumberFormatException, IOException {
		HashMap<Integer, ArrayList<KeyValue>> resultSet = new HashMap<Integer, ArrayList<KeyValue>>();
		String s;
		Integer userId, itemId;
		Double rating;
		BufferedReader in = new BufferedReader(new FileReader(nameFile));
        while((s = in.readLine()) != null){
            String[] var = s.split("\\t");
            userId = Integer.parseInt(var[0]);
            itemId = Integer.parseInt(var[1]);
            rating = Double.parseDouble(var[2]);
          if (resultSet.containsKey(userId)){
        	  	resultSet.get(userId).add(new KeyValue(itemId, rating));
            } else {
            	resultSet.put(userId, new ArrayList<KeyValue>());
            }
        }
        in.close();
		
		// TODO Auto-generated method stub
		return resultSet;
	}
}
