package tp3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Recommender {
	private RocchioRecommender recommender;
	public Recommender(String usersFilename, String itemsFilename, String ratingsFilename) {
		recommender = new RocchioRecommender(usersFilename, ratingsFilename,
				"output/inverted_index", "output/directed_index");
	}

	public void execute(){
		BufferedWriter fout;
		try {
			fout = new BufferedWriter(new FileWriter("output/output"));
			for (Integer user : recommender.getUsers()) {
				ArrayList<KeyValue> predictions = new ArrayList<KeyValue>();
				for (Integer item : recommender.getItems()) {
					if (!recommender.userContainsItem(user,item)) {
						Float score = this.recommender.predictRating(user, item);
						predictions.add(new KeyValue(item, score));
					}
				}
				Collections.sort(predictions, new KeyValueComparator());
				for (int i = 0; i < 100; i++) {
					fout.write(user + "	" + predictions.get(i).key + "	" + predictions.get(i).value + "\n" );		
				}
				
			}
			fout.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
