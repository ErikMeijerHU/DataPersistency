package P3;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ReizigerDAO {
    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;
    public Reiziger findById(int id) throws SQLException;
    public Reiziger findByGbdatum(LocalDate geboortedatum) throws SQLException;
    public ArrayList<Reiziger> findAll() throws SQLException;
}
