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
                    ("Employee", 13, '1996-11-27', 40123485, "Celeste", "Rios");
                    
-- Data Client
insert into `client`
			values ("carlos.silva@gmail.com", 1), ("martitah.torres@hotmail.com", 2), ("rmilic@gmail.com", 3);
            
-- Data Employee
insert into employee values (false, 26000, 0, 8, 4, 1), (true, 35000, 0, 8, 5, 1), (false, 25000, 0, 8, 6, 2), 
							(false, 18000, 0, 4, 7, 3), (false, 20000, 0, 6, 8, 4), (false, 26500, 0, 8, 9, 4), 
                            (true, 50000, 0, 6, 10, 4), (false, 22000, 0, 6, 11, 5), (false, 26200, 0, 8, 12, 5), 
                            (false, 23200, 0, 7, 13, 6);
                            
-- Data Product
insert into product
			values 	(1, "Nike", null, "Botines de Messi", '2020-05-19', 1, 6000, null), 
					(2, "Nike", null, "Zapatillas 2D-1", '2020-05-18', 1, 3400, null), 
					(3, "Adidas", null, "Remera adudis", '2020-05-17', 0, 2000, null), 
                    (4, "Adidas", null, "Pantalon de arquero", '2020-05-16', 1, 1600, null),
                    (5, "Adidas", null, "Botines negros like my soul", '2020-05-16', 1, 5250, null),
                    (6, "Puma", null, "Medias con un tigre", '2020-03-09', 0, 300, null),
                    (7, "Gucci", null, "Las gucci de Duki", '2020-02-26', 1, 10000, null),
                    (8, "Nike", null, "Short de Duki", '2020-05-10', 0, 8000, null),
                    (9, "Puma", null, "Campera verde militar", '2020-05-03', 1, 4700, null);

-- Data Batch 
insert into batch
			values 	(1, '2020-05-19', 20, 20, "42", 1, 1), (2, '2020-05-19', 20, 20, "40", 2, 1),
					(3, '2020-05-19', 20, 20, "M", 3, 2), (4, '2020-05-19', 20, 20, "L", 4, 2),
                    (5, '2020-05-19', 20, 20, "40", 5, 3), (6, '2020-05-19', 20, 20, "XL", 6, 3),
                    (7, '2020-05-19', 20, 20, "41", 7, 4), (8, '2020-05-19', 20, 20, "S", 8, 5),
                    (9, '2020-05-19', 20, 20, "M", 9, 6);