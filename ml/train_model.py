import pandas as pd
import joblib

from sklearn.model_selection import train_test_split
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
from sklearn.ensemble import RandomForestClassifier
from sklearn.pipeline import Pipeline
from sklearn.metrics import accuracy_score


# 1. Load dataset
data = pd.read_csv("Crop_recommendationV2.csv")

print("Dataset loaded successfully!")
print("Rows:", len(data))
print("Columns:")
print(data.columns.tolist())


# 2. Features we will use
features = [
    "temperature",
    "humidity",
    "rainfall",
    "soil_type"
]

target = "label"


# 3. Remove rows with missing values
data = data.dropna(subset=features + [target])


X = data[features]
y = data[target]


# 4. Separate categorical and numerical features
categorical_features = ["soil_type"]

numerical_features = [
    "temperature",
    "humidity",
    "rainfall"
]


# 5. Convert soil type into numerical values
preprocessor = ColumnTransformer(
    transformers=[
        (
            "soil",
            OneHotEncoder(handle_unknown="ignore"),
            categorical_features
        ),
        (
            "numbers",
            "passthrough",
            numerical_features
        )
    ]
)


# 6. Create Random Forest model
model = RandomForestClassifier(
    n_estimators=200,
    random_state=42
)


# 7. Create ML pipeline
pipeline = Pipeline(
    steps=[
        ("preprocessor", preprocessor),
        ("model", model)
    ]
)


# 8. Split dataset into training and testing data
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42,
    stratify=y
)


# 9. Train the model
print("\nTraining model...")

pipeline.fit(X_train, y_train)


# 10. Test the model
predictions = pipeline.predict(X_test)

accuracy = accuracy_score(y_test, predictions)

print("\nModel training completed!")
print("Accuracy:", round(accuracy * 100, 2), "%")


# 11. Save trained model
joblib.dump(
    pipeline,
    "crop_recommendation_model.pkl"
)

print("\nModel saved successfully!")
print("File: crop_recommendation_model.pkl")