
public class Main {
    public static void main(String[] args) {
        Connection1 connection1 = new Connection1();
        connection1.chooseActionMenu(connection1);

            /*      Скрипт таблицы
                    create table "QA_Practice_2".countries (
                            id int unique not null primary key ,
                    name varchar unique not null
            );
                    create table "QA_Practice_2".airports (
                            id int unique not null primary key ,
                    city varchar not null ,
                            country_id int references "QA_Practice_2".countries(id)
            );
                    create table "QA_Practice_2".flights (
                            id int unique not null primary key ,
                    plane_model varchar not null ,
                            departure_time timestamp not null ,
                            came_from int  unique references "QA_Practice_2".airports(id),
                            arrival int unique references "QA_Practice_2".airports(id),
                            duration varchar,
                            seats int not null
            );
                    create table "QA_Practice_2".passengers(
                            id_passport varchar not null unique primary key ,
                            inn int unique not null ,
                            fio varchar unique not null ,
                            gender varchar not null ,
                            country_id int not null references "QA_Practice_2".countries(id)
            );
                    create table "QA_Practice_2".tickets(
                            ticket_id int unique not null ,
                            id_passenger varchar not null unique references "QA_Practice_2".passengers(id_passport) ,
                            flight_number int not null references "QA_Practice_2".flights(id),
                            register_time timestamp not null
            );
            */
    }
}
