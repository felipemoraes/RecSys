package pa2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AveragePrecision {
	public HashMap<Integer, ArrayList<KeyValue>> recSet;
	public HashMap<Integer, ArrayList<KeyValue>> realSet;
	
	public AveragePrecision(String groundTruth, String outputRecommendation) throws NumberFormatException, IOException{
		recSet = LoadInput.loadResultSet(groundTruth);
		realSet = LoadInput.loadResultSet(outputRecommendation);
	}
	
	public Double getAPForUser(Integer userId, Integer n){
		ArrayList<KeyValue> recList = recSet.get(userId);
		ArrayList<KeyValue> realList = realSet.get(userId);
		Double sum = 0.0;
		Double numHits = 0.0;
		for (int i = 0; i < n; i++) {
			KeyValue rate = recList.get(i);
            for (int j = 0; j < realList.size(); j++) {
				if (rate.key == realList.get(j).key) {
					numHits += 1.0;
					sum += numHits/(i+1.0);
					break;
				}
			}
		}
        if (sum == 0)
        	return 0.0;
        return sum/n;
	}
	
    public Double getMAP(Integer n){
        Double sum = 0.0;
        for(Integer userId : realSet.keySet()){
                sum += getAPForUser(userId, n);
        }
        return sum/realSet.keySet().size();
    }

}
