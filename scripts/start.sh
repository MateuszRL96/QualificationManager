#!/bin/bash

# Load environment variables from .env file
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
else
  echo ".env file not found"
  exit 1
fi

# Skrypt do uruchamiania Docker Compose z opcją --build

# Przejdź do katalogu projektu
cd $PROJECT_PATH

# Uruchom Docker Compose z opcją --build
docker compose up --build