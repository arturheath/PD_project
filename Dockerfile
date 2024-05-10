version: '3.8'

services:
  postgres:
    image: postgres:14
    container_name: postgres14
    environment:
      POSTGRES_DB: pdmovies
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_HOST_AUTH_METHOD: trust
      PGDATA: /data/pgdata
    volumes:
      - postgres_data:/data/pgdata
    ports:
      - "5432:5432"

  backend:
    build:
      context: ../backend/pdmovies
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    depends_on:
      - postgres

  frontend:
    build:
      context: ../frontend/pd-movies-presentation
      dockerfile: Dockerfile
    ports:
      - "5000:5000"
    depends_on:
      - backend

volumes:
  postgres_data:
