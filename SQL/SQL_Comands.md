DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;

CREATE TABLE Animals(
id INT AUTO_INCREMENT PRIMARY KEY,
class_name VARCHAR(45) NOT NULL);

INSERT INTO animals (class_name) VALUES ('packs'), ('pets');

CREATE TABLE packs (
id INT AUTO_INCREMENT PRIMARY KEY,
animal_name VARCHAR(45) NOT NULL,
class_id INT,
FOREIGN KEY (class_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO packs (animal_name, class_id)
VALUES
('Лошади', 1),
('Верблюды', 1),
('Ослы', 1);

CREATE TABLE pets (
id INT AUTO_INCREMENT PRIMARY KEY,
animal_name VARCHAR(45) NOT NULL,
class_id INT,
FOREIGN KEY (class_id) REFERENCES animals (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pets (animal_name, class_id)
VALUES
('Кошки', 2),
('Собаки', 2),
('Хомяки', 2);

CREATE TABLE cats (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dogs (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hamsters (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES pets (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES packs (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES packs (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels (  
 id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20),
type_id INT,
birthday DATE,
commands VARCHAR(70),
Foreign KEY (type_id) REFERENCES packs (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
cats (name, type_id, birthday, commands)
VALUES
('Мурзик', 1, '2019-05-15', 'Клубок, Кувырок'),
('Барсик', 1, '2022-02-22', 'Клубок, Кувырок, апорт'),  
 ('Персик', 1, '2020-012-30', 'Клубок, Кувырок, мяу');

INSERT INTO
dogs (name, type_id, birthday, commands)
VALUES
('Полкан', 2, '2010-01-01', 'Сидеть, Стоять, Лежать'),
('Бобик', 2, '2008-11-10', 'Сидеть, Лапу'),  
 ('Мухтар', 2, '2009-11-21', 'Фу, Бе, Усни');

INSERT INTO
hamsters (name, type_id, birthday, commands)
VALUES
('Кузя', 3, '2022-03-13', 'Пук, ТУк'),
('Маня', 3, '2003-08-31', 'Грызть, Спать');

INSERT INTO
horses (name, type_id, birthday, commands)
VALUES
('Буран', 1, '2015-07-21', 'Торопись, ПРРРР'),
('Ночка', 1, '2014-05-05', 'Стоять'),  
 ('Плотва', 1, '2016-02-29', 'Живей плотва');

INSERT INTO
donkeys (name, type_id, birthday, commands)
VALUES
('ИА', 3, '2007-09-18', 'Улыбнись'),
('Ослодракон', 3, '2014-01-23', 'Прыгай, Грузись');

INSERT INTO
camels (name, type_id, birthday, commands)
VALUES
('Квазимода', 3, '2016-11-03', 'Приляг, топай'),
('Электрожираф', 3, '2008-12-12', 'Не плюйся, Турбо'),
('Верблюди', 3, '2015-08-14', 'Лежать, Принеси палку');

DELETE FROM packs WHERE id = 2;

CREATE TABLE horses_and_donkeys
SELECT _ FROM horses
UNION
SELECT _ FROM donkeys;

DROP TABLE IF EXISTS young_anim;
CREATE TEMPORARY TABLE young_anim AS
SELECT _ FROM cats
UNION
SELECT _ FROM dogs
UNION
SELECT _ FROM hamsters
UNION
SELECT _ FROM horses
UNION
SELECT _ FROM donkeys
UNION
SELECT _ FROM camels;

DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals
SELECT
name, type_id, birthday, commands, TIMESTAMPDIFF(MONTH, birthday, CURDATE()) AS age_in_month
FROM
young_anim
WHERE birthday BETWEEN ADDDATE(CURDATE(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

DROP TABLE IF EXISTS all_animals;
CREATE TABLE all_animals

SELECT
dg.name,
p.animal_name,
dg.birthday,
dg.commands,
a.class_name
FROM dogs dg
LEFT JOIN pets p ON p.id = dg.type_id
LEFT JOIN animals a ON a.id = p.class_id
UNION
SELECT
ct.name,
p.animal_name,
ct.birthday,
ct.commands,
a.class_name
FROM cats ct
LEFT JOIN pets p ON p.id = ct.type_id
LEFT JOIN animals a ON a.id = p.class_id
UNION
SELECT
hm.name,
p.animal_name,
hm.birthday,
hm.commands,
a.class_name
FROM hamsters hm
LEFT JOIN pets p ON p.id = hm.type_id
LEFT JOIN animals a ON a.id = p.class_id
UNION
SELECT
hr.name,
pa.animal_name,
hr.birthday,
hr.commands,
a.class_name
FROM horses hr
LEFT JOIN packs pa ON pa.id = hr.type_id
LEFT JOIN animals a ON a.id = pa.class_id
UNION
SELECT
dk.name,
pa.animal_name,
dk.birthday,
dk.commands,
a.class_name
FROM donkeys dk
LEFT JOIN packs pa ON pa.id = dk.type_id
LEFT JOIN animals a ON a.id = pa.class_id
UNION
SELECT
cm.name,
pa.animal_name,
cm.birthday,
cm.commands,
a.class_name
FROM camels cm
LEFT JOIN packs pa ON pa.id = cm.type_id
LEFT JOIN animals a ON a.id = pa.class_id
