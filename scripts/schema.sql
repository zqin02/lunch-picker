use db;
 
CREATE TABLE restaurant (
    user_id VARCHAR(64) NOT NULL,
    restaurant_name VARCHAR(64) NOT NULL,
    session_id VARCHAR(64) NOT NULL,
    PRIMARY KEY (user_id , restaurant_name , session_id)
);
CREATE TABLE session (
    uuid VARCHAR(64) PRIMARY KEY,
    user_id VARCHAR(64) NOT NULL,
	user_alias VARCHAR(64)
);