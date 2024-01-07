# Warehouse Search System Documentation

## Application Information

- **Name:** Warehouse Search System
- **Version:** 1.1
- **Creation Date:** 2024-01-04
- **Developer:** TolKing (To'lqin Oltiboyev)
- **Email:** shaxzodsdf@gmail.com

## Setup Instructions

Before running the Warehouse Search System, follow these setup instructions:

1. Change the database configuration in the `db.properties` file located in the project folder:
    - `host='your_host'`
    - `dbName='your_database_name'`

2. Run the SQL code in the `db.sql` file in the project folder to create a guest account and necessary tables.

3. Change your own data in `src/test/resources/admin.properties`:
    - `login='your_login'` by default `postgres`
    - `password='your_password'` by default your own password before installation postgres

## Running the Project

Run the project by executing the `WareHouseApp.java` file located in `src/main/java/uz/tolKing/warehouse/`.

## Authorization

You need to authorize yourself with either of the following roles:

- **Admin:** Can create new categories, delete items, add items, and add new admins.
- **Guest:** Can only read data from the database.

**Guest Credentials:**
- **Login:** guest
- **Password:** guest

**Admin Credentials (PostgreSQL):**
- **Login:** your_postgresql_login
- **Password:** your_postgresql_password



## User Choices

After authorization, you have the following choices:
- `-2`: Add a new category.
- `-1`: Display the admin list.
- `0`: Close the application.
- Positive integers: Access tables corresponding to the selected positive integer.

## Category Management

To add a new category:
1. Enter the name of the category (e.g., 'pillow').
2. Enter column names and types (e.g., 'name varchar(100)'). Leave empty to confirm all columns or enter 0 to exit.

**Note:** The 'id' with a primary key is auto-generated; no need to create it explicitly.

## Admin List

1. Add an admin with the command: `add <login> <password>`.
2. Enter 0 to return to the main menu.

## Tables Access

For positive integers shown in the menu, you can access data from tables.

1. Add a new item with the command: `add <id> <name> ...`.
2. Delete an item with the id: `delete <id>`.
3. Search for an item with: `search <column name> <data>`.
4. Sort items with: `sort <column name> <asc or desc>`.
5. Get items in a range using: `range <column name> <min> <max>`.

**Note:** Add and delete functions are available only for admins.

## Project Structure - 3 Layers

The project follows a 3-layer structure:
1. **DAO (Data Access Object):** Responsible for interacting with the database.
2. **Service:** Contains business logic and communicates between DAO and Controller.
3. **Controller:** Manages user input and communicates with the Service layer.

## Project Overview

- **Entities:** No entities are used since JDBC is directly employed for data retrieval.
- **Low Coupling:** Layers are connected sequentially and are not directly connected to another layer, ensuring low coupling.
- **Project Challenges:** The use of JDBC required the entire project to be redone, and some aspects, like the static method `getConnection` in the connection service, might need improvement.
- **Dynamic Behavior:** The project adapts to changes in database tables and roles.

**Note:** SQL codes in methods might have some safety concerns as prepared statements were not used due to limitations in defining table names and some data dynamically. Future improvements could include enhancing security measures in this regard.

## Testing

You can test the project in the `test` folder.
