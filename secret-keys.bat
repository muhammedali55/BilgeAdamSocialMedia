@echo off
setx auth_service_port "8091"
setx auth_service_spring_rabbit_host "34.118.80.59"
setx auth_service_spring_rabbit_port "5672"
setx auth_service_spring_rabbit_username "BilgeAdamAdmin"
setx auth_service_spring_rabbit_password "BilgeAdam!**"
setx auth_service_datasource_url "jdbc:postgresql://34.118.80.59:5432/AuthDb"
setx auth_service_datasource_username "postgres"
setx auth_service_datasource_password "BilgeAdam!**"
setx auth_service_zipkin_baseurl "http://34.118.80.59:9411"
setx auth_service_myapplication_userservice "localhost:8090"

setx elastic_service_server_port "7777"
setx elastic_service_spring_rabbit_host "34.118.80.59"
setx elastic_service_spring_elastic_uri "34.118.80.59:9200"
setx elastic_service_spring_rabbit_port "5672"
setx elastic_service_spring_rabbit_username "BilgeAdamAdmin"
setx elastic_service_spring_rabbit_password "BilgeAdam!**"

setx user_service_port "8090"
setx user_service_spring_rabbit_host "34.118.80.59"
setx user_service_spring_rabbit_port "5672"
setx user_service_spring_rabbit_username "BilgeAdamAdmin"
setx user_service_spring_rabbit_password "BilgeAdam!**"
setx user_service_data_mongodb_database "socialmedia"
setx user_service_data_mongodb_username "bilgeadamuser"
setx user_service_data_mongodb_password "!b1lgeadam**"
setx user_service_data_mongodb_host "34.118.80.59"
setx user_service_data_mongodb_port "27017"
setx user_service_zipkin_baseurl "http://34.118.80.59:9411/"

setx web_service_server_port "80"
setx web_service_zipkin_baseurl "http://34.118.80.59:9411/"

setx api_gateway_service_server_port "3333"
setx api_gateway_service_zipkin_baseurl "http://34.118.80.59:9411/"

setx config_service_zipkin_baseurl "http://34.118.80.59:9411/"

setx config_security_username "administrator"
setx config_security_password "Bilge123!!"

setx token_security_key_sign "A7dfdtnSmjKDpQxwjtAfljaYkNPJFZ9bIJAG08pq4c77kXPvvE"