INSERT INTO user_access_status(status) 
VALUES ('STAGED');
INSERT INTO user_access_status(status) 
VALUES ('ACTIVE');
INSERT INTO user_access_status(status) 
VALUES ('ADMIN');
INSERT INTO user_access_status(status) 
VALUES ('BOAT_OWNER');
INSERT INTO user_access_status(status) 
VALUES ('CREW');

INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('Niall Walters', 'niallwalters135@gmail.com', '1998-10-25', 'Password135', '2'); 
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('Sarah Jennings', 'sarahjennings1@gmail.com', '2001-01-23', 'Password15', '2'); 
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('Aoibhin Wood', 'awood3@gmail.com', '1999-12-07', 'Password10', '3'); 
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('James Regan', 'jregan10@gmail.com', '1968-04-23', 'JamesPassword13', '4'); 
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('James Curran', 'jjcurran01@gmail.com', '1976-04-05', 'JamesPassword13', '5');
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('David Maxwell', 'dmaxwell4@gmail.com', '1965-03-06', 'Admin101', '5'); 
INSERT INTO app_user (name, email_address, dob, password, user_access_status_id)
VALUES ('Stephen Walters', 'walters3147@gmail.com', '1967-08-03', 'Password14', '5'); 

INSERT INTO boat(name, sail_no, boat_class, age, description)
VALUES ('Lizante', '1967', 'NHC 1', '2007', 'Jeanneau Sun Oddessey'); 
INSERT INTO boat(name, sail_no, boat_class, age, description)
VALUES ('Beeste', '13842', 'IRC 2', '2005', 'Hunter Impala'); 

INSERT INTO event(name, type, date, description, status)
VALUES ('EDYC Club Race 3', 'Club Race', '2022-06-24', 'Third Event of club racing', '3'); 
INSERT INTO event(name, type, date, description, status)
VALUES ('SLYC Regatta', 'Regatta', '2022-05-13', 'SLYC Regatta Event', '3'); 

INSERT INTO boat_owner(boat_id, user_id)
VALUES ((select id from boat where name = 'Lizante'), (select id from app_user where name = 'James Regan')); 
INSERT INTO boat_owner(boat_id, user_id)
VALUES ((select id from boat where name = 'Beeste'), (select id from app_user where name = 'James Curran')); 

INSERT INTO crewmate(user_id)
VALUES ((select id from app_user where name = 'Niall Walters')); 
INSERT INTO crewmate(user_id)
VALUES ((select id from app_user where name = 'Sarah Jennings')); 
INSERT INTO crewmate(user_id)
VALUES ((select id from app_user where name = 'Aoibhin Wood')); 
INSERT INTO crewmate(user_id)
VALUES ((select id from app_user where name = 'Stephen Walters')); 

INSERT INTO admin(user_id)
VALUES ((select id from app_user where name = 'David Maxwell')); 

INSERT INTO boat_crewmate(boat_id, crewmate_id)
VALUES ((select id from boat where name = 'Lizante'), (select id from crewmate where user_id = (select id from app_user where name = 'Niall Walters'))); 
INSERT INTO boat_crewmate(boat_id, crewmate_id)
VALUES ((select id from boat where name = 'Lizante'), (select id from crewmate where user_id = (select id from app_user where name = 'Sarah Jennings'))); 
INSERT INTO boat_crewmate(boat_id, crewmate_id)
VALUES ((select id from boat where name = 'Lizante'), (select id from crewmate where user_id = (select id from app_user where name = 'Aoibhin Wood'))); 
INSERT INTO boat_crewmate(boat_id, crewmate_id)
VALUES ((select id from boat where name = 'Beeste'), (select id from crewmate where user_id = (select id from app_user where name = 'Stephen Walters'))); 

INSERT INTO event_boat(event_id, boat_id, position, time)
VALUES ((select id from event where name = 'EDYC Club Race 3'), (select id from boat where name = 'Lizante'), '1', '01:17:13'); 
INSERT INTO event_boat(event_id, boat_id, position, time)
VALUES ((select id from event where name = 'EDYC Club Race 3'), (select id from boat where name = 'Beeste'), '2', '01:21:12'); 
INSERT INTO event_boat(event_id, boat_id, position, time)
VALUES ((select id from event where name = 'SLYC Regatta'), (select id from boat where name = 'Lizante'), '3', '01:54:10'); 
INSERT INTO event_boat(event_id, boat_id, position, time)
VALUES ((select id from event where name = 'SLYC Regatta'), (select id from boat where name = 'Beeste'), '6', '02:24:03'); 

INSERT INTO event_admin(event_id, admin_id)
VALUES ((select id from event where name = 'EDYC Club Race 3'), (select id from admin where user_id = (select id from app_user where name = 'David Maxwell'))); 
INSERT INTO event_admin(event_id, admin_id)
VALUES ((select id from event where name = 'SLYC Regatta'), (select id from admin where user_id = (select id from app_user where name = 'David Maxwell')));