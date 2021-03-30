create table engine (
    id serial primary key,
    name text
);

create table body (
    id serial primary key,
    name text
);

create table brand (
    id serial primary key,
    name text
);

create table transmission (
    id serial primary key,
    name text
);

create table car (
    id serial primary key,
    brand_id int references brand(id),
    model text,
    body_id int references body(id),
    year text,
    hp int,
    mileage int,
    transmission_id int references transmission(id),
    color text,
    price int,
    engine_id int not null references engine(id)
);

create table photo (
    id serial primary key,
    name text
);

create table users (
    id serial primary key,
    name text,
    email text unique,
    phone text,
    password text
);

create table city (
    id serial primary key,
    name text
);

create table post (
    id serial primary key,
    description text,
    car_id int references car(id),
    photo_id int references photo(id),
    status int,
    user_id int references users(id),
    created timestamp,
    city_id int references city(id)
);