        drop table if exists ticket CASCADE;
        drop table if exists passenger CASCADE;
        drop table if exists users_roles CASCADE;
        drop table if exists roles CASCADE;
        drop table if exists users CASCADE;
        drop table if exists client CASCADE;
        drop table if exists seat CASCADE;
        drop table if exists flight CASCADE;
        drop table if exists plane CASCADE;
        drop table if exists air_company CASCADE;
        drop table if exists address CASCADE;

        CREATE TABLE  client (
            id bigint NOT NULL AUTO_INCREMENT,
            first_name varchar(30) not null,
            second_name varchar(30) not null,
            email varchar(255) not null,
            phone_number int DEFAULT 0,
            primary key(id)
        );

        CREATE TABLE users (
            id bigint NOT NULL AUTO_INCREMENT,
            login varchar(255) NOT NULL,
            password varchar(255) NOT NULL,
            enabled bool DEFAULT true,
            client_id bigint,
            FOREIGN KEY (client_id) REFERENCES client(id),
            primary key(id)
        );

        CREATE TABLE roles (
            id bigint NOT NULL AUTO_INCREMENT,
            role_name varchar(255) NOT NULL,
            primary key(id)
        );

        CREATE TABLE users_roles (
            user_id bigint NOT NULL,
            role_id bigint NOT NULL,
            PRIMARY KEY (user_id, role_id),
            FOREIGN KEY (user_id) REFERENCES users (id),
            FOREIGN KEY (role_id) REFERENCES roles (id)
        );

        CREATE TABLE address(
            id bigint NOT NULL AUTO_INCREMENT,
            city varchar(255),
            country varchar(255),
            number_cabinet smallint DEFAULT 0,
            number_flat smallint DEFAULT 0,
            number_house varchar(40) DEFAULT null,
            street varchar(255),
            primary key(id)
        );

        CREATE TABLE air_company(
            id bigint NOT NULL AUTO_INCREMENT,
            name_company varchar(255) NOT NULL,
            phone_number int DEFAULT 0,
            address_id bigint,
            FOREIGN KEY (address_id) REFERENCES address(id),
            primary key(id)
        );

        CREATE TABLE plane(
            id bigint NOT NULL AUTO_INCREMENT,
            invertor_number int default 0,
            name_plane varchar(255) default null,
            name_pilot varchar(255) default null,
            quantity_seats int default 0,
            seats_in_line int default 0,
            quantity_lines int default 0,
            company_id bigint,
            FOREIGN KEY (company_id) REFERENCES air_company(id),
            primary key(id)
        );

        CREATE TABLE flight(
            id bigint NOT NULL AUTO_INCREMENT,
            number_flight varchar(255) default null,
            arrive_city varchar(50) default null,
            arrive_date_time timestamp,
            duration_flight int default 0,
            departure_city varchar(50) default null,
            departure_date_time timestamp,
            plane_id bigint,
            FOREIGN KEY (plane_id) REFERENCES plane(id),
            primary key(id)
        );

        CREATE TABLE seat(
            id bigint NOT NULL AUTO_INCREMENT,
            booked bool default false,
            number_seat varchar(15) not null,
            flight_id bigint,
            FOREIGN KEY (flight_id) REFERENCES flight(id),
            primary key(id)
        );

        CREATE TABLE PASSENGER(
            id bigint NOT NULL AUTO_INCREMENT,
            first_name varchar(30) not null,
            second_name varchar(30) not null,
            passport_number varchar(30) not null,
            phone_number int DEFAULT 0,
            address_id bigint,
            client_id bigint,
            FOREIGN KEY (address_id) REFERENCES address(id),
            FOREIGN KEY (client_id) REFERENCES client(id),
            primary key(id)
        );

        CREATE TABLE ticket(
            id bigint NOT NULL AUTO_INCREMENT,
            booked_date_time timestamp DEFAULT CURRENT_TIMESTAMP,
            booked bool default true,
            passenger_id bigint,
            flight_id bigint,
            seat_id bigint,
            FOREIGN KEY (seat_id) REFERENCES seat(id),
            FOREIGN KEY (flight_id) REFERENCES flight(id),
            FOREIGN KEY (passenger_id) REFERENCES passenger(id),
            primary key(id)
        );

        INSERT INTO client (first_name, second_name, email, phone_number)
        VALUES ('sasha', 'sakahruk', 'sakharukaliaksandr@gmail.com', 292001211);
        INSERT INTO client (first_name, second_name, email, phone_number)
        VALUES ('petia', 'olejnik', 'olejnik@gmail.com', 292112121);
        INSERT INTO client (first_name, second_name, email, phone_number)
        VALUES ('sasha', 'kazak', 'kazak@gmail.com', 297330135);
        INSERT INTO client (first_name, second_name, email, phone_number)
        VALUES ('roma', 'salapura', 'salapurar@gmail.com', 297229238);

        INSERT INTO roles (role_name) VALUES ('READER');
        INSERT INTO roles (role_name) VALUES ('EDITOR');
        INSERT INTO roles (role_name) VALUES ('AUTHOR');

        INSERT INTO users (login, password, client_id)
        VALUES ('admin', '$2a$10$73dQALAYAHPjOZdIqObi4u5jUZ2Tqg2Mzr4pPk5AHRaKFm/jBEhwK', 1);
        INSERT INTO users (login, password, client_id)
        VALUES ('editor', '$2a$10$Y4uw8pVXJ19lvYKUHehyuunVjPBdaU7OEOqgxKBKgVGbWSb.rTYrC', 2);
        INSERT INTO users (login, password, client_id)
        VALUES ('sasha', '$2a$10$dOSLyd3hnCQTqEn1qOxxCudCzFWw9Mmrw7wqwYuPRHVGXvCwCp5NK', 3);
        INSERT INTO users (login, password, client_id)
        VALUES ('roma', '$2a$10$n7iTfbubVVoJn/OmEpbC2el.tHTYjeAHHB8jrQecxbUHe5lalmvR.', 4);

        INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
        INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
        INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
        INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
        INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
        INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
        INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);

        INSERT INTO address(country, city, street, number_house)
        values('BELARUS', 'MINSK', 'NEMIGA', '14A');
        INSERT INTO address(country, city, street, number_house)
        values('RUSSIA', 'MOSCOW', 'ARBAT', '1');
        INSERT INTO address(country, city, street, number_house, number_flat)
        values('BELARUS', 'BREST', 'FRUNZE', '15', 55);
        INSERT INTO address(country, city, street, number_house, number_flat)
        values('BELARUS', 'MINSK', 'SERENIVAI', '11', 105);
        INSERT INTO address(country, city, street, number_house, number_flat)
        values('BELARUS', 'BARANOVICHI', 'VLKSM', '8', 1);
        INSERT INTO address(country, city, street, number_house, number_flat)
        values('BELARUS', 'GRODNO', 'MOSKOVKAJA', '222', 21);
        INSERT INTO address(country, city, street, number_house, number_flat)
        values('BELARUS', 'MINSK', 'SERENIVAI', '11', 105);
        INSERT INTO address(country, city, street, number_house)
        values('BELARUS', 'BREST', 'proezd BEREZOVSKI', '10');


        INSERT INTO air_company(name_company, phone_number, address_id)
        values('BELAVIA',  2232323, 1);
        INSERT INTO air_company(name_company, phone_number, address_id)
        values('AEROFLOT',  3234455, 2);

        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(234567, 'AH 24', 'Nikita', 48, 4, 12, 1);
        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(1122423, 'AH 24', 'Ruslan', 48, 4, 12, 1);
        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(7777771, 'AH 24', 'Tereshkov', 48, 4, 12, 1);
        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(555555, 'AH 24', 'Peskov', 48, 4, 12, 2);
        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(333333, 'AH 24', 'Zhyrynovski', 48, 4, 12, 2);
        INSERT INTO plane(invertor_number, name_plane, name_pilot, quantity_seats, seats_in_line, quantity_lines, company_id)
        values(111111, 'AH 24', 'Mishustin', 48, 4, 12, 2);

        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
        values('FK256', 'BREST', '2021-11-01 14:00:00', 50, 'GOMEL', '2021-11-01 14:50:00', 1);
        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
        values('FK333', 'BREST', '2021-11-01 14:00:00', 45, 'MINSK', '2021-11-01 14:45:00', 2);
        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
        values('FK123', 'MINSK', '2021-11-01 16:00:00', 60, 'MOSCOW', '2021-11-01 17:00:00', 3);
        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
        values('FK777', 'BREST', '2021-11-01 19:00:00', 80, 'MOSCOW', '2021-11-01 20:20:00', 4);
        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
         values('FK999', 'BREST', '2021-11-01 10:00:00', 80, 'MINSK', '2021-11-01 11:00:00', 5);
        INSERT INTO flight(number_flight, departure_city, departure_date_time, duration_flight, arrive_city, arrive_date_time, plane_id)
        values('FK567', 'MINSK', '2021-11-01 20:00:00', 80, 'MOSCOW', '2021-11-01 21:20:00', 6);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 1);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 1);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '7B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 2);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 2);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '7B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 3);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 3);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '4D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '7B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 4);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 4);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 5);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 5);

        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '1C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '1D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '2C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '2D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
                                values(false, '3A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '3C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '3D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '4D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '5C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '5D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '6C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '6D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '7D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '8C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '8D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '9C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '9D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '10C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '10D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '11D', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12A', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12B', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(true, '12C', 6);
        INSERT INTO seat(booked, number_seat, flight_id)
        values(false, '12D', 6);

        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Vadim', 'Kazak', 'AB 3234455', 297903271, 3, 3);
        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Maksim', 'Fedeev', 'AB 3457901', 296330195, 4, 3);
        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Semen', 'Dron', 'AB 6540394', 292250081, 5, 3);
        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Ruslan', 'Zavizenec', 'AB 3840101', 295643201, 6, 4);
        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Zaur', 'Martirosian', 'AB 3110401', 291230673, 7, 4);
        INSERT INTO PASSENGER(first_name, second_name, passport_number, phone_number, address_id, client_id)
        values('Igor', 'Olechovich', 'AB 5349591', 296446666, 8, 4);

        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(1, 1, 1);
        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(2, 2, 86);
        INSERT INTO ticket( passenger_id, flight_id, seat_id)
        values(3, 2, 87);
        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(4, 4, 170);
        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(5, 4, 173);
        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(6, 2, 88);
        INSERT INTO ticket(passenger_id, flight_id, seat_id)
        values(6, 3, 128);

