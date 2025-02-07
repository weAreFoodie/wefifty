use wefifty;

CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    pwd VARCHAR(20) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    bio TEXT NOT NULL,
    name VARCHAR(50) NOT NULL,
    gender CHAR(1) NOT NULL CHECK (gender IN ('f', 'm')),
    phone VARCHAR(20) NOT NULL UNIQUE,
    birth DATE NOT NULL,
    profile_picture VARCHAR(255) NULL,
    point INT NOT NULL DEFAULT 1000,
    PRIMARY KEY (user_id)
);

CREATE TABLE user_school (
    user_school_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    school_name VARCHAR(50) NOT NULL,
    grad_year YEAR NOT NULL,
    school_type CHAR(1) NOT NULL CHECK (school_type IN ('e', 'm', 'h', 'u')),
    PRIMARY KEY (user_school_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

CREATE TABLE friend_request (
    id INT NOT NULL AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status CHAR(1) NOT NULL CHECK (status IN ('p', 'a', 'r')),
    PRIMARY KEY (id),
    FOREIGN KEY (sender_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES user(user_id) ON DELETE CASCADE,
    CONSTRAINT unique_friend_request UNIQUE (sender_id, receiver_id)
);


-- drop table user_school;
-- drop table friend_request;
-- drop table user;
