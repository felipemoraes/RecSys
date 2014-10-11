package pa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ItemBasedRecommender {
	public HashMap<Integer, User> users;
	public HashMap<Integer, HashMap<Integer,Integer>> ratings;
	public HashMap<Integer, HashMap<Integer,Double>> items;
	public ItemBasedRecommender(HashMap<Integer, HashMap<Integer,Integer>> ratings, HashMap<Integer, User> users) throws NumberFormatException, IOException {
		this.ratings = ratings;
		this.users = users;
		this.items = itemsSimilarities("item_similarities");
		
	}
	public HashMap<Integer, HashMap<Integer,Double>> itemsSimilarities(String nameFile) throws NumberFormatException, IOException{
		HashMap<Integer, HashMap<Integer,Double>> similarities = new HashMap<Integer, HashMap<Integer,Double>>();
		String s;
		//Read file and process each line putting in the R matrix
		Integer itemA, itemB;
		Double similarity;
		BufferedReader in = new BufferedReader(new FileReader(nameFile));
        while((s = in.readLine()) != null){
            String[] var = s.split(" ");
            itemA = Integer.parseInt(var[0]);
            itemB = Integer.parseInt(var[1]);
            similarity = Double.parseDouble(var[2]);
          if (similarities.containsKey(itemA)){
        	  similarities.get(itemA).put(itemB, similarity);
            } else {
            	similarities.put(itemA, new HashMap<Integer, Double>());
            	similarities.get(itemA).put(itemB, similarity);
            }
        }
        in.close();
        return similarities;
	}
	
	public Double predictingItemRating(Integer user, Integer item, Integer K){
		Double sumItemSimsTimesRating = 0.0, sumSimsItems = 0.0, prediction = 0.0;
		ArrayList<KeyValue> ratedItems = new ArrayList<KeyValue>();
		for (Integer i : ratings.get(user).keySet()) {
			if (items.get(i).containsKey(item)){
				Double simItem = items.get(i).get(item);
				ratedItems.add(new KeyValue(i, simItem));
			}
		}
		Collections.sort(ratedItems, new KeyValueComparator());
		if (K > ratedItems.size()){
			K = ratedItems.size();
		}
		for (int i = 0; i < K; i++) {
			KeyValue ratedItem = ratedItems.get(i);
			if (ratedItem.value >= 0) {
				sumItemSimsTimesRating += ratedItem.value*ratings.get(user).get(ratedItem.key);
				sumSimsItems += ratedItem.value;
			}
		}
		if (sumItemSimsTimesRating > 0) 
			prediction = sumItemSimsTimesRating/sumSimsItems;
		return prediction;
		
	}
}
