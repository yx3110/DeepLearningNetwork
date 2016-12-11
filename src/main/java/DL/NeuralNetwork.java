package DL;


import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.FeedForwardLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.deeplearning4j.rl4j.policy.DQNPolicy;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.util.DataManager;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.logging.Logger;

import java.util.List;


/**
 * Created by Yang Xu on 06/12/2016.
 */
public class NeuralNetwork {
    static Logger logger = Logger.getLogger(NeuralNetwork.class.getName() );

    private static int outputNum = 100;
    private static final String url = "";
    private int rngSeed=123;
    private List<Double> weights = new ArrayList<>();
    public NeuralNetwork(boolean isTraining) {
        if(isTraining)initNewNN();
        else loadNN(url);
    }

    private void loadNN(String url) {

    }

    private void initNewNN() {

    }

    public Double evaluate(List<List<Double>> valMatrix) {
        // TODO: 06/12/2016 evaluate action based on input features
        double res = 0;
        List<Double> cTypes = getCTypes(valMatrix);
        MultiLayerConfiguration first2LayersConf = new NeuralNetConfiguration.Builder().seed(rngSeed)
                .iterations(1)
                .learningRate(0.1)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(valMatrix.get(0).size() * 1) // Number of input datapoints.
                        .nOut(100) // Number of output datapoints.
                        .activation("elu") // Activation function.
                        .weightInit(WeightInit.XAVIER) // Weight initialization.
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(100)
                        .nOut(outputNum)
                        .activation("tanh")
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .pretrain(false).backprop(true)
                .build();
        MultiLayerNetwork first2LayersModel = new MultiLayerNetwork(first2LayersConf);
        first2LayersModel.init();
        logger.info("Start training");
        
        List<INDArray> firstLayerRes = new ArrayList<>();
        for(List<Double> cur:valMatrix){

        }
        addCTypes(maxPool(firstLayerRes));


        MultiLayerConfiguration second2LayersConf = new NeuralNetConfiguration.Builder().seed(rngSeed)
                .iterations(1)
                .learningRate(0.1)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(210 * 1) // Number of input datapoints.
                        .nOut(100) // Number of output datapoints.
                        .activation("elu") // Activation function.
                        .weightInit(WeightInit.XAVIER) // Weight initialization.
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(100)
                        .nOut(outputNum)
                        .activation("relu")
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .pretrain(false).backprop(true)
                .build();

        return new Double(res);
    }

    private double maxPool(List<INDArray> firstLayerRes) {
        return 0;
    }

    private List<Double> addCTypes(double res) {
        return null;
    }

    List<Double> getCTypes(List<List<Double>> valMatrix){
        List<Double> res = new ArrayList<>();
        for(List<Double> curList:valMatrix){
            double cType = curList.remove(curList.size()-1);
            res.add(cType);
        }
        return res;
    }
}
