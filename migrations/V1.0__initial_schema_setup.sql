CREATE TABLE app_user( 
id serial primary key, 
name VARCHAR(255) NOT NULL, 
email_address VARCHAR(255) NOT NULL, 
dob DATE NOT NULL, 
password VARCHAR(255) NOT NULL 
);

 

CREATE TABLE boat( 
id serial primary key, 
name VARCHAR(255) NOT NULL, 
sail_no VARCHAR(100) NOT NULL,  
boat_class VARCHAR(255) NOT NULL,  
age integer NOT NULL, 
description VARCHAR(255) NOT NULL
);

 

CREATE TABLE event( 
id serial primary key, 
name VARCHAR(255) NOT NULL, 
type VARCHAR(100) NOT NULL, 
date DATE NOT NULL, 
description VARCHAR(255) NOT NULL,
status int NOT NULL
);

 

CREATE TABLE boat_owner( 
id serial primary key, 
boat_id integer REFERENCES boat(id), 
user_id integer REFERENCES app_user(id) 
);

 

CREATE TABLE crewmate( 
id serial primary key, 
user_id integer REFERENCES app_user(id) 
);

 

CREATE TABLE admin( 
id serial primary key, 
user_id integer REFERENCES app_user(id) 
);

 

CREATE TABLE boat_crewmate( 
id serial primary key, 
boat_id integer REFERENCES boat(id), 
crewmate_id integer REFERENCES crewmate(id) 
); 

 

CREATE TABLE event_admin( 
id serial primary key, 
event_id integer REFERENCES event(id), 
admin_id integer REFERENCES admin(id) 
); 

 

CREATE TABLE event_boat( 
id serial primary key, 
boat_id integer REFERENCES boat (id), 
event_id integer REFERENCES event (id), 
position integer NULL, 
time time NULL 
); 