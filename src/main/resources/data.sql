insert into skill(name) values('Frontend developer');
insert into skill(name) values('Backend developer');

insert into company(company_name) values('TVZ Company');

insert into person(name, email, phone, description, achievements, skill_id, salary, rating, experience, company_id)
values('Patrik Stojsavljevic', 'patrik@gmail.com', '0955555555', 'patrik description', 'patrik achievements', 1, 1500, 4.5, 2, 1);

insert into person(name, email, phone, description, achievements, skill_id, salary, rating, experience, company_id) values('Jan Stojsavljevic', 'jan@gmail.com', '0956666666', 'Jan description', 'Jan achievements', 2, 2500, 4, 1, 1);

insert into USERS(username, password) values('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'); --password
insert into USERS(username, password) values('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'); --password
insert into USERS(username, password) values('read_only', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'); --password
insert into USERS(username, password) values('dummy', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'); --password

insert into roles(name) values('USER');
insert into roles(name) values('ADMIN');
insert into roles(name) values('READ_ONLY');
insert into roles(name) values('DUMMY');

insert into role_user(application_user_id, role_id) values(1, 1);
insert into role_user(application_user_id, role_id) values(2, 1);
insert into role_user(application_user_id, role_id) values(2, 2);
insert into role_user(application_user_id, role_id) values(3, 3);