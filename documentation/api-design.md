## Movies

### Retrieve a list of all movies.

GET /movies

```
{
   [
      {
         "movie_id":"integer",
         "movie_name":"varchar",
         "year":"integer",
         "banner":"varchar"
      }
   ]
}
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
   },
   "director_id":{
      "person_id":"int",
      "person_fname varchar",
      "person_lname varchar"
   },
   "producer_id":{
      "person_id":"int",
      "person_fname":"varchar",
      "person_lname":"varchar"
   }
}
```

### Create a new movie. Requires movie_name, year, description, banner, categorie_id, director_id, producer_id.

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
   },
   "director_id":{
      "person_id":"int",
      "person_fname":"varchar",
      "person_lname":"varchar"
   },
   "producer_id":{
      "person_id":"int",
      "person_fname":"varchar",
      "person_lname":"varchar"
   }
}
```


## Celebrities

### Retrieve a list of all celebrities. 

GET /celebrities

```
[
   {
      "person_id":"integer",
      "person_fname":"varchar",
      "person_lname":"varchar",
      "photo":"varchar"
   }
]
```

### Retrieve all details of a specific celebrity by id.


GET /celebrities/{id}

```
{
   "person_id":"integer",
   "person_fname":"varchar",
   "person_mname":"varchar",
   "person_lname":"varchar",
   "photo":"varchar",
   "birthday":"date"
}
```

### Create a new celebrity

POST /celebrities: 

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

PUT /celebrities/{id}: 

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


## Cast

### Retrieve all details of a specific cast by cast_id.

GET /casts/{cast_id}

```
{
   "cast_id":"integer",
   "movie_id":{
      "movie_id":"int",
      "movie_name":"varchar"
   },
   "actor_id":{
      "person_id":"int",
      "person_fname":"varchar",
      "person_lname":"varchar"
   }
}
```

### Create a new cast

POST /casts

```
{
   "cast_id":"integer",
   "movie_id":{
      "movie_id":"int",
      "movie_name":"varchar"
   },
   "actor_id":{
      "person_id":"int",
      "person_fname":"varchar",
      "person_lname":"varchar"
   }
}
```

