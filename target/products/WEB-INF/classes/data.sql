truncate table basket cascade;
truncate table users1 cascade;
truncate table roles1 cascade;
truncate table goods cascade;

insert into goods (name, price) values ('bread', 20);
insert into goods (name, price) values ('ice cream', 40);
insert into goods (name, price) values ('water', 25);
insert into goods (name, price) values ('tomatoes', 31);
insert into goods (name, price) values ('apples', 32);
insert into goods (name, price) values ('pasta', 35);
insert into goods (name, price) values ('beef', 70);
insert into goods (name, price) values ('potatoes', 15);
insert into goods (name, price) values ('salt', 20);
insert into goods (name, price) values ('sugar', 19);
insert into goods (name, price) values ('crisps', 42);
insert into goods (name, price) values ('beer', 53);
insert into goods (name, price) values ('sausage', 48);
insert into goods (name, price) values ('milk', 20);
insert into goods (name, price) values ('juice', 30);
insert into goods (name, price) values ('burgers', 70);
insert into goods (name, price) values ('sauce', 15);

insert into users1 (firstname, lastname, phone_number, email, password)
values ('Alexandr', 'Chesnokov', '89261234567', 'admin@a.ua', '$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');
insert into users1 (firstname, lastname, phone_number, email,  password)
values ('Ada', 'Lovelace', '89261234567', 'manager@a.ua','$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');
insert into users1 (firstname, lastname, phone_number, email, password)
values ('Alan', 'Turing', '89261234567', 'user@a.ua','$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');
insert into users1 (id, firstname, lastname, phone_number, email, password)
values (133241, 'Vasia', 'Pupkin', '89261234567', 'vasia@a.ua','$2a$10$CIy6hcBPxgflOyj8IRVDj.hegrWet/3UhtKghgLo.Pbku3xOnENee');

insert into roles1 (id, name) values (1,'ADMIN');
insert into roles1 (id, name) values (2,'MANAGER');
insert into roles1 (id, name) values (3,'USER');


insert into user_roles1 (user_id, role_id) values (133241,1);
