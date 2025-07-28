
# Tombola

## Description
The project represents a REST API which creates and manages a Tombola game.
It consists of Players which we can create, get and asign to a tombola, Awards which we
can create and assign to Tombola, and the Tombola Game itself - which we can create, play it out and give awards to players that got lucky.

## Project setup
To run the program you need to do the follow steps :

    1. Clone the repo
    2. Since it is a containerazed app you will need a docker desktop
    3. (Optional) If you want to follow your DB you can download MYSQL Workbench

    After downloading the app you will need to build it ;

    mvn clean package - for the build
    docker-compose up --build -d - for the docker start/build
    docker-compose logs -f app - you can follow your logs in the console
    docker-compose down - if you want to shut it down
    docker compose down --volumes --rmi all - if you want to remove containers and volumes


## Database
You dont need to run any scripts manually since liquibase is used.
On the first launch of the app the scripts will automatically be run and the DB will be created.

If you want to connect to the DB within Workbench for better visibility-

Port -3306

Username - myuser

Password - mypass

## Testing the app
You can test the app by calling the endpoints.
This can be done by opening your swagger url : http://localhost:8080/swagger-ui/index.html.
To test any of the endpoints you will need an oauth2 token. The credentials for it are as follows 

clientID - admin 

clientSecret - nimda