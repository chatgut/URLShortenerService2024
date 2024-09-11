# URL Shortener Service 2024
This project implements a microservice for shortening URLs. 
Users can input a long URL and receive a shortened URL in return. The service also handles redirection from the shortened URL back to the original long URL.

<h2>How to Run the Project</h2>
Clone the repository: https://github.com/chatgut/URLShortenerService2024.git

<h2>Build and run the services using Docker Compose</h2>
docker-compose up --build

This will start two services:

1. url_shortener: Runs the URL shortener service on port 8080.
2. db: A MySQL database used by the URL shortener.

<h2> Testing the Service </h2>
1. Send a POST request to shorten a URL:

POST http://localhost:8080/shorten 

Content-Type: application/json
````
{
  "longUrl": "https://www.google.com"
}
````

2. Response should look like this:

````
{
  "longUrl": "https://www.google.com",
  "shortUrl": "abc123",
  "message": "URL successfully shortened"
}
````
3. Use GET request to access the shortened URL to get redirected:

````
http://localhost:8080/{shortUrl}
````

For example, if your short URL is abc123, visiting http://localhost:8080/abc123 will redirect you to the original https://www.google.com.

