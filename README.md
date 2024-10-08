
---

# DemoProject

## Overview

This Spring Boot project provides a backend API for user management, including registration, editing, reading, and (soft) deleting users. A welcome email is sent upon registration. The project is Dockerized, and a shell script is provided for building, packaging, and running the application.

## How to Run

Make sure you have maven, java, docker and docker-compose environment

1. **Clone the repository**:
   ```bash
   git clone git@github.com:Ccbillow/DemoProject.git
   cd DemoProject
   ```

2. **Run project**:
   ```bash
   sh deploy.sh
   ```
3. **Access the API**:
    - Swagger UI: `http://localhost:8081/swagger-ui.html`

4. **Access the Eureka**:
   - Eureka UI: `http://localhost:8761`
   - username: root
   - password: root

## Features

- User CRUD operations with validation and error handling.
- Welcome email on registration using a fake SMTP server. (Since a fake SMTP server is used, recipients won't actually receive the emails. the attachment `EmailSendingDetail.jpg` will show the email sending detail. To receive real emails, replace the email settings in `./EmailService/src/main/resources/application.yml` with the value of `googleEmailConfig.txt` in the attachment.)
- Dockerized for easy deployment.
- REST API documentation with Swagger.
- Using Spring Cloud, split UserService and EmailService into independent microservices, with Eureka as the service registry.
- Using docker-compose to manage container, allowing multiple microservices to be deployed with a single command.

## Testing

Run tests with:
```bash
mvn test
```

---