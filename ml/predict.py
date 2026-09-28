import joblib
import pandas as pd


# Load trained model
model = joblib.load("crop_recommendation_model.pkl")


# Example field conditions
data = pd.DataFrame([
    {
        "temperature": 28,
        "humidity": 75,
        "rainfall": 180,
        "soil_type": "Clay soil"
    }
])


# Predict crop
prediction = model.predict(data)[0]


# Get prediction probabilities
probabilities = model.predict_proba(data)[0]

classes = model.classes_

confidence = probabilities.max() * 100


print("Recommended Crop:", prediction)
print("Confidence:", round(confidence, 2), "%")


# Show top 3 recommendations
results = sorted(
    zip(classes, probabilities),
    key=lambda x: x[1],
    reverse=True
)

print("\nTop recommendations:")

for crop, probability in results[:3]:
    print(
        crop,
        "-",
        round(probability * 100, 2),
        "%"
    )