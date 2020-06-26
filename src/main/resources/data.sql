use `grupo-17-bdd-oo2-2020`;

-- Data Address
insert into address 
			values 	(1, "Monte Grande", -34.815343, -58.465338, 246, "Gral. Rodríguez"),
					(2, "Luis Guillón", -34.807921, -58.444249, 1745, "Bv. Buenos Aires"),
                    (3, "Temperley", -34.781591, -58.406378, 10699, "Av. Pres. Hipólito Yrigoyen"),
                    (4, "Canning", -34.860099, -58.503009, 1277, "Mariano Castex"), 
                    (5, "Adrogué", -34.800227, -58.397167, 669, "Francisco Seguí"),
                    (6, "Lomas de Zamora", -34.766522, -58.402316, 501, "Italia");
					
-- Data Store
insert into store 
			values 	(1, 43829472, 1), (2, 42638262, 2), (3, 23710293, 3), (4, 42088232, 4), (5, 33218321, 5), 
					(6, 53328821, 6);
                    
-- Data Store
insert into person 
			values 	("Client", 1, '1988-10-2', 34643212, "Carlos", "Silva"),
					("Client", 2, '1999-11-5', 42232326, "Marta", "Torres"),
                    ("Client", 3, '1950-2-26', 17849237, "Roberto", "Milic"),
                    ("Employee", 4, '1959-06-30', 14459434, "Rocío", "Cortés"),
                    ("Employee", 5, '1973-05-07', 22718823, "Valentina", "Saavedra"),
                    ("Employee", 6, '1960-03-28', 15233546, "Guido", "Machado"),
                    ("Employee", 7, '1980-11-25', 28482948, "Brenda", "Rodriguez"),
                    ("Employee", 8, '1962-05-05', 17936591, "Ismael", "Prego"),
                    ("Employee", 9, '1983-05-15', 30213831, "Carla", "Carrasco"),
                    ("Employee", 10, '1978-06-24', 26752869, "Juan Román", "Riquelme"),
                    ("Employee", 11, '1984-09-08', 32419312, "Dario", "Benedetto"),
                    ("Employee", 12, '1991-08-30', 38213403, "Carlos", "Silva"),
                    ("Employee", 13, '1996-11-27', 40123485, "Celeste", "Rios"),
                    ("Client", 14, '1986-05-16', 28296757, "Leandro", "Romero"),
                    ("Client", 15, '1996-02-14', 40213573, "Lautaro", "Turati"),
                    ("Client", 16, '2000-10-02', 43218429, "Federico", "Luengo"),
                    ("Client", 17, '2002-04-28', 45810251, "Ramiro", "Ruiz"),
                    ("Client", 18, '2000-04-11', 42320398, "Brenda", "Dominguez"),
                    ("Client", 19, '2000-01-22', 42297093, "Carla", "Milic"),
                    ("Client", 20, '1984-09-12', 28320398, "David Luis", "Hernandez"),
                    ("Client", 21, '1972-03-05', 22835175, "Jorge", "Lopez"),
                    ("Employee", 22, '1982-12-08', 32457482, "Marcos", "Di Palma"),
                    ("Employee", 23, '1962-10-24', 19328412, "Jaime", "Altozano"),
                    ("Employee", 24, '1999-6-03', 42927481, "Macarena", "Sanchez"),
                    ("Employee", 25, '1995-9-14', 39284700, "Cecilia", "Diaz");
                    
-- Data Client
insert into `client`
			values 	("carlos.silva@gmail.com", 1), ("martitah.torres@hotmail.com", 2), ("rmilic@gmail.com", 3),
					("leito.r.r@outlook.com", 14), ("otaku03@gmail.com", 15), ("fede_ebrio_23@yahoo.com.ar", 16),
                    ("ramadmg@hotmail.com", 17), ("brendiskey@gmail.com", 18), ("car.milic@gmail.com", 19),
                    ("david.luis.hernandez@hotmail.com", 20), ("jorgito001@outlook.com", 21);
            
-- Data Employee
insert into employee values (false, 26000, 0, 8, 4, 1), (true, 35000, 0, 8, 5, 1), (true, 25000, 0, 8, 6, 2), 
							(false, 18000, 0, 4, 7, 3), (false, 20000, 0, 6, 8, 4), (false, 26500, 0, 8, 9, 4), 
                            (true, 50000, 0, 6, 10, 4), (false, 22000, 0, 6, 11, 5), (true, 26200, 0, 8, 12, 5), 
                            (false, 23200, 0, 8, 13, 6), (false, 25200, 0, 8, 22, 2), (true, 22500, 0, 8, 23, 3),
                            (false, 18000, 0, 6, 24, 1), (true, 30000, 0, 8, 25, 6);
                            
-- Data Product
insert into product
			values 	(1, "Nike", null, "Botines de Messi", '2020-05-19', 1, 6000, null), 
					(2, "Nike", null, "Zapatillas 2D-1", '2020-05-18', 1, 3400, null), 
					(3, "Adidas", null, "Remera adudis", '2020-05-17', 1, 2000, null), 
                    (4, "Adidas", null, "Pantalon de arquero", '2020-05-16', 1, 1600, null),
                    (5, "Adidas", null, "Botines negros", '2020-05-16', 1, 5250, null),
                    (6, "Puma", null, "Medias 4A3", '2020-05-09', 1, 300, null),
                    (7, "Gucci", null, "Las gucci de Duki", '2020-05-26', 1, 10000, null),
                    (8, "Nike", null, "Short de Duki", '2020-05-10', 1, 8000, null),
                    (9, "Puma", null, "Campera verde militar", '2020-05-03', 1, 4700, null),
                    (10, "Reebok", null, "Botines rojo y negro", '2020-06-03', 1, 4700, null),
                    (11, "Reebok", null, "Enterizo de natacion", '2020-06-20', 1, 2500, null),
                    (12, "Reebok", null, "Calzas biker", '2020-06-01', 1, 2000, null),
                    (13, "Puma", null, "Gorras", '2020-06-15', 1, 1000, null),
                    (14, "Puma", null, "Medias amarillas ralladas", '2020-06-13', 1, 700, null),
                    (15, "Nike", null, "Joggins", '2020-06-09', 1, 400, null),
                    (16, "Puma", null, "Antiparras", '2020-06-25', 1, 500, null);

-- Data Batch 
insert into batch
			values 	(1, '2020-05-01', 10, 10, "42", 1, 1), (2, '2020-05-01', 30, 30, "40", 2, 1),
					(3, '2020-05-01', 10, 10, "M", 3, 1), (4, '2020-05-01', 20, 20, "L", 4, 2),
                    (5, '2020-05-01', 10, 10, "40", 5, 2), (6, '2020-05-01', 10, 10, "XL", 6, 2),
                    (7, '2020-05-01', 20, 20, "41", 7, 3), (8, '2020-05-01', 10, 10, "S", 8, 3),
                    (9, '2020-05-01', 30, 30, "M", 9, 3), (10, '2020-05-01', 10, 10, "42", 10, 4),
                    (11, '2020-05-01', 20, 20, "M", 11, 4), (12, '2020-05-01', 50, 50, "XL", 12, 4),
                    (13, '2020-05-01', 40, 40, "M", 13, 5), (14, '2020-05-01', 30, 30, "S", 14, 5),
                    (15, '2020-05-01', 10, 10, "L", 15, 6), (16, '2020-05-01', 10, 10, "M", 16, 6), 
                    (17, '2020-05-01', 20, 20, "40", 1, 2), (18, '2020-05-01', 10, 10, "41", 2, 3),
                    (19, '2020-05-01', 10, 10, "S", 3, 4), (20, '2020-05-01', 10, 10, "L", 6, 5),
                    (21, '2020-05-01', 10, 10, "L", 8, 6);