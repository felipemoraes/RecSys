package tp3;

public class Evaluator {

	public Evaluator(String groundTruthFilename, String outputFilename) {
		Metrics metrics = new Metrics(groundTruthFilename, outputFilename);
		System.out.println(metrics.RMSE());
	}

	public void execute(){
		
	}
}
