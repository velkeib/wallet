wallet backend spring boot REST and MySQL database by Bence Velkei

run docker MySQL db:

docker run --network wallet-network --name wallet_db -e MYSQL_ROOT_PASSWORD=admin -d mysql:latest

Run to open MySQL console
$ docker run -it --network wallet-network --rm mysql mysql -hwallet_db -p



docker build -t velkeib/wallet .

Run spring boot
docker container run -p 8080:8080 --net wallet-network --name wallet velkeib/wallet
