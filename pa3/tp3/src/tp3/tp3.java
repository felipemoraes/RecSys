package tp3;

public class tp3 {

	public static void main(String[] args) {
		
		if (args[0].equals("Indexer")) {
			
			Indexer indexer = new Indexer(args[1]);
			indexer.execute();
		} else if (args[0].equals("Recommender")) {
			System.out.println("Starting");
			Recommender recommender = new Recommender(args[1], args[2], args[3]);
			recommender.execute();
		} else if (args[0].equals("Evaluator")) {
			Evaluator evaluator = new Evaluator(args[1],args[2]);
			evaluator.execute();
		}

	}

}
