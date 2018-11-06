FROM postgres:11.0

EXPOSE 5432

COPY init.sql /docker-entrypoint-initdb.d/init.sql
COPY init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh
