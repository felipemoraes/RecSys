package recsys;

import java.util.Set;

public class User {
	public String gender, profession;
	public Integer id;
	private Set<Integer> kSimilars;
	User(Integer id, String gender, String profession){
		this.id = id;
		this.gender = gender;
		this.profession = profession;
	}
	public void setkSimilars(Set<Integer> similars){
		this.kSimilars = similars;
	}
	
	public Set<Integer> getkSimilars(){
		return this.kSimilars;
	}
}
