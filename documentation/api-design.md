## Movies

### Retrieve a list of all movies.

GET /movies

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "year":"integer",
      "banner":"varchar"
   }
]
```

###  Retrieve all details of a specific movie by movie_id

GET /movies/{movie_id}

```
{
   "movie_id":"integer",
   "movie_name":"varchar",
   "year":"integer",
   "description":"varchar",
   "banner":"varchar",
   "categorie_id":{
      "categorie_id":"int",
      "categorie_name":"varchar"
   }
}
```

### Create a new movie. Requires movie_name, year, description, banner, categorie_id.

POST /movies

```
{
   "movie_name":"varchar",
   "year":"integer",
   "description":"varchar",
   "banner":"varchar",
   "categorie_id":{
      "categorie_id":"int",
      "categorie_name":"varchar"
   }
}
```
### Delete a movie.

DELETE /movies

```
{
   "movie_name":"varchar",
   "year":"integer",
   "description":"varchar",
   "banner":"varchar",
}
```

## People

### Retrieve all of the directors of a movie.

GET /movies/{movie_id}/directors

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "director_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar"
      }
   }
]
```
### Retrieve all of the producers of a movie.

GET /movies/{movie_id}/producers

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "producer_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar"
      }
   }
]
```
### Retrieve all the information of a specific director of a movie.

GET /movies/{movie_id}/directors/{director_id}: 

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "director_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_mname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar",
         "birthday":"date"
      }
   }
]
```
### Retrieve all the information of a specific producer of a movie.

GET /movies/{movie_id}/producer/{producer_id}: 

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "producer_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_mname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar",
         "birthday":"date"
      }
   }
]
```

### Retrieve all the cast of a specific movie.

GET /movies/{movie_id}/cast:

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "person_id":"integer",
      "person_fname":"varchar",
      "person_mname":"varchar",
      "person_lname":"varchar",
      "photo":"varchar",
      "birthday":"date"
   }
]
```

### Add a director to a specific movie.

POST /movies/{movie_id}/directors:

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "director_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar"
      }
   }
]
```
### Add a producer to a specific movie.

POST /movies/{movie_id}/producers:

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "director_id":{
         "person_id":"integer",
         "person_fname":"varchar",
         "person_lname":"varchar",
         "photo":"varchar"
      }
   }
]
```
### Add a person to the cast of a specific movie.

POST /movies/{movie_id}/cast:

```
[
   {
      "movie_id":"integer",
      "movie_name":"varchar",
      "person_id":"integer",
      "person_fname":"varchar",
      "person_mname":"varchar",
      "person_lname":"varchar",
      "photo":"varchar",
      "birthday":"date"
   }
]
```


### Create a new celebrity

POST /people: 

```
{
   "person_fname":"varchar",
   "person_mname":"varchar",
   "person_lname":"varchar",
   "photo":"varchar",
   "birthday":"date"
}
```

### Update a specific celebrity by id. Can update only mnane,lname and photo.

PUT /people/{id}: 

```
{
   "person_fname":"varchar",
   "person_mname":"varchar",
   "person_lname":"varchar",
   "photo":"varchar"
}
```

## Categories

### Create a new category. Requires categorie_name.

POST /categories:

```
{
   "categorie_id":"integer",
   "categorie_name":"varchar"
}
```

