#SQL QUERY:

```sql
*drop schema nasa_schema;
create schema nasa_schema;
CREATE TABLE IF NOT EXISTS nasa_schema.crew_members(
    member_id INT PRIMARY KEY,
    member_name VARCHAR(20) NOT NULL,
    role_id INT CHECK (role_id < 5 AND role_id > 0),
    rank_id INT CHECK (rank_id < 5 AND rank_id > 0),
    is_ready_for_next_mission BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS nasa_schema.spaceships(
    spaceship_id INT PRIMARY KEY,
    spaceship_name VARCHAR(20) NOT NULL,
    flight_distance LONG CHECK (flight_distance > 0),
    is_ready_for_next_mission BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS nasa_schema.spaceships_crews (
    spaceship_id INT PRIMARY KEY,
    spaceship_name VARCHAR(20) NOT NULL,
    role_id INT CHECK (role_id < 5 AND role_id > 0),
    members_number INT CHECK (members_number > 0),
    FOREIGN KEY (spaceship_id) REFERENCES nasa_schema.spaceships(spaceship_id)
);


CREATE TABLE IF NOT EXISTS nasa_schema.flight_missions (
    flight_mission_id INT PRIMARY KEY,
    flight_mission_name VARCHAR(20) NOT NULL,
    start_date DATETIME DEFAULT NOW(),
    end_date DATETIME,
    flight_distance LONG CHECK (flight_distance > 0)
);

CREATE TABLE IF NOT EXISTS nasa_schema.flight_missions_crews(
    flight_mission_id INT,
    crew_member_id INT,
    PRIMARY KEY (flight_mission_id, crew_member_id),
    FOREIGN KEY (flight_mission_id) REFERENCES nasa_schema.flight_missions(flight_mission_id),
    FOREIGN KEY (crew_member_id) REFERENCES nasa_schema.crew_members(member_id)
);

CREATE TABLE IF NOT EXISTS nasa_schema.flight_missions_spaceships(
    flight_mission_id INT,
    spaceship_id INt,
    PRIMARY KEY (flight_mission_id, spaceship_id),
    FOREIGN KEY (flight_mission_id) REFERENCES nasa_schema.flight_missions(flight_mission_id),
    FOREIGN KEY (spaceship_id) REFERENCES nasa_schema.spaceships(spaceship_id),
    mission_result ENUM('CANCELLED', 'FAILED', 'PLANNED', 'IN_PROGRESS', 'COMPLETED')
)
```

#Database diagram:

![picture](/diagram.png)

