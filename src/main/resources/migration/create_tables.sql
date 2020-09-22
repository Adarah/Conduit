-- This extension is used for email validation
-- source: https://stackoverflow.com/questions/4908211/postgres-function-to-validate-email-address
CREATE EXTENSION IF NOT EXISTS citext;
CREATE DOMAIN email AS citext
    CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS
    conduit_user,
    article,
    tag,
    article_tag,
    user_article_favorite,
    user_follow CASCADE;

CREATE TABLE IF NOT EXISTS conduit_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email email NOT NULL UNIQUE,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    bio TEXT,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS article (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    slug VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    author_id UUID NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
        REFERENCES conduit_user(id)
        ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION process_update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_update_timestamp
BEFORE UPDATE ON article
FOR EACH ROW
EXECUTE PROCEDURE process_update_timestamp();

CREATE TABLE IF NOT EXISTS tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS article_tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    article_id UUID NOT NULL,
    tag_id UUID NOT NULL,
    CONSTRAINT fk_article
        FOREIGN KEY (article_id)
        REFERENCES article(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_tag
        FOREIGN KEY (tag_id)
        REFERENCES tag(id)
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS user_article_favorite(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    article_id UUID NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES conduit_user(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_article
        FOREIGN KEY(article_id)
        REFERENCES article(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_follow(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    target_id UUID NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES conduit_user(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_target
        FOREIGN KEY (target_id)
        REFERENCES conduit_user(id)
        ON DELETE CASCADE
);
