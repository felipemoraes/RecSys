package recsys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
 
public class UserBasedRecommender {
	public HashMap<Integer, HashMap<Integer,Integer>> ratings;
	public HashMap<Integer, User> users;
	public UserBasedRecommender(HashMap<Integer, HashMap<Integer,Integer>> ratings, HashMap<Integer, User> users){
		this.ratings = ratings;
		this.users = users;
	}
	
	public float averageRatings(HashMap<Integer,Integer> ratings){
		float average = 0;
		Iterator<Integer> iter;
		iter = ratings.keySet().iterator();
		while(iter.hasNext()){
			Integer key = iter.next();
			average += ratings.get(key);
		}
		return average /= ratings.size();
	}
	public double Similarity(Integer userId1, Integer userId2){
		double sim = 0;
		float averageRatingUser1, averageRatingUser2 = 0;
		double top = 0, bottom = 0, bottom1 = 0, bottom2 = 0;
		HashMap<Integer,Integer> user1Ratings = this.ratings.get(userId1);
		averageRatingUser1 = averageRatings(user1Ratings);
		HashMap<Integer,Integer> user2Ratings = this.ratings.get(userId2);
		averageRatingUser2 = averageRatings(user2Ratings);
		for (Integer item1 : user1Ratings.keySet()) {
			for (Integer item2 : user2Ratings.keySet()) {
					if (item1 == item2){
						top += (user1Ratings.get(item1) - averageRatingUser1)*(user2Ratings.get(item2) - averageRatingUser2);
						bottom1 += Math.pow((user1Ratings.get(item1) - averageRatingUser1),2);
						bottom2 += Math.pow((user2Ratings.get(item2) - averageRatingUser2),2);
					}
				
			}
			
		}
		bottom = Math.sqrt(bottom1)*Math.sqrt(bottom2);
		sim = top / bottom;
		return sim;
	}

	public Set<Integer> kNearestNeighbors(Integer userId, Integer k){
		Set<Integer> kSimilars;
		TreeMap<Integer, Double> simValues = new TreeMap<Integer, Double>();
		for (Integer user : this.users.keySet()) {
			simValues.put(user, Similarity(user, userId));
		}
		kSimilars = simValues.keySet();
		return kSimilars;
	}
	
	public Double predictRating(User user, Item item, Integer k){
		float averageRating = averageRatings(ratings.get(user.id));
		double simalarities = 0, aux = 0, sim;
		Set<Integer> simalars = user.getkSimilars();
		Iterator<Integer> iter = simalars.iterator();
		for (int i = 0; i < k; i++) {
			sim = Similarity(user.id, iter.next());
			simalarities += sim;
			aux += sim * (ratings.get(user.id).get(item.id) -  averageRatings(ratings.get(user.id)));
		}		
		return averageRating + (aux/simalarities);
	}

}
