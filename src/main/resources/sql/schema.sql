-- Drop all tables
DROP TABLE IF EXISTS carpato_trip_schema.clubs CASCADE;
DROP TABLE IF EXISTS carpato_trip_schema.events CASCADE;
DROP TABLE IF EXISTS carpato_trip_schema.roles CASCADE;
DROP TABLE IF EXISTS carpato_trip_schema.users CASCADE;
DROP TABLE IF EXISTS carpato_trip_schema.users_roles CASCADE;

-- SCHEMA: carpato_trip_schema
DROP SCHEMA IF EXISTS carpato_trip_schema; 
CREATE SCHEMA IF NOT EXISTS carpato_trip_schema
AUTHORIZATION yymuukyvamvfdj;

-- TABLE: clubs
CREATE TABLE IF NOT EXISTS carpato_trip_schema.clubs (
    id SERIAL PRIMARY KEY,
    club_title character(255),
    club_content character(255),
    created_on TIMESTAMP,
    updated_on TIMESTAMP,
    club_photo_url character(255),
    created_by BIGINT
);
--TABLESPACE pg_default;
ALTER TABLE IF EXISTS carpato_trip_schema.clubs
    OWNER to yymuukyvamvfdj;

-- TABLE: events
CREATE TABLE IF NOT EXISTS carpato_trip_schema.events (
    id SERIAL PRIMARY KEY,
    event_name character(255),
    created_on TIMESTAMP,
    updated_on TIMESTAMP,
    event_start_time TIMESTAMP,
    event_end_time TIMESTAMP,
    event_type character(255),
    event_photo_url character(255),
    club_id BIGINT
);
--TABLESPACE pg_default;
ALTER TABLE IF EXISTS carpato_trip_schema.events
    OWNER to yymuukyvamvfdj;
    
--TABLE: roles
CREATE TABLE IF NOT EXISTS carpato_trip_schema.roles (
    id SERIAL PRIMARY KEY,
    role_name character(255)
);
--TABLESPACE pg_default;
ALTER TABLE IF EXISTS carpato_trip_schema.roles
    OWNER to yymuukyvamvfdj;

-- TABLE: users
CREATE TABLE IF NOT EXISTS carpato_trip_schema.users (
    id SERIAL PRIMARY KEY,
    first_name character(255),
    last_name character(255),
    user_email character(320),
    user_password character(320)
);
--TABLESPACE pg_default;
ALTER TABLE IF EXISTS carpato_trip_schema.users
    OWNER to yymuukyvamvfdj;
    
-- TABLE: users_roles - this is a table for implementation MANY TO MANY relationship
CREATE TABLE IF NOT EXISTS carpato_trip_schema.users_roles (
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) 
    REFERENCES carpato_trip_schema.users(id)
    ON DELETE CASCADE,
    FOREIGN KEY (role_id) 
    REFERENCES carpato_trip_schema.roles(id)
    ON DELETE CASCADE
);
--TABLESPACE pg_default;
ALTER TABLE IF EXISTS carpato_trip_schema.users_roles
    OWNER to yymuukyvamvfdj;