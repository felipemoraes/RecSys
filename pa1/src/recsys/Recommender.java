package recsys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Recommender {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// Get file from a user
		// Attetion to run via jar file you need change the range of args, starting from 1
		String USER_FILE = args[1];
		String ITEM_FILE = args[2];
		String RATING_FILE = args[3];
		// Load all input files
		HashMap<Integer, HashMap<Integer,Integer>> ratings = LoadInput.loadRatings(RATING_FILE);
		HashMap<Integer, User> users = LoadInput.loadUsers(USER_FILE);
		HashMap<Integer, Item> items = LoadInput.loadItems(ITEM_FILE);
		UserBasedRecommender recommender = new UserBasedRecommender(ratings,users);
		BufferedWriter fout = new BufferedWriter(new FileWriter("output"));
		// Calculate for each user the predictions and print out to a output file
		for (Integer user : users.keySet()) {
			ArrayList<KeyValue> predictions = new ArrayList<KeyValue>();
			Integer count = 0;
			// Get the prediction for each item that was not rating, totalling 100
			for (Integer item : items.keySet()) {
				if (!ratings.get(user).containsKey(item) && count < 100){
					predictions.add( new KeyValue (item, recommender.predictRating(users.get(user), items.get(item), 100)));
					count++;
				}
			}
			// Sort predictions from and print out to a file
			Collections.sort(predictions, new KeyValueComparator());
			recommender.users.get(user).setItemsPredications(predictions);
			for (KeyValue item : recommender.users.get(user).getItemsPredications()){
				fout.write(user + "\t" + item.key + "\t" + item.value + "\n");
			}
		}
		fout.close();

	}

}
