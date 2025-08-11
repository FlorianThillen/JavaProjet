-- Insert 10 values into brand
INSERT INTO brand (name, waranty_duration) VALUES
('BrandA', 24),
('BrandB', 12),
('BrandC', 18),
('BrandD', 36),
('BrandE', 6),
('BrandF', 24),
('BrandG', 12),
('BrandH', 18),
('BrandI', 36),
('BrandJ', 6);

-- Insert 10 values into localite
INSERT INTO localite (postal_code, name) VALUES
(1000, 'CityA'),
(1001, 'CityB'),
(1002, 'CityC'),
(1003, 'CityD'),
(1004, 'CityE'),
(1005, 'CityF'),
(1006, 'CityG'),
(1007, 'CityH'),
(1008, 'CityI'),
(1009, 'CityJ');

-- Insert 10 values into station
INSERT INTO station (station_number, name, street, street_number, postal_code, local_name) VALUES
(1, 'Station1', 'Main St', 10, 1000, 'CityA'),
(2, 'Station2', 'Second St', 20, 1001, 'CityB'),
(3, 'Station3', 'Third St', 30, 1002, 'CityC'),
(4, 'Station4', 'Fourth St', 40, 1003, 'CityD'),
(5, 'Station5', 'Fifth St', 50, 1004, 'CityE'),
(6, 'Station6', 'Sixth St', 60, 1005, 'CityF'),
(7, 'Station7', 'Seventh St', 70, 1006, 'CityG'),
(8, 'Station8', 'Eighth St', 80, 1007, 'CityH'),
(9, 'Station9', 'Ninth St', 90, 1008, 'CityI'),
(10, 'Station10', 'Tenth St', 100, 1009, 'CityJ');

-- Insert 10 values into mechanic
INSERT INTO mechanic (badge_id, first_name, last_name, email, phone_number) VALUES
(101, 'John', 'Doe', 'john.doe@example.com', 111111111),
(102, 'Jane', 'Smith', 'jane.smith@example.com', 222222222),
(103, 'Mike', 'Brown', 'mike.brown@example.com', 333333333),
(104, 'Lisa', 'White', 'lisa.white@example.com', 444444444),
(105, 'Tom', 'Green', 'tom.green@example.com', 555555555),
(106, 'Emma', 'Black', 'emma.black@example.com', 666666666),
(107, 'Chris', 'Blue', 'chris.blue@example.com', 777777777),
(108, 'Anna', 'Gray', 'anna.gray@example.com', 888888888),
(109, 'James', 'Yellow', 'james.yellow@example.com', 999999999),
(110, 'Olivia', 'Red', 'olivia.red@example.com', 101010101);

-- Insert 10 values into repair_status
INSERT INTO repair_status (libelle) VALUES
('Pending'),
('In Progress'),
('Completed'),
('Cancelled'),
('Delayed'),
('Approved'),
('Rejected'),
('On Hold'),
('Under Review'),
('Closed');

-- Insert 10 values into subscription
INSERT INTO subscription (card_number, price, date, caution_paid, subscription_paid) VALUES
(10001, 50.0, '2025-01-01', TRUE, TRUE),
(10002, 40.0, '2025-02-01', FALSE, TRUE),
(10003, 60.0, '2025-03-01', TRUE, FALSE),
(10004, 55.0, '2025-04-01', TRUE, TRUE),
(10005, 45.0, '2025-05-01', FALSE, FALSE),
(10006, 65.0, '2025-06-01', TRUE, TRUE),
(10007, 50.0, '2025-07-01', TRUE, TRUE),
(10008, 55.0, '2025-08-01', FALSE, TRUE),
(10009, 60.0, '2025-09-01', TRUE, FALSE),
(10010, 45.0, '2025-10-01', TRUE, TRUE);

-- Insert 10 values into member
INSERT INTO member (national_number, first_name, last_name, email, phone_number, got_discount, street, street_number, sub_id, postal_code, name) VALUES
('NAT001', 'Alice', 'Martin', 'alice.martin@example.com', 123456789, TRUE, 'Oak St', 10, 10001, 1000, 'CityA'),
('NAT002', 'Bob', 'Lee', 'bob.lee@example.com', 234567890, FALSE, 'Pine St', 20, 10002, 1001, 'CityB'),
('NAT003', 'Carol', 'King', 'carol.king@example.com', 345678901, TRUE, 'Maple St', 30, 10003, 1002, 'CityC'),
('NAT004', 'David', 'Scott', 'david.scott@example.com', 456789012, FALSE, 'Elm St', 40, 10004, 1003, 'CityD'),
('NAT005', 'Eva', 'Wong', 'eva.wong@example.com', 567890123, TRUE, 'Birch St', 50, 10005, 1004, 'CityE'),
('NAT006', 'Frank', 'Hill', 'frank.hill@example.com', 678901234, FALSE, 'Cedar St', 60, 10006, 1005, 'CityF'),
('NAT007', 'Grace', 'Adams', 'grace.adams@example.com', 789012345, TRUE, 'Spruce St', 70, 10007, 1006, 'CityG'),
('NAT008', 'Henry', 'Baker', 'henry.baker@example.com', 890123456, FALSE, 'Fir St', 80, 10008, 1007, 'CityH'),
('NAT009', 'Ivy', 'Clark', 'ivy.clark@example.com', 901234567, TRUE, 'Ash St', 90, 10009, 1008, 'CityI'),
('NAT010', 'Jack', 'Davis', 'jack.davis@example.com', 123123123, FALSE, 'Poplar St', 100, 10010, 1009, 'CityJ');

-- Insert 10 values into bike
INSERT INTO bike (serial_number, is_electric, buying_date, battery_level, nb_kilometer, station_id, brand_name) VALUES
(1001, TRUE, '2024-01-01', 0.85, 100, 1, 'BrandA'),
(1002, FALSE, '2024-02-01', 0.00, 150, 2, 'BrandB'),
(1003, TRUE, '2024-03-01', 0.60, 200, 3, 'BrandC'),
(1004, FALSE, '2024-04-01', 0.00, 50, 4, 'BrandD'),
(1005, TRUE, '2024-05-01', 0.90, 80, 5, 'BrandE'),
(1006, FALSE, '2024-06-01', 0.00, 300, 6, 'BrandF'),
(1007, TRUE, '2024-07-01', 0.75, 60, 7, 'BrandG'),
(1008, FALSE, '2024-08-01', 0.00, 120, 8, 'BrandH'),
(1009, TRUE, '2024-09-01', 0.55, 140, 9, 'BrandI'),
(1010, FALSE, '2024-10-01', 0.00, 90, 10, 'BrandJ');

-- Insert 10 values into rental
INSERT INTO rental (id, start_date, return_date, comment, had_issue, bike_id, sub_id) VALUES
(1, '2025-07-01', '2025-07-05', 'No issues', FALSE, 1001, 10001),
(2, '2025-07-03', '2025-07-07', 'Brake problem', TRUE, 1002, 10002),
(3, '2025-07-05', '2025-07-10', NULL, FALSE, 1003, 10003),
(4, '2025-07-08', '2025-07-12', 'Battery low', TRUE, 1004, 10004),
(5, '2025-07-10', '2025-07-15', NULL, FALSE, 1005, 10005),
(6, '2025-07-13', '2025-07-18', 'Chain issue', TRUE, 1006, 10006),
(7, '2025-07-16', '2025-07-20', NULL, FALSE, 1007, 10007),
(8, '2025-07-18', '2025-07-22', 'Flat tire', TRUE, 1008, 10008),
(9, '2025-07-20', '2025-07-25', NULL, FALSE, 1009, 10009),
(10, '2025-07-23', '2025-07-28', 'Seat loose', TRUE, 1010, 10010);

-- Insert 10 values into repair
INSERT INTO repair (cost, date, libelle, serial_number, mechanic_id) VALUES
(100.00, '2025-07-06', 'Completed', 1002, 101),
(50.00, '2025-07-09', 'Completed', 1004, 102),
(75.50, '2025-07-14', 'In Progress', 1006, 103),
(30.00, '2025-07-19', 'Pending', 1008, 104),
(120.00, '2025-07-21', 'Completed', 1010, 105),
(90.00, '2025-07-24', 'Approved', 1001, 106),
(60.00, '2025-07-26', 'Rejected', 1003, 107),
(80.00, '2025-07-28', 'Cancelled', 1005, 108),
(110.00, '2025-07-30', 'Under Review', 1007, 109),
(40.00, '2025-08-01', 'On Hold', 1009, 110);
