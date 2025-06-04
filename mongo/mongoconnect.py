from pymongo import MongoClient

client = MongoClient("mongodb+srv://ashoooberoi:MonteiroSonia1!@clusterashoo.vormn2f.mongodb.net/")

db = client['appdb']

collections = db.list_collection_names()

print("Collections:", collections)

