package com.qulificationRecomendation.qulificationRecomendation.DL4J;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

public class ModelTraining {
    public static void trainModel(MultiLayerNetwork model, DataSetIterator trainData, int numEpochs) {
        for (int i = 0; i < numEpochs; i++) {
            model.fit(trainData);
        }
    }
}