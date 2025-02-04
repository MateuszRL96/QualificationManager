import pandas as pd
import numpy as np

# Load the existing dataset
data = pd.read_csv('/home/mateuszkulec/Dokumenty/QualificationManager/src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Data/skills_data.csv')

# Generate additional records
num_new_records = 1000  # Number of new records to generate
new_data = {
    'skill1': np.random.randint(1, 6, num_new_records),
    'skill2': np.random.randint(1, 6, num_new_records),
    'skill3': np.random.randint(1, 6, num_new_records),
    'target': np.random.randint(1, 6, num_new_records)
}

# Create a DataFrame for the new records
new_data_df = pd.DataFrame(new_data)

# Concatenate the new records to the existing dataset
expanded_data = pd.concat([data, new_data_df], ignore_index=True)

# Save the expanded dataset to a new CSV file
expanded_data.to_csv('/home/mateuszkulec/Dokumenty/QualificationManager/src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Data/expanded_skills_data.csv', index=False)

print("Dataset expanded and saved to 'expanded_skills_data.csv'")