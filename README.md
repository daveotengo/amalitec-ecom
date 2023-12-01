# Amalitec Ecommerce Application: 
# Name of Author : David Oteng

This Standalone Spring boot project demonstrates the crud operations on Order services using GraphQL as API.


## 1. In Memory H2 Database is used.
    So you can modify application.properies as per your database. 

## 2. Running as a Packaged Application (Following ways)
    Way-1 : java -jar target/amalitec-ecom-0.0.1-SNAPSHOT.jar
    Way-2 : docker compose up -d to build docker compose sert to start

## 3. Once the application is started you can access http://localhost:11234/graphiql
    ![GraphiQL](screen-shots/graphiql.PNG)

## Testing the Application:

## GraphQL

### To retrieve AllProducts:
    ```
    GraphiQL Syntax:

     query GetAllProducts {
            getAllProducts {
                id
                name
                price
                stock
            }
        }
    
    JSON/postman syntax:
        {
            "query":"{getAllProducts{id name price stock {id name price stock} }}"
        }
    ```

### To createProduct:
    ```
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

    ```
### To deleteProduct:

    ```
    mutation DeleteProduct($id: ID!) {
    deleteProduct(id: $id){
      message
      success
    }
    }
        
    {
        "id": "c52e6c2c-b2f7-49d9-90e7-053bb5277f3d"
    }
    ```

### To UpdateProduct:

    ```
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

    ```

### To GetProductById:

    ```
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

### To GetProductsLowOnStock:

    query GetLowStockItems{
        getLowStockItems {
            id
            name
            price
            stock
        }
    }


--------------------------------------------------------------------------------


    GraphQL
    To Retrieve All Users
    
    GraphiQL Syntax:
    
    graphql
    ```

### To Get All Users:
    ```

    query GetAllUsers {
        getAllUsers {
            id
            name
            email
            password
        }
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
    
    {
    "id": "a33bab5f-490c-445f-9420-acece6be45b3"
    }


### To GetUserById:

    ```
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

    {
    "id": "8a83e992-4c4c-4e49-a315-80a6c441091e"
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
    
    {
    "id": "a33bab5f-490c-445f-9420-acece6be45b3"
    }


### To GetOrderById:

    ```
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

### Curl request to graphql:

    ```

    curl -X POST \
    -H "Content-Type: application/json" \
    --data '{"query": "query { getAllProducts { id name price stock } }"}' \
    http://localhost:11234/graphql
    ```

    curl -X POST \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTAzODY1MiwiZXhwIjoxNzAxMTI1MDUyfQ.b25fwJMuJovORSRu1MJzQ4hnAoemwxnNBQbTBy1uPhw" \
    --data '{"  query { getAllProducts { id name price stock } }"}' \
    http://localhost:11234/graphql

### Curl response to graphql:
     ```
    {"data":{"getAllProducts":[{"id":"e7a571a8-1970-497a-b658-5f43468c4d1b","name":"Product A","price":20.0,"stock":50},{"id":"ad726ff2-d9cf-4a77-8954-57fe9ace3c4c","name":"Product B","price":30.0,"stock":30}]}}%   
    
    ```

### Authorization request send in headers
    {
        "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcwMTQ1MzY0OSwiZXhwIjoxNzAxNTQwMDQ5fQ.IiNkL0TtZQzk3lcqjT_YF2qFUfPuZDZMwBhXdMx3fuI"
    }