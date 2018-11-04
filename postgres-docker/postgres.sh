#!/bin/bash

image="postgres"
container_name="postgres"
password="verysecret"

key="$1"

case $key in
    install)
	echo "Attempting to retrieve image '$container_name'"
	docker pull $image
	
	echo "Attempting to remove image '$container_name', if already exists"
	docker rm $container_name
	
	echo "Creating PostgreSQL image with name: '$container_name'"
	docker create --name $container_name -e POSTGRES_PASSWORD=$password $image
    	;;
    start)
	docker start $container_name -d
    	;;
    client)
	docker run -it --rm --link $container_name:server $image psql -h server -U postgres
	;;
    stop)
	docker stop $container_name
    	;;
esac
