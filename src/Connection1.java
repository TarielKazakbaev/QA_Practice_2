import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Connection1 {
    boolean exit = true;
    Countries countries = new Countries();
    Airports airports = new Airports();
    Flights flights = new Flights();
    Passengers passengers = new Passengers();
    Tickets tickets = new Tickets();

    Scanner scan = new Scanner(System.in);

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "19041988";

    public Connection connect() {
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertCountries(Countries countries) {
        String SQL = "INSERT INTO \"QA_Practice_2\".countries" +
                "( id,name )" +
                " VALUES ( ?, ?); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, countries.getId());
            stmt.setString(2, countries.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAirports(Airports airports) {
        String SQL = "INSERT INTO \"QA_Practice_2\".airports" +
                "( id,city,country_id )" +
                " VALUES ( ?, ?,? ); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, airports.getId());
            stmt.setString(2, airports.getCity());
            stmt.setInt(3, airports.getCountry_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPassengers(Passengers passengers) {
        String SQL = "INSERT INTO \"QA_Practice_2\".passengers" +
                "( id_passport,inn,fio,gender,country_id )" +
                " VALUES ( ?, ?,?,?,? ); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, passengers.getId_passport());
            stmt.setInt(2, passengers.getInn());
            stmt.setString(3, passengers.getFio());
            stmt.setString(4, passengers.getGender());
            stmt.setInt(5, passengers.getCountry_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFlights(Flights flights) {
        String SQL = "INSERT INTO \"QA_Practice_2\".flights" +
                "( id,plane_model,departure_time,came_from,arrival,duration,seats )" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ? ); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, flights.getId());
            stmt.setString(2, flights.getPlane_model());
            stmt.setTimestamp(3, (Timestamp) flights.getDeparture_time());
            stmt.setInt(4, flights.getCame_from());
            stmt.setInt(5, flights.getArrival());
            stmt.setString(6, flights.getDuration());
            stmt.setInt(7, flights.getSeats());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTickets(Tickets tickets) {
        String SQL = "INSERT INTO \"QA_Practice_2\".tickets" +
                "( ticket_id,id_passenger,flight_number,register_time )" +
                " VALUES ( ?, ?,?,? ); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, tickets.getTicked_id());
            stmt.setString(2, tickets.getId_passenger());
            stmt.setInt(3, tickets.getFlight_number());
            stmt.setTimestamp(4, (Timestamp) tickets.getRegister_time());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCountries(Countries countries) {
        String SQL = "SELECT * FROM \"QA_Practice_2\".countries";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("name") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAirports(Airports airports) {
        String SQL = "select * from \"QA_Practice_2\".airports";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("city") + "|" + rs.getString("country_id") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectPassengers(Passengers passengers) {
        String SQL = "select * from \"QA_Practice_2\".passengers";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getString("id_passport") + "|" + rs.getString("inn") + "|" + rs.getString("fio") + "|" + rs.getString("gender") + "|" + rs.getString("country_id") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectFlights(Flights flights) {
        String SQL = "select * from \"QA_Practice_2\".flights";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("plane_model") + "|" + rs.getString("departure_time")
                        + "|" + rs.getString("came_from") + "|" + rs.getString("arrival") + "|" + "|" +
                        rs.getString("duration") + "|" + rs.getString("seats") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectTickets(Tickets tickets) {
        String SQL = "select * from \"QA_Practice_2\".tickets";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("ticket_id") + "|" + rs.getString("id_passenger") + "|" + rs.getString("flight_number")
                        + "|" + rs.getString("register_time") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCountriesId(Countries countries) {
        String SQL = "select * from \"QA_Practice_2\".countries where id =?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, countries.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("name") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectAirportsId(Airports airports) {
        String SQL = "select * from \"QA_Practice_2\".airports where id =?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, airports.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("city") + "|" + rs.getString("country_id") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectPassengersId(Passengers passengers) {
        String SQL = "select * from \"QA_Practice_2\".passengers where passport_id =?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, passengers.getId_passport());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("passport_id") + "|" + rs.getString("inn") + "|" + rs.getString("fio") + "|" + rs.getString("gender") + "|" + rs.getString("country_id") + "|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectFlightsId(Flights flights) {
        String SQL = "select * from \"QA_Practice_2\".flights where id=?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, flights.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("id") + "|" + rs.getString("plane_model") + "|" + rs.getString("departure_time")
                        + "|" + rs.getString("came_from") + "|" + rs.getString("arrival") + "|" + "|" +
                        rs.getString("duration") + "|" + rs.getString("seats") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectTicketsId(Tickets tickets) {
        String SQL = "select * from \"QA_Practice_2\".tickets where ticket_id=?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, tickets.getTicked_id());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("|" + rs.getInt("ticket_id") + "|" + rs.getString("id_passenger") + "|" + rs.getString("flight_number")
                        + "|" + rs.getString("register_time") + "|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCountries(Countries countries) {
        String SQL = "DELETE  FROM \"QA_Practice_2\".countries WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, countries.getId());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAirports(Airports airports) {
        String SQL = "DELETE  FROM \"QA_Practice_2\".countries WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, airports.getId());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassengers(Passengers passengers) {
        String SQL = "DELETE  FROM \"QA_Practice_2\".passengers WHERE passport_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, passengers.getId_passport());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlights(Flights flights) {
        String SQL = "DELETE  FROM \"QA_Practice_2\".flights WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, flights.getId());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTickets(Tickets tickets) {
        String SQL = "DELETE  FROM \"QA_Practice_2\".tickets WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, tickets.getTicked_id());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateAirports(Airports airports) {
        String SQL = "UPDATE \"QA_Practice_2\".airports SET " +
                "country_id = ? " +
                "where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, airports.getId());
            stmt.setInt(2,airports.getCountry_id());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassengers(Passengers passengers) {
        String SQL = "update \"QA_Practice_2\".passengers set" +
                " id_passport =?" +
                " where id_passport =?; ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, passengers.getId_passport());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlights(Flights flights) {
        String SQL = "update \"QA_Practice_2\".passengers set" +
                " id =?" +
                " where id =?; ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, flights.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTickets(Tickets tickets) {
        String SQL = "update \"QA_Practice_2\".tickets set" +
                " ticket_id =?" +
                " where ticket_id =?; ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, tickets.getTicked_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chooseActionCountry(Connection1 connection1) {

        while (exit) {
            System.out.println("     Меню Страны:\n"+
                    "1)- добавления страны.\n" +
                    "2)- удаление страны \n" +
                    "3)- изменить аэропорт по коду страны \n" +
                    "4)- введите код страны чтобы получить информацию по Id \n" +
                    "5)- чтобы получить всю информацию о странах \n" +
                    "0)- для выхода в главное меню ");
            int enterAction1 = scan.nextInt();
            if (enterAction1 == 1) {
                System.out.println("Введите код страны:");
                int enterAction2 = scan.nextInt();
                System.out.println("Введите название страны:");
                String enterAction3 = scan.next();
                countries.setId(enterAction2);
                countries.setName(enterAction3);
                connection1.insertCountries(countries);
            } else if (enterAction1 == 2) {
                System.out.println("Какую страну хотите удалить? Введите номер ID");
                int enterAction = scan.nextInt();
                countries.setId(enterAction);
                connection1.deleteCountries(countries);
            } else if (enterAction1 == 3) {
                System.out.println("Введите код страны чтобы поменять аэропорт:");
                int enterAction = scan.nextInt();
                airports.setId(enterAction);
                System.out.println("Введите код аэропорта которую хотите сохранить:");
                int enterAction3 = scan.nextInt();
                airports.setCountry_id(enterAction3);
                connection1.updateAirports(airports);
            } else if (enterAction1 == 4) {
                System.out.println("Введите код страны чтобы получить информацию: ");
                int enterAction = scan.nextInt();
                countries.setId(enterAction);
                connection1.selectCountriesId(countries);
            } else if (enterAction1 == 5) {
                connection1.selectCountries(countries);
            }
            else if(enterAction1==0){
                connection1.chooseActionMenu(connection1);
            }
            else {
                System.out.println("Ввели цифру вместо буквы или наоборот");
            }
        }
    }

    public void chooseActionAirports(Connection1 connection1) {

        while (exit) {
            System.out.println("     Меню Аэропорта:\n"+
                    "1)- добавления аэропорта.\n" +
                    "2)- удаление аэропорта \n" +
                    "3)- изменить аэропорт \n" +
                    "4)- введите код аэропорта чтобы получить информацию по Id \n" +
                    "5)- чтобы получить всю информацию об аэропортах\n" +
                    "0)- для выхода в главное меню ");
            int enterAction1 = scan.nextInt();
            if (enterAction1 == 1) {
                System.out.println("Введите код аэропорта:");
                int enterAction2 = scan.nextInt();
                System.out.println("Введите название города на котором расположен аэропорт:");
                String enterAction3 = scan.next();
                airports.setId(enterAction2);
                airports.setCity(enterAction3);
                System.out.println("Введите код страны на котором расположен аэропорт: ");
                int enterAction4 = scan.nextInt();
                airports.setCountry_id(enterAction4);
                connection1.insertAirports(airports);
            } else if (enterAction1 == 2) {
                System.out.println("Какой аэропорт хотите удалить? Введите номер ID");
                int enterAction = scan.nextInt();
                airports.setId(enterAction);
                connection1.deleteAirports(airports);
            } else if (enterAction1 == 3) {
                System.out.println("Введите код страны чтобы поменять аэропорт:");
                int enterAction = scan.nextInt();
                airports.setId(enterAction);
                System.out.println("Введите код аэропорта которую хотите сохранить:");
                int enterAction3 = scan.nextInt();
                airports.setCountry_id(enterAction3);
                connection1.updateAirports(airports);

            } else if (enterAction1 == 4) {
                System.out.println("Введите код аэропорта чтобы получить информацию: ");
                int enterAction = scan.nextInt();
                airports.setId(enterAction);
                connection1.selectAirportsId(airports);
            } else if (enterAction1 == 5) {
                connection1.selectAirports(airports);
            }
            else if(enterAction1==0){
                connection1.chooseActionMenu(connection1);
            }
            else {
                System.out.println("Ввели цифру вместо буквы или наоборот");
            }
        }
    }

    public void chooseActionPassengers(Connection1 connection1)  {

        while (exit) {
            System.out.println("     Меню Пассажира:\n"+
                    "1)- добавления пассажира.\n" +
                    "2)- удаление пассажира \n" +
                    "3)- изменить пассажира \n" +
                    "4)- введите id пасспорта чтобы получить информацию о пассажире \n" +
                    "5)- чтобы получить всю информацию о пассажире \n" +
                    "0)- для выхода в главное меню ");
            int enterAction1 = scan.nextInt();
            if (enterAction1 == 1) {
                System.out.println("Введите id паспорта:");
                String enterAction2 = scan.next();
                System.out.println("Введите инн :");
                int enterAction3 = scan.nextInt();
                passengers.setId_passport(enterAction2);
                passengers.setInn(enterAction3);
                System.out.println("Введите ФИО :");
                String enterAction4 = scan.next();
                passengers.setFio(enterAction4);
                System.out.println("Укажите пол ");
                String enterAction5 = scan.next();
                passengers.setGender(enterAction5);
                System.out.println("Введите код страны пассажира :");
                int enterAction6 = scan.nextInt();
                passengers.setCountry_id(enterAction6);
                connection1.insertPassengers(passengers);
            } else if (enterAction1 == 2) {
                System.out.println("Какого пассажира хотите удалить? Введите номер ID паспорта");
                String enterAction = scan.next();
                passengers.setId_passport(enterAction);
                connection1.deletePassengers(passengers);
            } else if (enterAction1 == 3) {
                System.out.println("Введите Id паспорта пассажира чтобы поменять:");
                String enterAction = scan.next();
                passengers.setId_passport(enterAction);
                System.out.println("Введите инн : ");
                int enterAction2 = scan.nextInt();
                passengers.setInn(enterAction2);
                System.out.println("Введите ФИО пассажира :");
                String enterAction3 = scan.next();
                passengers.setFio(enterAction3);
                System.out.println("Укажите пол :");
                String enterAction4 = scan.next();
                passengers.setGender(enterAction4);
                System.out.println("Введите код страны пассажира :");
                int enterAction5 = scan.nextInt();
                passengers.setCountry_id(enterAction5);
                connection1.updatePassengers(passengers);

            } else if (enterAction1 == 4) {
                System.out.println("Введите Id паспорта пассажира чтобы получить информацию :");
                String enterAction = scan.next();
                passengers.setId_passport(enterAction);
                connection1.selectPassengersId(passengers);
            } else if (enterAction1 == 5) {
                connection1.selectPassengers(passengers);
            }
            else if(enterAction1==0){
                connection1.chooseActionMenu(connection1);
            }
            else {
                System.out.println("Ввели цифру вместо буквы или наоборот");
            }
        }
    }

    public void chooseActionFlights(Connection1 connection1) {

        while (exit) {
            System.out.println("     Меню Рейсов:\n"+
                    "1)- добавления рейса.\n" +
                    "2)- удаление рейса \n" +
                    "3)- изменить рейс \n" +
                    "4)- введите номер рейса чтобы получить информацию \n" +
                    "5)- чтобы получить всю информацию о рейсе \n" +
                    "0)- для выхода в главное меню ");
            int enterAction1 = scan.nextInt();
            if (enterAction1 == 1) {
                System.out.println("Введите номер рейса :");
                flights.setId(enterAction1);
                System.out.println("Введите модель самолета :");
                String enterAction3 = scan.next();
                flights.setPlane_model(enterAction3);
                flights.setDeparture_time(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Введите код аэропорта откуда будет вылет :");
                int enterAction4 = scan.nextInt();
                flights.setCame_from(enterAction4);
                System.out.println("Введите код аэропорта где будет прилет :");
                int enterAction5 = scan.nextInt();
                flights.setArrival(enterAction5);
                System.out.println("Введите продолжительность полета");
                String enterAction6 = scan.next();
                flights.setDuration(enterAction6);
                System.out.println("Введите сколько мест");
                int enterAction7 = scan.nextInt();
                flights.setSeats(enterAction7);
                connection1.insertFlights(flights);

            } else if (enterAction1 == 2) {
                System.out.println("Какой рейс хотите удалить? Введите номер :");
                int enterAction = scan.nextInt();
                flights.setId(enterAction);
                connection1.deleteFlights(flights);
            } else if (enterAction1 == 3) {
                System.out.println("Введите номер рейса чтобы поменять:");
                int enterAction = scan.nextInt();
                flights.setId(enterAction);
                System.out.println("Введите модель самолета : ");
                String enterAction2 = scan.next();
                flights.setPlane_model(enterAction2);
                flights.setDeparture_time(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Введите код аэропорта откуда будет вылет :");
                int enterAction3 = scan.nextInt();
                flights.setCame_from(enterAction3);
                System.out.println("Введите код аэропорта где  будет прилет:");
                int enterAction4 = scan.nextInt();
                flights.setArrival(enterAction4);
                System.out.println("Введите продолжительность полета :");
                String enterAction5 = scan.next();
                flights.setDuration(enterAction5);
                connection1.updateFlights(flights);

            } else if (enterAction1 == 4) {
                System.out.println("Введите номер рейса чтобы получить информацию :");
                int enterAction = scan.nextInt();
                flights.setId(enterAction);
                connection1.selectFlightsId(flights);
            } else if (enterAction1 == 5) {
                connection1.selectFlights(flights);
            }
            else if(enterAction1==0){
                connection1.chooseActionMenu(connection1);
            }
            else {
                System.out.println("Ввели цифру вместо буквы или наоборот");
            }
        }
    }

    public void chooseActionTickets(Connection1 connection1) {

        while (exit) {
            System.out.println("     Меню Билетов:\n"+
                    "1)- добавления билета.\n" +
                    "2)- удаление билета \n" +
                    "3)- изменить билет \n" +
                    "4)- введите номер билета чтобы получить информацию \n" +
                    "5)- чтобы получить всю информацию о билете\n" +
                    "0)- для выхода в главное меню ");
            int enterAction1 = scan.nextInt();
            if (enterAction1 == 1) {
                System.out.println("Введите номер билета :");
                int enterAction0 = scan.nextInt();
                tickets.setTicked_id(enterAction0);
                System.out.println("Введите id пассажира :");
                String enterAction3 = scan.next();
                tickets.setId_passenger(enterAction3);
                tickets.setRegister_time(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Введите номер рейса :");
                int enterAction4 = scan.nextInt();
                tickets.setFlight_number(enterAction4);
                tickets.setRegister_time(Timestamp.valueOf(LocalDateTime.now()));
                connection1.insertTickets(tickets);
            } else if (enterAction1 == 2) {
                System.out.println("Какой билет хотите удалить? Введите номер :");
                int enterAction = scan.nextInt();
                tickets.setTicked_id(enterAction);
                connection1.deleteTickets(tickets);
            } else if (enterAction1 == 3) {
                System.out.println("Введите номер билета чтобы поменять:");
                int enterAction = scan.nextInt();
                tickets.setTicked_id(enterAction);
                System.out.println("Введите id  пассажира : ");
                String enterAction2 = scan.next();
                tickets.setId_passenger(enterAction2);
                System.out.println("Введите номер рейса :");
                int enterAction3 = scan.nextInt();
                tickets.setFlight_number(enterAction3);
                tickets.setRegister_time(Timestamp.valueOf(LocalDateTime.now()));
                connection1.updateTickets(tickets);
            } else if (enterAction1 == 4) {
                System.out.println("Введите номер билета чтобы получить информацию :");
                int enterAction = scan.nextInt();
                tickets.setTicked_id(enterAction);
                connection1.selectTicketsId(tickets);
            } else if (enterAction1 == 5) {
                connection1.selectTickets(tickets);
            }
            else if(enterAction1==0){
                connection1.chooseActionMenu(connection1);
            }
            else {
                System.out.println("Ввели цифру вместо буквы или наоборот");
            }
        }
    }

    public void chooseActionMenu(Connection1 connection1) {
        while (exit) {
            System.out.println("     Главное Меню:\n"+
                    "1)- Меню со странами.\n" +
                    "2)- Меню с аэропортами \n" +
                    "3)- Меню с рейсами \n" +
                    "4)- Меню с пассажирами \n" +
                    "5)- Меню с билетами \n" +
                    "0)- для выхода ");
            int enterAction = scan.nextInt();
            if (enterAction == 1) {
                connection1.chooseActionCountry(connection1);
            } else if (enterAction == 2) {
                connection1.chooseActionAirports(connection1);
            } else if (enterAction == 3) {
                connection1.chooseActionFlights(connection1);
            } else if (enterAction == 4) {
                connection1.chooseActionPassengers(connection1);
            } else if (enterAction == 5) {
                connection1.chooseActionTickets(connection1);
            }
            else if(enterAction==0){
                exit=false;
            }
            else {
                System.out.println("Введите цифру из меню!");
            }
        }
    }


}
