# Weather sensor REST API intro
This is the introductory documentation for my REST API service that allows you to upload, store and search weather data from weather sensors.

An application was written using Spring Boot, everything was validated using the Hibernate Validator, the database was PostgrSQL. I generated the full documentation using Swagger, for your convenience you can find the generated page in the root of the project. The whole project is also loaded into the Docker container, I used FlyWay to generate tables, you can also find all the SQL scripts, Dockerfile and docker-compose files in the root of the project. Also for your convenience, a client for this API was written, the link to it is below! Everything works correctly, both through Spring Boot and through the Docker container.

Now about the project itself, it has 2 main controllers - SensorController and MeasurementController.
First about the SensorController.

## Sensor Controller

### Register new sensor

`POST /sensor/register`

it request JSON data:

```
{
"name":"UniqueSensorName",
"location":"Location"
}
```
Example response:
![As you can see, everything is fine)](https://od.lk/s/NDZfMzMyNzk2ODJf/Register%20Success.png)

Below you can see the response if you try to send a request with an already existing name or with empty fields:

An already existing controller name    |   Empty fields or only spaces
:-------------------------:|:-------------------------:
![User-friendly exceptions)](https://od.lk/s/NDZfMzMyNzk4NDlf/NotUnique.png)|![User-friendly exceptions)](https://od.lk/s/NDZfMzMyNzk4NTBf/Exceptions.png)

I tried to validate all possible inputs, there are exceptions for all possible situations.

### Full list of sensors
`GET /sensor`

#### Response:
![](https://od.lk/s/NDZfMzMyNzk4NTFf/All.png)


### Find sensor data by name
`GET /sensor/findByName?name=name`

#### Response:
![](https://od.lk/s/NDZfMzMyNzk4NTJf/find%20by%20name.png)

An exception may be thrown here that the sensor was not found.
#### Example:
![](https://od.lk/s/NDZfMzMyNzk4NTNf/Not%20found.png)

### Сhange sensor location

`Patch /sensor/{name}/updatelocation`

#### Example:
![](https://od.lk/s/NDZfMzMyNzk4NTdf/location.png)

It also throws an exception if you enter a non-existent name or an empty location value.
I won't pollute this documentation with this, you can check it yourself)

### Delete sensor

`Delete /sensor/{name}/delete`

here is an example, if you input a non-existent sensor name
![](https://od.lk/s/NDZfMzMyNzk4ODJf/deleteNotFound.png)

### Find sensor data by id
The last method in this controller is to find by id, I think it is applicable only for administrators who have access to the database, because the user does not get the sensor id
`GET /sensor/{id}`


## Measurement Controller

### Add new measurement

`POST /measurement/add`

it request JSON data:

#### Example:
```
{
"temperatureValue":11.851231,
"raining":false,
"windSpeed":2.5,
"timeOfMeasurement":"2020-07-19T15:32:01",
"sensor":{
"name":"Sensor 10"
}
}
```
Each measurement has a location, it is not specified in the request, because it is assigned in the service according to the location of the sensor.

The temperature, when added to the database, will be rounded to 2 digits after the dot. It can be greater than -100 and less than 100, otherwise an error will be thrown. The wind speed can only be positive, but less than 140 meters per second.
The measurement date must be entered in the format yyyy-MM-dd'T'HH:mm:ss.
Also, the Sensor to which the measurement is attached must exist.

I tried to make every exception as clear as possible for the user.

#### Example:

![](https://od.lk/s/NDZfMzMyODA1MDhf/Example.png)

#### Invalid input example:

![User-friendly exceptions](https://od.lk/s/NDZfMzMyODA1MDlf/IncorrectDataExample.png)

### Find measurements by sensor name

`GET /measurement/getDataForSensorsName?sensorName=name`

#### Example:
![](https://od.lk/s/NDZfMzMyODA1MTFf/findByName.png)

An exception may also be thrown here that the sensor was not found.

### Find measurements by location

`GET /measurement/getDataForLocation?location=location`

#### Example:
![](https://od.lk/s/NDZfMzMyODA1MTNf/location.png)



#### This location does not have a measurements or was not found
![User-friendly exceptions](https://od.lk/s/NDZfMzMyODA1MTZf/locationNotFound.png)


### Get rainy days count by location

`GET /measurement/getRainyDaysCountForLocation?location=location`

#### Example:
![](https://od.lk/s/NDZfMzMyODA2MjVf/rainyDays.png)

An exception may also be thrown here that the location was not found.


### Get rainy days count by sensor

`GET /measurement/getRainyDaysCountForSensorsName?name=name`

#### Example:
![](https://od.lk/s/NDZfMzMyODA3NDJf/rainyDaysBySensor.png)

An exception may also be thrown here that the sensor was not found.


### Find measurements by location and date

`GET /measurement/getDataForLocationAndDate?name=name`

If you need to find measurements in a specific location and on specific dates, there is a method for this.

The date is also needed in the format yyyy-MM-dd'T'HH:mm:ss.
All data will be sorted in descending order.

#### Example:
![](https://od.lk/s/NDZfMzMyODA3NjRf/locationAndDateSuccess.png)

#### Location not found Example:

![](https://od.lk/s/NDZfMzMyODA3NjBf/locationAndDateIvalidDateNotFound.png)

#### Invalid date type:
![](https://od.lk/s/NDZfMzMyODA3NjFf/locationAndDateIvalidDataType.png)

## Docker
For convenience, I also uploaded this application to the Docker container, below are some examples

### Register new sensor
![](https://od.lk/s/NDZfMzMyODA3Njdf/Register.png)

### Add some measurements

![](https://od.lk/s/NDZfMzMyODA3NjVf/add%20measurement.png)

### Find them by location

![](https://od.lk/s/NDZfMzMyODA3NjZf/findByLocation.png)

#### This is the end of the documentation, thanks for your attention, for all other questions - write to me, I will be happy to answer
