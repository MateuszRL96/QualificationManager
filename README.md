How the QualificationManager Application Works
### Overview
QualificationManager is a web application designed to manage and recommend qualifications. It uses various technologies and frameworks to provide a robust and scalable solution.  
### Technologies Used
- Java 17: The primary programming language.
- Spring Boot: Framework for building the backend.
- PostgreSQL: Database management system.
- Docker: Containerization platform.
- DeepLearning4J (DL4J): Library for deep learning to provide job qualification recommendations.

### Application Structure

- Backend: Built with Spring Boot, it handles the business logic, data processing, and interactions with the database.
- Database: PostgreSQL is used to store user data, job qualifications, and other relevant information.
- Containerization: Docker and Docker Compose are used to containerize the application, making it easy to deploy and manage.

### Key Features

- User Authentication: Users can log in using GitHub and Google OAuth2.
- Database Management: PostgreSQL is used for data storage, with Flyway handling database migrations.
- Server-Side Rendering: Thymeleaf is used for rendering HTML templates on the server side.
- Job Qualification Recommendations: DL4J is used to provide recommendations based on historical data.

### Deep Learning for Job Qualification Recommendations

- The application uses DL4J to implement a recommendation system for job qualifications. Here’s how it works:  
- Data Preprocessing: Historical job qualification data is cleaned and prepared for training.
- Model Configuration: A neural network architecture is defined using DL4J.
- Training: The model is trained using the preprocessed data.
- Evaluation: The model's performance is evaluated on a validation dataset.
- Integration: The trained model is integrated into the application to provide real-time recommendations.

### Running the Application

### Clone the repository
```sh
git clone https://github.com/MateuszRL96/QualificationManager.git
cd QualificationManager
```
Create a .env file in the scripts directory with the following content:
```sh
PROJECT_PATH=/home/mateusz/Documents/QualificationManager
```

Build and Run with Docker Composer
```sh
docker-compose up --build
``` 
or 
```sh
./scripts/start.sh
```

Running Tests
To run tests, use the following command:
```sh   
./mvnw test
``` 

Add cretentials to application properties
```sh   
# Github Login
spring.security.oauth2.client.registration.github.client-id= {{client-id}}
spring.security.oauth2.client.registration.github.client-secret= {{client-secret}}
# Google Login
spring.security.oauth2.client.registration.google.client-id= {{client-id}}
spring.security.oauth2.client.registration.google.client-secret= {{client-secret}}
```
