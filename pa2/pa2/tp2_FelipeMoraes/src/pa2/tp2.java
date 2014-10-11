package pa2;

import java.io.IOException;

public class tp2 {

	public static void main(String[] args) throws IOException {
		System.out.println(args[0]);
		if (args[0].equals("Indexer")){
			String[] newArgs = {args[1], args[2], args[3]};
			Indexer indexer = new Indexer(newArgs);
		} else if (args[0].equals("Recommender")){
			String[] newArgs = {args[1], args[2], args[3]};
			Recommender recommender = new Recommender(newArgs);
		} else if (args[0].equals("Evaluator")) {
			String[] newArgs = {args[1], args[2]};
			Evaluator evaluator = new Evaluator(newArgs);
		} else {
			System.err.println("Invalid parameters");
		}

	}

}
