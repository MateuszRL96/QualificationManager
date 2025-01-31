import os
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
import joblib

# Załaduj dane
data = pd.read_csv('/home/mateuszkulec/Dokumenty/QualificationManager/src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Data/expanded_skills_data.csv')

# Przygotuj dane
X = data.drop('target', axis=1)
y = data['target']

# Podziel dane na zestawy treningowe i testowe
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Wybierz i wytrenuj model
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

# Przewiduj na zestawie testowym
y_pred = model.predict(X_test)

# Oceń model
mse = mean_squared_error(y_test, y_pred)
print(f'Mean Squared Error: {mse}')

# Upewnij się, że katalog istnieje
os.makedirs('DL4J/Prediction', exist_ok=True)

# Zapisz model do pliku
joblib.dump(model, 'DL4J/Prediction/recommendation_model.pkl')

# Wyświetl zawartość modelu
print(model)