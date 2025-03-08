create table medicine(
medname VARCHAR(70) primary key,
dose VARCHAR(30), amount VARCHAR(50), pricePerUnit int, img VARCHAR(200)
);

create table equipment(
name VARCHAR(70) primary key,
price INT, img VARCHAR(200)
);

insert into medicine values('Almex', '400 mg', '4 tablets/strip', 5,  '/resources/img/Almex.jpg');
insert into medicine values('Amdocal', '5 mg', '10 tablets/strip', 5,  '/resources/img/amdocal.jpg');
insert into medicine values('Aristophen', '10 ml', '1 bottle/pack', 100,  '/resources/img/aristophen.jpg');
insert into medicine values('Azepam', '5 mg', '10 tablets/strip', 10,  '/resources/img/azepam.jpg');
insert into medicine values('Ceevit', '250 mg', '10 tablets/strip', 7,  '/resources/img/Ceevit.jpg');

insert into equipment values('Blood Sugar Machine', 1000, '/resources/img/bloodsugar.jpg');
insert into equipment values('Digital Pressure Machine', 2500, '/resources/img/digitalbp.jpg');
insert into equipment values('Manual Pressure Machine', 500, '/resources/img/manual-blood-pressure-machine.jpg');
insert into equipment values('Oximeter', 350, '/resources/img/oximeter.png');

select * from equipment;