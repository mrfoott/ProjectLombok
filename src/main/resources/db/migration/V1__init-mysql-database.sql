drop table if exists beer;

drop table if exists customer;

create table beer (
                      id varchar(36) not null,
                      `version` integer,
                      beer_name varchar(50) not null,
                      beer_style tinyint not null check (beer_style between 0 and 10),
                      price decimal(38,2) not null,
                      quantity_on_hand integer,
                      upc varchar(255) not null,
                      created_date datetime(6),
                      update_date datetime(6),
                      primary key (id)
);

create table customer (
                          id varchar(36) not null,
                          `version` integer,
                          name varchar(255),
                          update_date datetime(6),
                          created_date datetime(6),
                          primary key (id)
);
