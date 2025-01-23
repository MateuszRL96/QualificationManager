package com.qulificationRecomendation.qulificationRecomendation.DL4J;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

public class ModelEvaluation {
    public static void evaluateModel(MultiLayerNetwork model, DataSetIterator testData) {
        Evaluation eval = new Evaluation();
        while (testData.hasNext()) {
            eval.eval(testData.next().getLabels(), model.output(testData.next().getFeatures()));
        }
        System.out.println(eval.stats());
    }
}