package com.qulificationRecomendation.qulificationRecomendation.DL4J;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ModelInference {
    public static INDArray predict(MultiLayerNetwork model, INDArray input) {
        return model.output(input);
    }
}