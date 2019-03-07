import java.util.List;

public class PerceptronLearner {
    
    /**
     * @author Thomas Gian
     * @id 0995114
     * 
     * The method that implements perceptron learning
     * @return answer string
     */
    public String execute(List<PVector> positive, List<PVector> negative, Boolean bias, Integer maxIterations, List<PVector> queries)
    {
        /*
        * If bias = true, add 1 to all PVectors
        */
        if (bias) {
            for (PVector x : positive) {
                x.addCoord(1);
            }
            for (PVector x : negative) {
                x.addCoord(1);
            }
            for (PVector x : queries) {
                x.addCoord(1);
            }
        }
        
        /*
        * Initiate weight vector w
        */
        int n = positive.get(0).size();
        PVector w = PVector.constant(n, 1);
        
        /*
        * Learning algorithm for w
        */
        int iterations = 0; // Keeps track of amount of iterations
        boolean modifiedW = false; // Keeps track whether w is modified last iteration
        
        do {
            modifiedW = false;
            for (PVector x : positive) {
                if (w.dotProduct(x) <= 0) {
                    w = w.add(x);
                    modifiedW = true;
                }
            }
            for (PVector x : negative) {
                if (w.dotProduct(x) > 0) {
                    w = w.subtract(x);
                    modifiedW = true;
                }
            }
            iterations++;
        } while (iterations < maxIterations && modifiedW);
        
        /*
        * Return the answer in the form of iterations
        * with + and - if it was within maxIterations
        */
        String answer = Integer.toString(iterations) + " ";
        if (iterations < maxIterations) {
            for (PVector x : queries) {
                if (w.dotProduct(x) > 0) {
                    answer += "+";
                } else {
                    answer += "-";
                }
            }
        }
        
        return answer;
    }
}
