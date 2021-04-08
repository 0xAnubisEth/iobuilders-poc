# iobuilders-poc

The test is divided into two microservices: user-backend and wallet-backend. The user-backend microservice is
responsible for user registration and login. The waller-backend microservice is in charge of making a money deposit,
transferring money, withdrawing money and obtaining all account movements.

# Index ⚖️

1. [Requirements](#requirements-)
2. [Build](#build-)
3. [Endpoints](#endpoints-)
    1. [User backend](#user-backend-)
        1. [Create user](#create-user)
        2. [Login](#login)
    2. [Wallet backend](#wallet-backend-)
        1. [Deposit money](#deposit-money)
        2. [Withdraw money](#withdraw-money)
        3. [Transfer money](#transfer-money)
        4. [All money transactions](#all-money-transactions)

<a name="requirements"></a>

## Requirements 📦

* JDK 11
* Docker
* Maven

<a name="build"></a>

## Build ⚒

Run in terminal: `docker-compose up --build`

<a name="endpoints"></a>

## Endpoints 🚪

<a name="user-backend"></a>

### User backend 👩‍💻

The user-backend microservice is raised on port 8080 by default.

<a name="create-user"></a>

#### Create user

* Endpoint: `/user`
* Method: `POST`
* Body:

```
{
    "username": "javier.roman", // Required
    "password": "12345678", // Required
    "name": "Javier", // Required
    "lastname": "Román" // Required
}
```

* Status codes:
    * 201 - User successfully created
    * 400 - Missing or invalid parameter
    * 409 - The username already exists

<a name="login"></a>

#### Login

* Endpoint: `/login`
* Method: `POST`
* Body:

```
{
    "username": "javier.roman", // Required
    "password": "12345678" // Required
}
```

* Status codes:
    * 201 - User successfully logged in
    * 400 - Missing or invalid parameter
    * 403 - Not authorized

> The JWT token is in the authorization header.

<a name="wallet-backend"></a>

### Wallet backend 💸

The wallet-backend microservice is raised on port 8081 by default.

<a name="deposit-money"></a>

#### Deposit money

* Endpoint: `/deposit`
* Method: `POST`
* Body:

```
{
    "quantity": 25, // Required
    "concept": "Whatever" // Optional
}
```

* Status codes:
    * 201 - User successfully logged in
    * 400 - Missing or invalid parameter
    * 403 - Not authorized

> Authorization header is required

<a name="withdraw-money"></a>

#### Withdraw money

* Endpoint: `/withdraw`
* Method: `POST`
* Body:

```
{
    "quantity": 25, // Required
    "concept": "Whatever" // Optional
}
```

* Status codes:
    * 201 - User successfully logged in
    * 400 - Missing or invalid parameter
    * 403 - Not authorized
    * 409 - Not enough money in the account

> Authorization header is required

<a name="transfer-money"></a>

#### Transfer money

* Endpoint: `/transfer`
* Method: `POST`
* Body:

```
{
    "quantity": 25, // Required
    "concept": "Whatever", // Optional
    "destination": "1f070d0a-5213-4034-964c-eac82e0d7f2c" // Required
}
```

* Status codes:
    * 201 - User successfully logged in
    * 400 - Missing or invalid parameter
    * 403 - Not authorized
    * 404 - Destination account does not exist
    * 409 - Not enough money in the account

> Authorization header is required

<a name="all-money-transactions"></a>

#### All money transactions

* Endpoint: `/transactions/all`
* Method: `GET`
* Status codes:
    * 200 - User successfully logged in
    * 403 - Not authorized

> Authorization header is required