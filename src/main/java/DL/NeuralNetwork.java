package DL;


import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.deeplearning4j.rl4j.policy.DQNPolicy;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.util.DataManager;

import java.util.logging.Logger;

import java.util.List;

/**
 * Created by Yang Xu on 06/12/2016.
 */
public class NeuralNetwork {
    public static QLearning.QLConfiguration CARTPOLE_QL =
            new QLearning.QLConfiguration(
                    123,    //Random seed
                    200,    //Max step By epoch
                    150000, //Max step
                    150000, //Max size of experience replay
                    32,     //size of batches
                    500,    //target update (hard)
                    10,     //num step noop warmup
                    0.01,   //reward scaling
                    0.99,   //gamma
                    1.0,    //td-error clipping
                    0.1f,   //min epsilon
                    1000,   //num step for eps greedy anneal
                    false //double DQN
            );

    public static DQNFactoryStdDense.Configuration CARTPOLE_NET =
            new DQNFactoryStdDense.Configuration(
                    3,         //number of layers
                    16,        //number of hidden nodes
                    0.001,     //learning rate
                    0.00       //l2 regularization
            );
    private static final String url = "";
    public NeuralNetwork(boolean isTraining) {
        DataManager manager = new DataManager(true);

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
        return new Double(res);
    }
}
