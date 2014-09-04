package recsys;

import java.util.ArrayList;
import recsys.KeyValue;

/*  This class is used to manage similars users and 
 * maintain a predict list to print out.*/

public class User {
	public String gender, profession;
	public Integer id;
	private ArrayList<KeyValue> kSimilars;
	private ArrayList<KeyValue> itemsPredications;
	// Constructor
	User(Integer id, String gender, String profession){
		this.id = id;
		this.gender = gender;
		this.profession = profession;
		this.kSimilars = new ArrayList<KeyValue>();
	}
	// setter and getters of a similars list
	public void setkSimilars(ArrayList<KeyValue> similars){
		this.kSimilars = similars;
	}
	
	public ArrayList<KeyValue> getkSimilars(){
		return this.kSimilars;
	}
	public ArrayList<KeyValue> getItemsPredications() {
		return itemsPredications;
	}
	public void setItemsPredications(ArrayList<KeyValue> itemsPredications) {
		this.itemsPredications = itemsPredications;
	}
	
}
