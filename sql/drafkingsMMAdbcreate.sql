drop table if exists lineup            cascade;
drop table if exists event             cascade;
drop table if exists fight             cascade;
drop table if exists fighter           cascade;


CREATE TABLE lineup (
    lineup_id serial NOT NULL,
    /*
    fighter_1_name varchar(64) NOT NULL,
    fighter_2_name varchar(64) NOT NULL,
    fighter_3_name varchar(64) NOT NULL,
    fighter_4_name varchar(64) NOT NULL,
    fighter_5_name varchar(64) NOT NULL,
    fighter_6_name varchar(64) NOT NULL,
    */
    event_id integer NOT NULL,
    fighter_1_id integer NOT NULL,
    fighter_2_id integer NOT NULL,
    fighter_3_id integer NOT NULL,
    fighter_4_id integer NOT NULL,
    fighter_5_id integer NOT NULL,
    fighter_6_id integer NOT NULL,
    average_points_for double precision,
    total_points_for double precision,
    average_salary double precision,
    total_salary int,
    /*total_score double precision,*/
    CONSTRAINT pk_lineup_id PRIMARY KEY (lineup_id)
);

CREATE TABLE event (
    event_id serial NOT NULL,
    event_name varchar(64) NOT NULL,
    event_date date NOT NULL,
    /*
    money_cutoff_points double precision,
    top_score double precision,
    
    event_location varchar(64) NOT NULL,
    */
    CONSTRAINT pk_event_id PRIMARY KEY (event_id)
);
/*
CREATE TABLE fight (
    fight_id serial NOT NULL,
    event_id int NOT NULL,
    winner_id int NOT NULL,
    loser_id int NOT NULL,
    method_of_victory varchar(64), 
    round int,
    CONSTRAINT pk_fight_id PRIMARY KEY (fight_id)
);
*/
CREATE TABLE fighter (
    fighter_id serial NOT NULL,
    first_name varchar(64) NOT NULL,
    last_name varchar(64) NOT NULL,
    salary int NOT NULL,
    average_points double precision,
    
    
    /*dob date NOT NULL,
    training_location varchar(64) NOT NULL,
    */
    CONSTRAINT pk_fighter_id PRIMARY KEY (fighter_id)
);


ALTER TABLE lineup
ADD FOREIGN KEY(event_id)
REFERENCES event(event_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_1_id)
REFERENCES fighter(fighter_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_2_id)
REFERENCES fighter(fighter_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_3_id)
REFERENCES fighter(fighter_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_4_id)
REFERENCES fighter(fighter_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_5_id)
REFERENCES fighter(fighter_id);

ALTER TABLE lineup
ADD FOREIGN KEY(fighter_6_id)
REFERENCES fighter(fighter_id);
/*
ALTER TABLE fight
ADD FOREIGN KEY(event_id)
REFERENCES event(event_id);

ALTER TABLE fight
ADD FOREIGN KEY(winner_id)
REFERENCES fighter(fighter_id);

ALTER TABLE fight
ADD FOREIGN KEY(loser_id)
REFERENCES fighter(fighter_id);
*/
