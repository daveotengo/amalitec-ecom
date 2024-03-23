# Amalitec Ecommerce Application: 
# Name of Author : David Oteng

This Standalone Spring boot project demonstrates the crud operations on Ecom services using GraphQL as API.


## 1. In Memory H2 Database is used.
    So you can modify application.yml as per your database. 

## 2. Running as a Packaged Application (Following ways)
    NB: 
    For some reasons when i used the AOP approach instead of rest for the login and register endpoints when i 
    run the application on docker the login and register endpoints throw a 403 error.
    So i will advice you use the Way-1 otherwise if you use the Way-2 you will have to pick the token
    from the logs to make the other endpoints work.
    Since the Project requirement never mention that we are detailed about the authentication.
    When i have time i will look at how to fix the AOP login and register endpoints.

    Way-1 : 
        Run: "mvn clean install -DskipTests=true" to compile
        Run: "java -jar target/amalitec-ecom-0.0.1-SNAPSHOT.jar"
    Way-2 : 
        Run "docker compose up -d" to build and start 
        Run "docker compose start" to start after building 

## 3. Once the application is started you can access it on http://localhost:11234/graphiql 
## when you run with docker but with docker you can user http://localhost/graphiql because of nginx


![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot1.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot2.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot3.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot4.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot5.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot6.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot7.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot8.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot9.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot10.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot11.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot12.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot13.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot14.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot15.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot16.png)
![GraphiQL](https://raw.githubusercontent.com/daveotengo/amalitec-ecom/main/screenshots/Screenshot17.png)



## Testing the Application:


## GraphQL

### Register

    mutation Register($input: RegisterInput!) {
        register(input: $input) {
            accessToken
            refreshToken
        }
    }

    {    
        "input": {
            "name": "John Doe",
            "email": "johndoe@email.com",
            "password": "password123",
            "role": "USER"
        }
    }

    {   
        "data": {
            "register": {
                "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGVtYWlsLmNvbSIsImlhdCI6MTcxMDkwMDA2NSwiZXhwIjoxNzEwOTg2NDY1fQ.qqWPK4Y-kTVw5oL3lt1PIYSPKxbkh0AJxf7R3vhjPUw",
                "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGVtYWlsLmNvbSIsImlhdCI6MTcxMDkwMDA2NSwiZXhwIjoxNzExNTA0ODY1fQ.KBE4yw8LkbDqxloeOnxpZjHfN_iTtk7Z8BI_ncXdLQc"
            }
        }
    }


### Login
    Request
    mutation AuthenticateUser($input: AuthenticationInput!) {
        authenticate(input: $input) {
           accessToken
            refreshToken
        }
     }


    {
        "input": {
            "email": "johndoe@email.com",
            "password": "password123"
        }
    }
    
    Response

    {
        "data": {
            "authenticate": {
            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGVtYWlsLmNvbSIsImlhdCI6MTcxMDkwMDY2NywiZXhwIjoxNzEwOTg3MDY3fQ.TAyLQPIpln0qkCAnnMy6QI7EJGOimR2PawPYksUO1Fg",
            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lQGVtYWlsLmNvbSIsImlhdCI6MTcxMDkwMDY2NywiZXhwIjoxNzExNTA1NDY3fQ.ZUwHgHFpPaNmmnte_6pwhFhRB4BiN0mofhQIUMv_mQ8"
            }
        }
    }

### Logout
    Request
    mutation LogoutUser {
        logout
    }
    
    Request Headers
    {"Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcxMDk1NjQ5MSwiZXhwIjoxNzExMDQyODkxfQ.upJzJ4njCmwbNg3v0bXRW1FzRdqOE5OjwOs5CZFtNAg"}


### Refresh Token
    Request Body
    mutation RefreshToken {
        refreshToken
    }
    Request Headers
    {"Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcxMDk5NTY4OSwiZXhwIjoxNzExMDgyMDg5fQ.WNvdkLz3S82R7eokZXhqZ64SS-P9picTXp3-bIwZJWg"}
    
    Response Body
    {
    "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcxMDk5NTc1OCwiZXhwIjoxNzExMDgyMTU4fQ.HPJJv1_3TO08EtmtFM02aOA24WbowbQaLvz7wziXbmY",
    "refresh_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcxMDk5NTY4OSwiZXhwIjoxNzExMDgyMDg5fQ.WNvdkLz3S82R7eokZXhqZ64SS-P9picTXp3-bIwZJWg"
}





### To retrieve AllProducts:
   
    GraphiQL Syntax:
    **Note : The authenticated user has to be admin to be able to make a request to 
    products endpoint

     query GetAllProducts {
            getAllProducts {
                id
                name
                price
                stock
            }
        }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }
    
    JSON/postman syntax:
        {
            "query":"{getAllProducts{id name price stock {id name price stock} }}"
        }
    

### To createProduct:
    
    GraphiQL Syntax:
    ** Remember to set the input as shown in the screenshots

    mutation CreateProduct($input: CreateProductInput!) {
        createProduct(input: $input) {
            id
            name
            stock
            price
        }
    
    }

    {
    "input": {
    "name": "Your Product Name",
    "stock": 5,
    "price": 19.99
    }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

    
### To deleteProduct:

    
    mutation DeleteProduct($id: ID!) {
    deleteProduct(id: $id){
      message
      success
    }
    }
        
    {
        "id": "c52e6c2c-b2f7-49d9-90e7-053bb5277f3d"
    }
    
    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

### To UpdateProduct:

    
       mutation UpdateProduct($input: UpdateProductInput!) {
          updateProduct(input: $input) {
            id
            stock
            price
          }
        }

      {
      "input": {
        "id": "8a83e992-4c4c-4e49-a315-80a6c441091e",
        "stock": 50,  
        "price": 29.99  
      }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }
    

### To GetProductById:

    
    query GetProductById($id: ID!) {
        getProductById(id: $id) {
            id
            name
            stock
            price
        }
    }

    {
    "id": "8a83e992-4c4c-4e49-a315-80a6c441091e"
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }
### To GetProductsLowOnStock:

    query GetLowStockItems{
        getLowStockItems {
            id
            name
            price
            stock
        }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

--------------------------------------------------------------------------------


    GraphQL
    To Retrieve All Users
    
    GraphiQL Syntax:
    
    graphql
    

### To Get All Users:
    

    query GetAllUsers {
        getAllUsers {
            id
            name
            email
            password
        }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }


    JSON/Postman Syntax:
    
    json
    {
        "query": "{getAllUsers{id name email password}}"
    }
    ...

###    To Create a User
    
    GraphiQL Syntax:
    
    graphql
    
     mutation CreateUser($input: CreateUserInput!) {
        createUser(input: $input) {
          status
            message
            user{
              email
              name
              id
            }
        }
    }

    JSON:
    
     {
        "input": {
        "name": "David Oteng",
        "email": "daveotengo@gmail.com",
        "password": "password"
        }
    }
    ...

###    To Update a User
    
    GraphiQL Syntax:
    
    graphql

    mutation UpdateUser($input: UpdateUserInput!) {
        updateUser(input: $input) {
            status
            message
            user{
              email
              name
              id
            }
        }
    }

    JSON:

    {
    "input": {
        "name": "David Oteng",
        "email": "daveotengo@gmail.com",
        "password": "password",
        "id": "a33bab5f-490c-445f-9420-acece6be45b3"

        }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }
    ...

###    To Delete a User
    
    GraphiQL Syntax:
    
    graphql

    mutation DeleteUser($id: ID!) {
        deleteUser(id: $id) {
        message
        success
        }
    }
    JSON:
    
    input:
    {
    "id": "a33bab5f-490c-445f-9420-acece6be45b3"
    }

        headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }


### To GetUserById:

    
    query GetUserById($id: ID!) {
        getUserById(id: $id) {
            status
            message
            user{
              email
              name
              id
            }
        }
    }

    input
    {
    "id": "8a83e992-4c4c-4e49-a315-80a6c441091e"
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

----------------------------------------------



    GraphQL
###    To Retrieve All Orders

    GraphiQL Syntax:
    
    graphql

    query GetAllOrders{
        getAllOrders {
            message
            status
            orderList {
            id
            user {
                id
                name
                email
            }
            productLineList {
                quantity
                product {
                    id
                    name
                    stock
                    price
                }
            }
            }
         }
    }


    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

    JSON/Postman Syntax:
    
    json
    {
        "query": "{getAllOrders{id name email password}}"
    }
    ...

###    To Create a Order
    
    GraphiQL Syntax:
    
   
       mutation CreateOrder($input: OrderInput!) {
        createOrder(input: $input) {
            status
            message
            order{
              id
              user {
                id
                name
                email
              }
              productLineList {
                quantity
                product {
                    id
                    name
                    stock
                price
              }
              }
            }
        }
    }

    JSON:
    
    {
        "input": {
        "productLineInputList": [
            {
            "quantity": 1,
            "productId": "fb51e35e-ddbc-4392-869b-8412dac65b3f"
            }
        ],
        "userId": "7b7d51e8-0d9e-47c8-98ba-589b3424ebf6"
        }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

    ...

###    To Update a Order
    
    GraphiQL Syntax:
    
    graphql

    mutation UpdateOrder($input: UpdateOrderInput!) {
        updateOrder(input: $input) {
            status
            message
            order{
              id
              user {
                id
                name
                email
              }
              productLineList {
                quantity
                product {
                    id
                    name
                    stock
                price
              }
              }
            }
        }
    }

    JSON:

    {
        "input": {
            "id": "2f4b8214-9ac0-4396-b431-13f075a62daa"
            "productLineInputList": [
                {
                    "quantity": 1,
                    "productId": "c1972612-df0b-4f05-9cdd-8f67da3029a9"
                }
            ],
            "userId": "2f4b8214-9ac0-4396-b431-13f075a62daa"
        }
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }
    ...

###    To Delete a Order
    
    GraphiQL Syntax:
    
    graphql

    mutation DeleteOrder($id: ID!) {
        deleteOrder(id: $id) {
        message
        success
        }
    }
    JSON:
    
    input: 
    {
    "id": "a33bab5f-490c-445f-9420-acece6be45b3"
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }


### To GetOrderById:

    
    query GetOrderById($id: ID!) {
        getOrderById(id: $id) {
             message
            status
            orderList {
            id
            user {
                id
                name
                email
            }
            productLineList {
                quantity
                product {
                    id
                    name
                    stock
                    price
                }
            }
            }
        }
    }

    {
    "id": "a15051f1-73fb-45cb-80d5-e1c0c7554c06"
    }

    headers :
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }

### Curl request to graphql:

    
    without auth
    curl -X POST \
    -H "Content-Type: application/json" \
    --data '{"query": "query { getAllProducts { id name price stock } }"}' \
    http://localhost:11234/graphql
    

    with auth
    curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1haWwuY29tIiwiaWF0IjoxNzAxNTAwODY2LCJleHAiOjE3MDE1ODcyNjZ9.C5XB0v0sdTw90ZtIhku1MChp-8tEo0Uss_PQYtCvrlk" \
    --data '{"query": "{ getAllProducts { id name price stock } }"}' \
    http://localhost:11234/graphql


### Curl response to graphql:
     
    {"data":{"getAllProducts":[{"id":"e7a571a8-1970-497a-b658-5f43468c4d1b","name":"Product A","price":20.0,"stock":50},{"id":"ad726ff2-d9cf-4a77-8954-57fe9ace3c4c","name":"Product B","price":30.0,"stock":30}]}}%   
    
    

### Authorization request send in headers
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTk2NjQ3MywiZXhwIjoxNzAyMDUyODczfQ.oUPCLzsdCJlslNGp6Z41bG0nmEzC42XJY6aob1IVUbk"
    }

### Response showing forbidden access message

    {
    "errors": [
        {
            "message": "Forbidden",
            "locations": [
                {
                    "line": 1,
                    "column": 9
                }
            ],
            "path": [
                "getAllProducts"
            ],
            "extensions": {
                "classification": "FORBIDDEN"
            }
        }
    ],
    "data": {
        "getAllProducts": null
    }
    }

    mvn clean install -DskipTests=true



