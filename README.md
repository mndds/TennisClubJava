Update project:
1) run:
   mvn clean package
   
3) update file: docker-compose.yml(app section)
app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      ....


  
