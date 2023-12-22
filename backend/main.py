from flask import Flask, request, jsonify
from keras.utils import get_file
import tensorflow as tf
from PIL import Image
import numpy as np
import pymysql
import requests
import googlemaps

app = Flask(__name__)

# Load the TFLite model
# model_path = 'model3.tflite'
# interpreter = tf.lite.Interpreter(model_path=model_path)
# interpreter.allocate_tensors()

# # Get input and output details
# input_details = interpreter.get_input_details()
# output_details = interpreter.get_output_details()

# get connection from cloud sql

def create_conn():
    conn = pymysql.connect(
        host='34.124.128.184',
        user='root',
        password='123123123',
        db='bangkit_qwander',
        cursorclass=pymysql.cursors.DictCursor
    )
    return conn

@app.route('/api')
def test():
    conn = create_conn()
    with conn.cursor() as cursor:
        cursor.execute("SELECT * FROM destinations ORDER BY ID")
        rows = cursor.fetchall()
    return jsonify(rows)

def get_destinations():
    conn = create_conn()
    with conn.cursor() as cursor:
        cursor.execute("SELECT ID, NAME, DESCRIPTION, CATEGORY, PRICE, RATING, DESCRIPTION, LATS, LONGS FROM destinations")
        destinations = cursor.fetchall()
    return destinations

# make function get customer metadata to classify the customer type which param is age and gender
# the return is  "family","new-family", "couple", "solo"
# family means they have money range from 1250000 to 50000000
# new-family means they have money range from 750000 to 1250000
# couple means they have money range from 500000 to 750000
# solo means they have money range from 0 to 500000
def get_cust_metadata(age, gender):
    metadata = {"type": "unknown", "low": 0, "high": 0}
    if(gender == "L"):
        # if  the family is gender L age 35 to 99 or P age 30 to 99
        if(age<=35 and age>=99):
            metadata = {"type":"family","low":1250000,"high":50000000}
        # if  the new-family is gender L age 25 to 34 or P age 20 to 29
        if(age<=25 and age>=34):
            metadata = {"type":"new-family","low":750000,"high":1250000}
        # if  the couple is gender L age 20 to 24 or P age 15 to 19
        if(age<=20 and age>=24):
            metadata = {"type":"couple","low":500000,"high":750000}
    if(gender == "P"):
        # if  the family is gender L age 35 to 99 or P age 30 to 99
        if(age<=30 and age>=99):
            metadata = {"type":"family","low":1250000,"high":50000000}
        # if  the new-family is gender L age 25 to 34 or P age 20 to 29
        if(age<=20 and age>=29):
            metadata = {"type":"new-family","low":750000,"high":1250000}
        # if  the couple is gender L age 20 to 24 or P age 15 to 19
        if(age<=18 and age>=19):
            metadata = {"type":"couple","low":500000,"high":750000}
    # if  the solo is age 15 to 20 for both gender
    if(age<=20):
        metadata = {"type":"solo","low":0,"high":500000}
    return metadata
    

@app.route('/predict-if', methods=['GET'])
def predict():
    # Get the origin coordinate from the request parameters
    orig_lat = float(request.args.get('lat', -7.3105008))
    orig_lon = float(request.args.get('lon', 112.7260853))
    orig_age = int(request.args.get('age', 20))
    orig_gender = request.args.get('gen', "L")
    # Initialize the Google Maps client
    gmaps = googlemaps.Client(key='AIzaSyAn9s1-VWROcVj2mxFDub5wpcfbRxpIl1s')

    # Connect to the database
    destinations = get_destinations()

    closest_destination = None
    shortest_distance = None
    # define category array to be Budaya Cagar Alam Pusat Perbelanjaan Taman Hiburan Tempat Ibadah
    category = ["Budaya","Cagar Alam","Pusat Perbelanjaan","Taman Hiburan","Tempat Ibadah"] 
    cust_category = []
    cust_metadata = get_cust_metadata(orig_age,orig_gender)
    # for budaya, it gonna be suggested for new-family and couple
    if(cust_metadata["type"]=="new-family" or cust_metadata["type"]=="couple"):
        cust_category.append(category[0])
    # for cagar alam, it gonna be suggested for family and new-family and couple
    if(cust_metadata["type"]=="family" or cust_metadata["type"]=="new-family" or cust_metadata["type"]=="couple"):
        cust_category.append(category[1])
    # for pusat perbelanjaan, it gonna be suggested for new-family and couple and solo
    if(cust_metadata["type"]=="new-family" or cust_metadata["type"]=="couple" or cust_metadata["type"]=="solo"):
        cust_category.append(category[2])
    # for taman hiburan, it gonna be suggested for family and new-family and couple and solo
    if(cust_metadata["type"]=="family" or cust_metadata["type"]=="new-family" or cust_metadata["type"]=="couple" or cust_metadata["type"]=="solo"):
        cust_category.append(category[3])
    # for tempat ibadah, it gonna be suggested for family and new-family 
    if(cust_metadata["type"]=="family" or cust_metadata["type"]=="new-family"):
        cust_category.append(category[4])
    
    
    destinations.pop(0)
     # Initialize a list to store the distances and destinations
    distances_and_destinations = []    

    for destination in destinations:
        dest_lat = destination['LATS']
        dest_lon = destination['LONGS']

        # Calculate the distance from your coordinate using the Distance Matrix API
        distance_result = gmaps.distance_matrix(origins=(orig_lat, orig_lon),
                                                destinations=(dest_lat, dest_lon),
                                                mode='driving')

        # Get the distance in meters
        distance = 9999
        if distance_result['rows'][0]['elements'][0].get('distance'):
            distance = distance_result['rows'][0]['elements'][0]['distance']['value']

        # Add the distance and destination to the list
        distances_and_destinations.append((distance, destination))

    # Sort the list by distance and cust_category
    distances_and_destinations.sort(key=lambda x: (x[0], x[1]['CATEGORY'] in cust_category))

    # Get the 5 closest destinations
    closest_destinations = distances_and_destinations[:5]

    # Return the 5 closest destinations
    return jsonify([destination for distance, destination in closest_destinations])

# @app.route('/predict', methods=['POST'])
# def predict():
#     file = request.files['file']
#     img = Image.open(file).convert('RGB')
#     img = img.resize((224, 224))
#     img_array = np.array(img, dtype=np.float32)
#     img_array = (img_array / 255.0).astype(np.float32)
#     img_array = np.expand_dims(img_array, axis=0)

#     # Set the input tensor
#     input_shape = input_details[0]['shape']
#     interpreter.set_tensor(input_details[0]['index'], img_array)

#     # Run the inference
#     interpreter.invoke()

#     # Get the output tensor
#     output = interpreter.get_tensor(output_details[0]['index'])

#     # Process the output probabilities
#     classes = np.squeeze(output)
#     max_prob = np.max(classes)
#     max_index = np.argmax(classes)

#     labels = [
#         'Fresh Apples', 'Fresh Bananas', 'Fresh Bellpepper', 'Fresh Cucumber', 'Fresh Mango', 'Fresh Meat',
#         'Fresh Orange', 'Fresh Potato', 'Fresh Strawberry', 'Fresh Tomato', 'Rotten Apples', 'Rotten Bananas',
#         'Rotten Bellpepper', 'Rotten Cucumber', 'Rotten Mango', 'Rotten Meat', 'Rotten Orange', 'Rotten Potato',
#         'Rotten Strawberry', 'Rotten Tomato'
#     ]

#     predict_label = labels[max_index]
#     confidence = round(float(max_prob) * 100, 2)
#     result = {
#         'label': predict_label,
#         'confidence': confidence
#     }
#     return jsonify(result)

if __name__ == '__main__':
    app.run()