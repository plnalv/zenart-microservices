create table if not exists category
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists masterclass
(
    id integer not null primary key,
    description varchar(255),
    date timestamp,
    time time,
    name varchar(255),
    seats_quantity integer not null,
    price numeric(38, 2),
    category_id integer -- Внешний ключ для связи с категорией
            constraint fk1asdgfhjklasdfgh references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists masterclass_seq increment by 50;