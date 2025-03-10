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
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Entacyd Plus', 200, 'ml', '1 bottle/pack', 120.00,  '/resources/img/ENTACYD-PLUS-200.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Eromycin', 100, 'ml', '1 bottle/pack', 140.00,  '/resources/img/EROMYCIN-100ML.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Folison ', 5, 'mg', '10 tablets/strip', 10.00,  '/resources/img/folison.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Glucomax D', 75, 'g', '1 sachet', 12.00,  '/resources/img/glucomax.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Larsulin', 100, 'IU/ml', '1 bottle/pack', 10.00,  '/resources/img/larsulin.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Moxaclav 375 ', 375, 'mg', '9 capsules/strip', 15.00,  '/resources/img/Moxaclav.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Napa', 500, 'mg', ' 10 tablets/strip', 15.00,  '/resources/img/napa.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Orsaline-N', 500, 'mg', '20 packets/box', 50.00,  '/resources/img/orsaline.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Pantonix', 20, 'mg', '14 tablets/strip', 12.00,  '/resources/img/Pantonix-20-Tab.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Profen', 100, 'ml', '1 bottle/pack', 80.00,  '/resources/img/profen.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Propranol', 10, 'mg', '10 tablets/strip', 20.00,  '/resources/img/propranolol.png');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Riboflavin', 5, 'mg', '10 tablets/strip', 8.00,  '/resources/img/riboflavin.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Rice ORS', 500, 'ml', '10 packets/box', 100.00,  '/resources/img/rice_saline.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Seclo', 40, 'mg', '6 capsules/strip', 10.00,  '/resources/img/Seclo.jpg');
insert into medicine(medname, dose, doseunit, amount, pricePerUnit, img)  values('Sergel', 20, 'mg', '10 tablets/strip', 20.00,  '/resources/img/sergel.jpg');

insert into equipment(name, price, img) values('Blood Sugar Machine', 1000.00, '/resources/img/bloodsugar.jpg');
insert into equipment(name, price, img)  values('Digital Pressure Machine', 2500.00, '/resources/img/digitalbp.jpg');
insert into equipment(name, price, img)  values('Manual Pressure Machine', 500.00, '/resources/img/manual-blood-pressure-machine.jpg');
insert into equipment(name, price, img)  values('Oximeter', 350.00, '/resources/img/oximeter.png');
insert into equipment(name, price, img)  values('Crutch', 480.00, '/resources/img/crutch.jpg');
insert into equipment(name, price, img)  values('Elbow Crutch', 650.00, '/resources/img/elbowcrutch.jpg');
insert into equipment(name, price, img)  values('Wheelchair', 1500.00, '/resources/img/wheelchair.jpg');