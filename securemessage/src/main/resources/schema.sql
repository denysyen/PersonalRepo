CREATE SCHEMA IF NOT EXISTS securemessage;

SET NAMES 'UTF8MB4';
-- SET TIME_ZONE = 'EU/Western';
-- SET TIME_ZONE = '0:00';

USE securemessage;

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(100) DEFAULT NULL,
    address      VARCHAR(255) DEFAULT NULL,
    phone        VARCHAR(30) DEFAULT NULL,
    title        VARCHAR(50) DEFAULT NULL,
    bio          VARCHAR(50) DEFAULT NULL,
    enable       BOOLEAN DEFAULT FALSE,
    non_locked   BOOLEAN DEFAULT TRUE,
    using_mfa    BOOLEAN DEFAULT FALSE,
    image_url    VARCHAR(255) DEFAULT NULL,
    CONSTRAINT   UQ_Users_Email UNIQUE (email)

);

DROP TABLE IF EXISTS Roles;

CREATE TABLE Roles (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    permission   VARCHAR(255) NOT NULL,
    CONSTRAINT   UQ_Roles_Name UNIQUE (name)
);

DROP TABLE IF EXISTS UserRoles;
-- this is need it ot avoid REFERENCES errors
-- while using a table's field that was already
-- dropped
ALTER TABLE UserRoles DROP FOREIGN KEY user_id;

CREATE TABLE UserRoles (
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT UNSIGNED NOT NULL,
    role_id       BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id)  REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id)  REFERENCES Roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT  UQ_UserRoles_User_id UNIQUE (user_id)
);


DROP TABLE IF EXISTS Events;

CREATE TABLE Events (
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type          VARCHAR(50) NOT NULL CHECK( type IN ('LOGIN_ATTEMPT', 'LOGIN_ATTEMPT_FAILURE', 'LOGIN_ATTEMPT_SUCCESS', 'PROFILE_UPDATE','PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE','ACCOUNT_SETTINGS_UPDATE', 'PASSWORD_UPDATE')),
    description  VARCHAR(255) NOT NULL,
    CONSTRAINT  UQ_Events_Type UNIQUE (type)
);

DROP TABLE IF EXISTS UserEvents;

CREATE TABLE UserEvents (
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id        BIGINT UNSIGNED NOT NULL,
    event_id       BIGINT UNSIGNED NOT NULL,
    device         VARCHAR(100) NOT NULL,
    ip_address     VARCHAR(100) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id)  REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id)  REFERENCES Events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE IF EXISTS AccountVerifications;

CREATE TABLE AccountVerifications (
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id        BIGINT UNSIGNED NOT NULL,
    url            VARCHAR(255) NOT NULL,
    -- date         DATETIME NOT NULL,
    FOREIGN KEY (user_id)  REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT  UQ_AccountVerifications_User_id UNIQUE (user_id),
    CONSTRAINT  UQ_AccountVerifications_Url UNIQUE (url)

);

DROP TABLE IF EXISTS ResetPasswordVerifications;

CREATE TABLE ResetPasswordVerifications (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id           BIGINT UNSIGNED NOT NULL,
    url               VARCHAR(255) NOT NULL,
    expiration_date   DATETIME NOT NULL,
    FOREIGN KEY (user_id)  REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT  UQ_ResetPasswordVerifications_User_id UNIQUE (user_id),
    CONSTRAINT  UQ_ResetPasswordVerifications_Url UNIQUE (url)

);

DROP TABLE IF EXISTS TwoFactorVerifications;

CREATE TABLE TwoFactorVerifications (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id           BIGINT UNSIGNED NOT NULL,
    code               VARCHAR(10) NOT NULL,
    expiration_date   DATETIME NOT NULL,
    FOREIGN KEY (user_id)  REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT  UQ_TwoFactorVerifications_User_id UNIQUE (user_id),
    CONSTRAINT  UQ_TwoFactorVerifications_Code UNIQUE (code)

);