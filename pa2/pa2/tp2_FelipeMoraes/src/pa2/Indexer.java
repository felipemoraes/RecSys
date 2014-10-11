package pa2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Indexer {
	public Indexer(String[] args) throws IOException {
		String ITEM_FILE = args[1];
		String RATING_FILE = args[2];
		FileWriter fw = new FileWriter("item_similarities");
		// Load all input files
		HashMap<Integer, HashMap<Integer,Integer>> ratings = LoadInput.loadRatings(RATING_FILE);
		HashMap<Integer, Item> items = LoadInput.loadItems(ITEM_FILE);
		for (Integer itemA : items.keySet()) {
			for (Integer itemB : items.keySet()) {
				if (itemA != itemB){
					fw.write(itemA+" "+itemB+" "+similarity(itemA, itemB, ratings) + "\n");
				}		
			}
			
		}
		fw.close();
	}
	
	public static Double similarity(Integer itemA, Integer itemB, HashMap<Integer, HashMap<Integer,Integer>> ratings){
		Double sim = 0.0; 
		Double sumDiffATimesdiffB = 0.0, sumDiffSqA = 0.0,sumDiffSqB = 0.0, avg;
		for (Integer user : ratings.keySet()) {
			HashMap<Integer,Integer> userRatings = ratings.get(user);
			if (userRatings.containsKey(itemA) && 
					userRatings.containsKey(itemB)){
				avg = average(userRatings);
				itemA = userRatings.get(itemA);
				itemB = userRatings.get(itemB);
				sumDiffATimesdiffB += (itemA - avg)*(itemB - avg);
				sumDiffSqA +=  (itemA - avg)*(itemA - avg);
				sumDiffSqB +=  (itemB - avg)*(itemB - avg);
			}
			if (sumDiffSqA > 0 && sumDiffSqB > 0 ) {
				sim = sumDiffATimesdiffB/ (Math.sqrt(sumDiffSqA)*Math.sqrt(sumDiffSqB));
			}
		}
		return sim;	
	}

	private static Double average(HashMap<Integer, Integer> hashMap) {
		Double average = 0.0;
		for (Integer key : hashMap.keySet()) {
			average += hashMap.get(key);
		}
		average /= hashMap.size();
		return average;
	}

}
