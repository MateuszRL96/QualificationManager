package com.qulificationRecomendation.qulificationRecomendation.Services;

import com.qulificationRecomendation.qulificationRecomendation.Repo.RecommendationRepository;
import com.qulificationRecomendation.qulificationRecomendation.DL4J.ModelConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataPreparationService {
    private static final Logger logger = LoggerFactory.getLogger(DataPreparationService.class);
    private final MultiLayerNetwork model;
    private final RecommendationRepository recommendationRepository;

    public DataPreparationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
        this.model = ModelConfiguration.createModel(50, 10); // Set input size to 50 and output size to 10
    }

    public List<Object[]> getQualificationsByUserId(Long userId) {
        return recommendationRepository.findQualificationsByUserId(userId);
    }

    public String getProposedProfession(List<Object[]> qualifications) {
        double[] input = new double[50]; // Adjust the size based on the number of qualifications

        // Map qualifications to indices
        Map<String, Integer> skillIndexMap = createSkillIndexMap();

        // Process qualifications to fill the input array
        for (Object[] qualification : qualifications) {
            String skill = (String) qualification[0];
            Integer level = (Integer) qualification[1];

            // Map skill to an index and set the level
            int skillIndex = skillIndexMap.getOrDefault(skill.toLowerCase(), -1);
            if (skillIndex >= 0 && skillIndex < input.length) {
                input[skillIndex] = level;
            }
        }

        // Create INDArray from the input array
        INDArray inputArray = Nd4j.create(input).reshape(1, input.length);

        // Log the input array
        logger.info("Input array: {}", inputArray);

        // Perform model inference
        INDArray output = model.output(inputArray);

        // Log the output array
        logger.info("Output array: {}", output);

        // Get the index of the predicted profession
        int professionIndex = Nd4j.argMax(output, 1).getInt(0);

        // Log the predicted profession index
        logger.info("Predicted profession index: {}", professionIndex);

        // Map the index to the profession
        String[] professions = {"Backend Developer", "Frontend Developer", "Full Stack Developer", "Data Scientist", "Machine Learning Engineer", "DevOps Engineer", "Database Administrator", "Cloud Engineer", "Cybersecurity Specialist", "Mobile Developer"};
        String proposedProfession = professions[professionIndex];

        // Log the proposed profession
        logger.info("Proposed profession: {}", proposedProfession);

        return proposedProfession;
    }

    // Helper method to create a map of skills to indices
    private Map<String, Integer> createSkillIndexMap() {
        Map<String, Integer> skillIndexMap = new HashMap<>();
        String[] skills = {
                "node.js", "azure", "html", "javascript", "slack", "bootstrap", "spring boot", "go", "python", "gcp",
                "ruby on rails", "rust", "vue.js", "typescript", "sql", "react", "c++", "angular", "terraform", "github",
                "java", "kotlin", "kubernetes", "jenkins", "aws", "asana", "docker", "gitlab", "django", "symfony",
                "puppet", "c#", "ansible", "flask", "chef", "php", "css", "git", "confluence", "laravel", "express.js",
                "asp.net", "swift", "bitbucket", "jira", "trello", "ruby"
        };

        for (int i = 0; i < skills.length; i++) {
            skillIndexMap.put(skills[i].toLowerCase(), i);
        }

        return skillIndexMap;
    }
}