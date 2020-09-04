package P3;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ReizigerDAO {
    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger);
    public boolean delete(Reiziger reiziger);
    public Reiziger findById(int id);
    public Reiziger findByGbdatum(LocalDate geboortedatum);
    public ArrayList<Reiziger> findAll();
}
