<pre>
build the image:
docker build -t account-manager:1.0 .

run the container:
docker run -d -p 8080:8080 account-manager:1.0

in the browser open: http://localhost:8080


HTTP method			URI				Description			Valid HTTP status codes
POST			/api/accounts				Create an account			201
GET			/api/accounts/{id}			Retrieve an account			200
PUT			/api/accounts/{id}			Update an account			200
GET			/api/accounts				Retrieve all accounts 			200, 204
GET			/api/accounts/{id}/events		Retrieve all events			200, 204
POST			/api/accounts/{id}/events 		Create an event				201
GET			/api/accounts/{id}/statistics		Retrieve an account Statistics		200, 204
GET			/api/accounts/statistics		Retrieve Statistics			200, 204


Swagger:
http://localhost:8080/swagger-ui.html

Actuator:
http://localhost:8080/actuator

H2 Database:
http://localhost:8080/h2-console/

</pre>
