package pa2;

import java.io.IOException;

public class Evaluator {

	public Evaluator(String[] args) throws NumberFormatException, IOException {
		String groundTruth = args[0];
		String outputRecommendation = args[1];
		AveragePrecision avgPrecison = new AveragePrecision(groundTruth, outputRecommendation);
		System.out.printf("->%.5f", avgPrecison.getMAP(2));
		
	}
	


}
