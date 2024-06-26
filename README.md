# Java Mini-Project
## Airline Reservation System
       
- Git clone the repo using command 
    ```shell
    git clone https://github.com/SHRIYASH-BAND/Airline-Reservation-System.git
    ```

- Entities 
    - Customer
    - Flight
    - Flight_Schedule
    - Airport
    - Airport_Address
    - Reservations

- Functionalities
    - Customer
        - Register
        - Login
        - View Flight Schedules 
        - View Booked Tickets
        - Book Flight Reservations
    - Airport
        - Register
        - Login
        - View Airport Flight Schedules
        - Schedule Flights


![ERD Diagram](./docs/Airline-Reservation-System.jpg)


- Presentation Link
    - [Click here to view presentation](https://docs.google.com/presentation/d/12clAlIiBWTtY32Bok4HfCwklBddiYouGhWTswgdCyg0/edit?usp=sharing)


- Steps to Run Eclipse Project.
1. Create a eclipse workspace and open it.
2. Click on `File` then click on `Import...`
3. Select `General` under which select `Existing Projects into Workspace`.
4. A prompt will appear for `import projects`.
5. Click on `Select root directory` and click on browse, then click on the `Airline-Reservation-System` folder.
6. Click on Finish.
7. Right click on the project and select `Run as..`, select `java applicaion`.


- Download the mysql connector jar.
    - [Click here to download mysql connector jar](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.1.0)

- Steps to add mysql jar connector to your project.
1. Right click on project and click on `build path`.
2. under build path choose `Add external jar archieves`.
3. choose the `mysql-connector-j-8.1.0.jar`

- Configurations
    - Update the `DBUtil.java` file 
        - Update DB_USER = `your mysql username`
        - Update DB_PASSWD = `your mysql password`
    - Add the dummy data from `MYSQL_Commands` folder
        - Enter below command 
            ```sql
            source `<Current_Directory>/MYSQL_Commands/commands.sql`
            ```
