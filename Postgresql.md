# Установка и настройка PostgreSQL в ОС Ubuntu 22.04

## Устанавливаем

    sudo apt update
    sudo apt install postgresql -y
    sudo service postgresql start

## Меняем пароль пользователя postgres

    sudo su - postgres
    psql -c "alter user postgres with password 'password1'"

## Создаем БД и назначаем владельца

    psql 
    postgres# create database myblogdb; 
    postgres# create user mybloguser with encrypted password '123'; 
    postgres# grant all privileges on database myblogdb to mybloguser; 
    postgres# \q 
    exit

## Настраимваем подключение к БД с другого IP адреса

    sudo nano /etc/postgresql/12/main/postgresql.conf 

    Находим и меняем:
    listen_addresses = '192.168.0.4'

    
    sudo nano /etc/postgresql/12/main/pg_hba.conf 

    Находим и меняем:
    host all all 192.168.0.0/24 md5

    Перезапускаем PostgreSQL для применения настроек.
    sudo service postgresql restart