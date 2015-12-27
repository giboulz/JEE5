# JEE5
livre sur java EE 5, blueprint sur petstore de sun.

petstore : Nom du domaine GlassFish utilisé par l’application

http://localhost:8080 : URL de l’application
htpp://localhost:4848 : URL de la console d’administration
admin/ : Login et mot de passe pour accéder à la console d’administration GlassFish

master/masterpwd
Login et mot de passe du super utilisateur GlassFish

petstoreDB
Nom de la base Derby où seront stockées les données de l’application

dbuser/dbpwd
Login et mot de passe de la base de données

petstorePool
Nom du pool de connexions à la base

petstoreDS
Nom de la source de données pour accéder à la base

jms/petstoreConnectionFactory
Fabrique JMS

jms/topic/order
File d’attente JMS pour la gestion des bons de commande


pool name : DerbyPool



pour lancer la base de données : 
cd C:\UtilsJava\glassfish-4.1.1\glassfish4\bin
asadmin
start-database

