//package com.qulificationRecomendation.qulificationRecomendation.DL4J;
//
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
//import org.nd4j.linalg.factory.Nd4j;
//
//public class DL4JWorkflow {
//    public static void main(String[] args) throws Exception {
//        // Krok 1: Załaduj i przygotuj dane
//        String trainDataPath = "/home/mateusz/Documents/QualificationManager/src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Data/dataset.csv";
//        int batchSize = 64;
//        int labelIndex = 2; // Indeks kolumny etykiety
//        int numClasses = 3; // Liczba klas
//
//        DataSetIterator trainData = DataPreparation.loadData(trainDataPath, batchSize, labelIndex, numClasses);
//
//        // Krok 2: Skonfiguruj i wytrenuj model
//        int inputSize = 2; // Liczba cech
//        int outputSize = numClasses;
//
//        MultiLayerNetwork model = ModelConfiguration.createModel(inputSize, outputSize);
//        int numEpochs = 10;
//        ModelTraining.trainModel(model, trainData, numEpochs);
//
//        // Krok 3: Oceń model
//        ModelEvaluation.evaluateModel(model, trainData);
//
//        // Krok 4: Użyj modelu do predykcji
//        // Przykładowe dane wejściowe
//        INDArray input = Nd4j.create(new float[]{/* wartości cech */}, new int[]{1, inputSize});
//        INDArray output = ModelInference.predict(model, input);
//        System.out.println("Prediction: " + output);
//    }
//}