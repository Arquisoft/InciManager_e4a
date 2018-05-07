
[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e4a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e4a)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f2f0d0b009384c8aba7deacb39b7b541)](https://www.codacy.com/app/Llambi/InciManager_e4a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e4a&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e4a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e4a)


# InciManager_e4a
InciManager e4a


# Authors

- Pablo Fernández Pérez (UO245015)
- Gonzalo Collada Vázquez (UO252066)
- Carlos Concheso Cubillas (UO237674)
- Paloma Sierra Bonet (UO232919)
- Pelayo Garcia Menendez (UO251765)

Pasos para ejecutar:

1-Descargar Apache Kafka de su página oficial.

  ->https://www.apache.org/dyn/closer.cgi?path=/kafka/1.0.1/kafka_2.11-1.0.1.tgz

2- Ejecutar Apache Zookeeper.

  ->bin\windows\zookeeper-server-start.bat config\zookeeper.properties

3- Ejecutar Apache Kafka.

  ->bin\windows\kafka-server-start.bat config\server.properties

4-Ejecutar eclipse desde la clase Application

5-Conectarse al localhost:8880

6-Introducir datos para loguearte

7-Consultar tus incidencias o crear una nueva

8-Si seleccionas crear una nueva, rellenas los datos y lo envias


# Rest

{ 
	"login": "13864928P", 
	"password": "123456", 
	"kind": "Person", 
	"name": "nombreIncidencia2", 
	"description": "descripcionIncidencia", 
	"location": ["11.111111","-2.222222"], 
	"labels": "label1,label2,label3", 
	"moreInfo": ["adInfo1","adInfo2","adInfo3"], 
	"properties" : { 
		"key1":"value1", 
		"key2":"value2" 
	} 
}
