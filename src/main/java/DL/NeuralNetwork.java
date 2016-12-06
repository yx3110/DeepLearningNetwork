package DL;

import java.util.List;

/**
 * Created by Yang Xu on 06/12/2016.
 */
public class NeuralNetwork {

    private static final String url = "";
    public NeuralNetwork(boolean isTraining) {
        if(isTraining)initNewNN();
        else loadNN(url);
    }

    private void loadNN(String url) {

    }

    private void initNewNN() {

    }

    public Double evaluate(List<List<Double>> valMatrix) {
        // TODO: 06/12/2016 build
        double res = 0;
        return new Double(res);
    }
}
