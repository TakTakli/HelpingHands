create table medicine(
product_id INT primary key auto_increment, 
medname VARCHAR(70),
dose INT, doseunit VARCHAR(5), amount VARCHAR(50), pricePerUnit DOUBLE, img VARCHAR(200)
);

create table equipment(
product_id INT primary key auto_increment, name VARCHAR(70),
price DOUBLE, img VARCHAR(200)
);

insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img) values('Almex', 400, 'mg', '4 tablets/strip', 5.00,  '/resources/img/Almex.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Amdocal', 5, 'mg', '10 tablets/strip', 5.00,  '/resources/img/amdocal.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Aristophen', 10, 'ml', '1 bottle/pack', 100.00,  '/resources/img/aristophen.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Azepam', 5, 'mg', '10 tablets/strip', 10.00,  '/resources/img/azepam.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Ceevit', 250, 'mg', '10 tablets/strip', 7.00,  '/resources/img/Ceevit.jpg');

insert into equipment(name, price, img) values('Blood Sugar Machine', 1000.00, '/resources/img/bloodsugar.jpg');
insert into equipment(name, price, img)  values('Digital Pressure Machine', 2500.00, '/resources/img/digitalbp.jpg');
insert into equipment(name, price, img)  values('Manual Pressure Machine', 500.00, '/resources/img/manual-blood-pressure-machine.jpg');
insert into equipment(name, price, img)  values('Oximeter', 350.00, '/resources/img/oximeter.png');