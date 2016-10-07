package user.connection;
import java.sql.Connection;

public interface ConnectionFactory {
    Connection openConnection();

}
