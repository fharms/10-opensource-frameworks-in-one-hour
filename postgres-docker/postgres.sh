#!/bin/bash

image_name="postgres-tenframeworks"
password="verysecret"
port=5432

key="$1"
container_id=$(docker ps -aqf "ancestor=$image_name")

case $key in
    build)
	    echo "Attempting to build image '$image_name'"
		cp ../springboot/src/main/resources/schema.sql ./init.sql
		docker build -t $image_name .
    	;;
    start)
		echo "Starting container"
		docker run -d -p $port:$port $image_name
    	;;
    client)
		docker run -it --rm --link $image_name:server postgres psql -h server -U postgres
		;;
	stop)
		echo "Attempting to stop containers running image '$image_name'"
		docker stop $container_id
		;;
	clear)
		$0 stop

		echo "Attempting to remove containers for image '$image_name'"
		docker rm $container_id
		;;
    clear-all)
            echo "Attempting to remove all stopped containers"
            docker rm $(docker ps -a -q) 2>&1 > /dev/null
            ;;
	*)
		echo "Unknown command '$key'..."
		;;
esac
