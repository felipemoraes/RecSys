package tp3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Indexer {
	
	private HashMap<String,Integer> bagOfWords;
	private String itemsFilename;
	public Indexer(String itemsFilename) {
		this.itemsFilename = itemsFilename;
		this.bagOfWords = new HashMap<String,Integer>();
		JSONParser parser = new JSONParser();
		String s;
		Integer count = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.itemsFilename));
			while((s = in.readLine()) != null){
	            String[] var = s.split(" ", 2);
	            Object obj = parser.parse(var[1]);
	            JSONObject jsonObject = (JSONObject) obj;
	            String plot = (String) jsonObject.get("Plot");
	            String genre = (String) jsonObject.get("Genre");
	            String director = (String) jsonObject.get("Director");
	            String terms = plot + " " + genre + " " + director;
	            if (plot != null) {
		            String[] words = terms.split("[^a-zA-Z']+");
		            for (String word : words) {
			            if (!bagOfWords.containsKey(word.toLowerCase())) {
			            	bagOfWords.put(word.toLowerCase(), count);
			            	count++;
						}
					}
				}
	        }
	        in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
			
		} 
	}

	public void execute(){
		invertedIndex();
		directedIndex();
	}
	
	// map from a term to a list of movies that contain the term
	public void invertedIndex(){
		
		JSONParser parser = new JSONParser();
		String s;
		HashMap<Integer, ArrayList<Integer>> localIndex = new HashMap<Integer, ArrayList<Integer>>();
		for (Integer item : bagOfWords.values()) {
			localIndex.put(item, new ArrayList<Integer>());
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.itemsFilename));
			while((s = in.readLine()) != null){
	            String[] var = s.split(" ", 2);
	            Integer index = Integer.parseInt(var[0]);
	            Object obj = parser.parse(var[1]);
	            JSONObject jsonObject = (JSONObject) obj;
	            String plot = (String) jsonObject.get("Plot");
	            String genre = (String) jsonObject.get("Genre");
	            String director = (String) jsonObject.get("Director");
	            String terms = plot + " " + genre + " " + director;
	            if (plot != null) {
		            String[] words = terms.split("[^a-zA-Z']+");
		            for (String term : words) {
			            Integer termKey = bagOfWords.get(term.toLowerCase());
			            localIndex.get(termKey).add(index);
					}
				}

	        }
	        in.close();
	        
	        printOutIndex(localIndex, "output/inverted_index");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//  map from a movie to a list of terms contained by the movie
	public void directedIndex(){
		JSONParser parser = new JSONParser();
		String s;
		HashMap<Integer, ArrayList<Integer>> localIndex = new HashMap<Integer, ArrayList<Integer>>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(this.itemsFilename));
			while((s = in.readLine()) != null){
	            String[] var = s.split(" ", 2);
	            Integer index = Integer.parseInt(var[0]);
	            localIndex.put(index, new ArrayList<Integer>());
	            Object obj = parser.parse(var[1]);
	            JSONObject jsonObject = (JSONObject) obj;
	            String plot = (String) jsonObject.get("Plot");
	            String genre = (String) jsonObject.get("Genre");
	            String director = (String) jsonObject.get("Director");
	            String terms = plot + " " + genre + " " + director;
	            if (plot != null) {
		            String[] words = terms.split("[^a-zA-Z']+");
		            for (String term : words) {
		            	Integer termKey = bagOfWords.get(term.toLowerCase());
		            	localIndex.get(index).add(termKey);
					}
				}

	        }
	        in.close();

	        printOutIndex(localIndex, "output/directed_index");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void printOutIndex(HashMap<Integer, ArrayList<Integer>> index, String filename) throws IOException{
	    BufferedWriter fout = new BufferedWriter(new FileWriter(filename));
	    for (Integer key : index.keySet()) {
			fout.write(key + " ");
			for (Integer  item: index.get(key)) {
				fout.write(item + " ");
			}
			fout.write("\n");
		}
	    fout.close();
	}
}
