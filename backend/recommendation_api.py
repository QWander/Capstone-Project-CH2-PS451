#!/Users/User/AppData/Local/Programs/Python/Python39/python.exe

import pandas as pd
from flask import Flask, request, jsonify
import numpy as np
from tensorflow.keras.models import load_model
from sklearn.preprocessing import LabelEncoder

app = Flask(__name__)


# Load final_df
final_df = pd.read_csv("final_df.csv")  # Sesuaikan dengan nama file Anda

# Load model Rekomendasi
recommendation_model = load_model("recommendation_model.h5")


@app.route("/predict", methods=["POST"])
def predict():
    try:
        # Terima input pengguna sebagai JSON
        print("=========MASUK 1==============")
        user_input = request.get_json()
        print(user_input)

        # Buat dictionary input pengguna yang sesuai dengan format data latihan
        print("=========MASUK 2==============")
        input_user = {
            "Gender": np.array([user_input["Gender"]]),
            "Age": np.array([user_input["Age"]]),
            "City": np.array([user_input["City"]]),
            "Category": np.array([user_input["Category"]]),
            "Price_Category": np.array([user_input["Price_Category"]]),
        }
        print("=========MASUK 3==============")

        # Lakukan prediksi menggunakan model
        predicted_place_index = recommendation_model.predict(
            [
                input_user["Gender"],
                input_user["Age"],
                input_user["City"],
                input_user["Category"],
                input_user["Price_Category"],
            ]
        )
        print("=========MASUK 4==============")

        # ... (sisa kode untuk mengonversi prediksi menjadi hasil yang sesuai)

        # Mengurutkan indeks berdasarkan nilai probabilitasnya
        sorted_indices = np.argsort(predicted_place_index[0])[::-1]
        print(sorted_indices)

        print("=========MASUK 5==============")

        # Mengambil tiga indeks pertama
        top3_indices = sorted_indices[:3]
        print(top3_indices)
        print("=========MASUK 6==============")
        # Menggunakan inverse_transform untuk mendapatkan nama tempat
        label_encoder_place = LabelEncoder()
        label_encoder_place.fit(final_df["Place_Name_x"])
        top3_place_names = label_encoder_place.inverse_transform(top3_indices)
        result = {
            "top3_recommendations": list(top3_place_names),
        }
        print("=========MASUK 7==============")
        print(result)

        return jsonify(result)

    except Exception as e:
        return jsonify({"error manTAPJIWA": str(e)})


if __name__ == "__main__":
    app.run(debug=True)
