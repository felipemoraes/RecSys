package recsys;

import java.io.IOException;
import java.util.HashMap;

public class Recommender {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String USER_FILE = args[0];
		String ITEM_FILE = args[1];
		String RATING_FILE = args[2];
		HashMap<Integer, HashMap<Integer,Integer>> ratings = LoadInput.loadRatings(RATING_FILE);
		HashMap<Integer, User> users = LoadInput.loadUsers(USER_FILE);
		HashMap<Integer, Item> items = LoadInput.loadItems(ITEM_FILE);
		UserBasedRecommender recommender = new UserBasedRecommender(ratings,users);
		for (Integer user : users.keySet()) {
			for (Integer item : items.keySet()) {
				if (!ratings.get(user).containsKey(item)){
					recommender.predictRating(users.get(user), items.get(item), 2);
				}
			}
		}

	}

}
