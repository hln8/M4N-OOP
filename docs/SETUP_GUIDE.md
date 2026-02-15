# Setup Guide

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0+

## Database Setup

1. Create a MySQL database named `m4n_db`.
2. Run the `database/schema.sql` script to create tables.
3. Run `database/seed-data.sql` to populate initial data.

## Server API

1. Navigate to `server-api`.
2. Run `mvn spring-boot:run`.

## Desktop App

1. Navigate to `desktop-app`.
2. Run `mvn javafx:run`.
