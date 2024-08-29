# Getting Started

## Executar mysql via docker:

docker pull mysql/mysql-server

docker run -d --name mysql \
-e MYSQL_ROOT_PASSWORD=root_password \
-e MYSQL_USER=user \
-e MYSQL_PASSWORD=123456 \
-e MYSQL_DATABASE=todolist \
-p 3306:3306 \
mysql/mysql-server

docker ps
Verificar se esta executando

