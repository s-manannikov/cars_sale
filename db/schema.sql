create table engine (
    id serial primary key
);

create table driver (
    id serial primary key
);

create table car (
    id serial primary key,
    brand text,
    model text,
    body text,
    engine_id int not null references engine(id)
);

create table history_owner (
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);

create table photo (
    id serial primary key,
    name text
);

create table users (
    id serial primary key,
    name text,
    email text unique,
    password text
);

create table post (
    id serial primary key,
    description text,
    car_id int references car(id),
    photo_id int references photo(id),
    status int,
    user_id int references users(id),
    created timestamp
);