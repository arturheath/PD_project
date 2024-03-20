// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs

Table movies {
  movie_id integer [primary key]
  movie_name varchar
  year integer 
  description varchar
  banner varchar
  categorie_id integer
  director_id integer
  producer_id integer
}

Table celebreties {
  person_id integer [primary key]
  person_fname varchar
  person_mname varchar
  person_lname varchar
  photo varchar
  birthday date
}

Table categorie {
  categorie_id integer [primary key]
  categorie_name varchar
}

Table cast {
  cast_id integer [primary key]
  movie_id integer 
  actor_id integer
}



Ref: movies.categorie_id < categorie.categorie_id
Ref: movies.director_id < celebreties.person_id
Ref: movies.producer_id < celebreties.person_id
Ref: cast.movie_id < movies.movie_id
Ref: cast.actor_id < celebreties.person_id