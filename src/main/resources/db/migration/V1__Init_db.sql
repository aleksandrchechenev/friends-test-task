CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL,
    name VARCHAR(255),
    age INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_friends (
    user_id BIGINT NOT NULL,
    friends_id BIGINT NOT NULL ,
    PRIMARY KEY (friends_id, user_id)
);

ALTER TABLE IF EXISTS users_friends
    ADD CONSTRAINT user_friend_fk
    FOREIGN KEY (friends_id) REFERENCES users;

ALTER TABLE IF EXISTS users_friends
    ADD CONSTRAINT friend_user_fk
        FOREIGN KEY (user_id) REFERENCES users;