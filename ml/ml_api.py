from fastapi import FastAPI
import joblib
import pandas as pd

app = FastAPI()

# Load trained ML pipeline
model = joblib.load("crop_recommendation_model.pkl")


@app.get("/")
def home():
    return {
        "message": "AgriFlow Crop Recommendation ML API is running"
    }


@app.post("/recommend-crop")
def recommend_crop(
    temperature: float,
    humidity: float,
    rainfall: float,
    soil_type: str
):

    # Create input data
    data = pd.DataFrame([
        {
            "temperature": temperature,
            "humidity": humidity,
            "rainfall": rainfall,
            "soil_type": soil_type
        }
    ])

    # Predict crop
    prediction = model.predict(data)[0]

    # Prediction probabilities
    probabilities = model.predict_proba(data)[0]

    classes = model.classes_

    probability = probabilities.max() * 100

    # Top 3 recommendations
    results = sorted(
        zip(classes, probabilities),
        key=lambda x: x[1],
        reverse=True
    )

    top_recommendations = []

    for crop, crop_probability in results[:3]:
        top_recommendations.append({
            "crop": crop,
            "probability": round(crop_probability * 100, 2)
        })

    # --------------------------------------------------
    # FEATURE IMPORTANCE
    # --------------------------------------------------

    preprocessor = model.named_steps["preprocessor"]
    random_forest = model.named_steps["model"]

    feature_names = preprocessor.get_feature_names_out()
    importances = random_forest.feature_importances_

    # Combine importance values by readable feature
    combined_importance = {}

    for feature, importance in zip(feature_names, importances):

        if "soil__" in feature:
            readable_name = "Soil Type"

        elif "numbers__temperature" in feature:
            readable_name = "Temperature"

        elif "numbers__humidity" in feature:
            readable_name = "Humidity"

        elif "numbers__rainfall" in feature:
            readable_name = "Rainfall"

        else:
            readable_name = feature

        combined_importance[readable_name] = (
            combined_importance.get(readable_name, 0)
            + importance
        )

    # Sort by importance
    sorted_importance = sorted(
        combined_importance.items(),
        key=lambda x: x[1],
        reverse=True
    )

    why_these_factors_matter = []

    for factor, importance in sorted_importance:

        why_these_factors_matter.append({
            "factor": factor,
            "importance": round(importance * 100, 2)
        })

    return {
        "recommendedCrop": prediction,

        "probability": round(probability, 2),

        "conditionsUsed": {
            "temperature": temperature,
            "humidity": humidity,
            "rainfall": rainfall,
            "soilType": soil_type
        },

        "whyTheseFactorsMatter": why_these_factors_matter,

        "topRecommendations": top_recommendations
    }