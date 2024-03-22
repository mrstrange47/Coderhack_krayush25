[Coderhack.postman_collection.json](https://github.com/mrstrange47/Coderhack_krayush25/files/14720156/Coderhack.postman_collection.json)**Problem Statement**

Develop a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.


**Endpoints**

**GET** /users - Retrieve a list of all registered users

**GET** /users/{userId} - Retrieve the details of a specific user

**POST** /users - Register a new user to the contest

**PUT** /users/{userId} - Update the score of a specific user

**DELETE** /users/{userId} - Deregister a specific user from the contest


Please find Postman collections for all endpoints below -

[Uploading Coderhack.postman_collecti{
	"info": {
		"_postman_id": "e4f10f23-6a2e-46d0-bef5-743f7fcffcaa",
		"name": "Coderhack",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "13799232"
	},
	"item": [
		{
			"name": "Find All Users",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/users",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"users"
					]
				}
			},
			"response": []
		},
		{
			"name": "Find User by Id",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/users/69859632",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"users",
						"69859632"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update User Score",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"score\":45\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/users/28425fd4",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"users",
						"28425fd4"
					]
				}
			},
			"response": []
		},
		{
			"name": "Create User",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"username\": \"abcd\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/users",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"users"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete User By Id",
			"request": {
				"method": "DELETE",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"username\": \"piyush\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/users/0a5abf",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"users",
						"0a5abf"
					]
				}
			},
			"response": []
		}
	]
}on.json…]()
