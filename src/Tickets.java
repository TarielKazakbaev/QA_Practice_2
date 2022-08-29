import java.sql.Timestamp;
import java.util.Date;

public class Tickets {
    private Integer ticked_id;
    private String id_passenger;
    private Integer flight_number;
    private Timestamp register_time;

    public Integer getTicked_id() {
        return ticked_id;
    }

    public void setTicked_id(Integer ticked_id) {
        this.ticked_id = ticked_id;
    }

    public String getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(String id_passenger) {
        this.id_passenger = id_passenger;
    }

    public Integer getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(Integer flight_number) {
        this.flight_number = flight_number;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }
}
