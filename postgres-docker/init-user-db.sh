#!/bin/bash
set -e

USER="presentation"
PASSWORD="password"
DATABASE="tenframeworks"

# Create user and database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER $USER;
    ALTER USER $USER WITH PASSWORD '$PASSWORD';

    CREATE DATABASE $DATABASE;
    
    GRANT CONNECT ON DATABASE $DATABASE TO $USER;
    GRANT USAGE ON SCHEMA public TO $USER;
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO $USER;
    GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO $USER;

    SELECT pg_reload_conf();
EOSQL

# Load initial schema for database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$DATABASE" < /docker-entrypoint-initdb.d/init.sql
