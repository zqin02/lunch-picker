CREATE TABLE restaurant(
user_id      varchar2(64)  NOT NULL,
restaurant_name varchar2(64)   NOT NULL,
session_id  varchar2(64) NOT NULL,
primary key (user_id,restaurant_name,session_id)
);
CREATE TABLE session(
                        uuid        varchar(64)  primary key,
                        user_id      varchar2(64)  NOT NULl
);