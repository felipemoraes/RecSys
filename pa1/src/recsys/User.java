package recsys;

import java.util.TreeMap;

public class User {
	public String gender, profession;
	public Integer id;
	private TreeMap<Integer, Double> kSimilars;
	private TreeMap<Integer, Double> itemsPredications;
	User(Integer id, String gender, String profession){
		this.id = id;
		this.gender = gender;
		this.profession = profession;
		this.kSimilars = new TreeMap<Integer, Double>();
	}
	public void setkSimilars(TreeMap<Integer, Double> similars){
		this.kSimilars = similars;
	}
	
	public TreeMap<Integer, Double> getkSimilars(){
		return this.kSimilars;
	}
	public TreeMap<Integer, Double> getItemsPredications() {
		return itemsPredications;
	}
	public void setItemsPredications(TreeMap<Integer, Double> itemsPredications) {
		this.itemsPredications = itemsPredications;
	}
	
}
