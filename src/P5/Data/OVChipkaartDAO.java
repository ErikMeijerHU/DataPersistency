package P5.Data;

import P5.Domein.OVChipkaart;
import P5.Domein.Product;
import P5.Domein.Reiziger;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ovChipkaart) throws SQLException;
    public boolean update(OVChipkaart ovChipkaart) throws SQLException;
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException;
    public OVChipkaart findById(int id) throws SQLException;
    public ArrayList<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    public ArrayList<OVChipkaart> findByProduct(Product product) throws SQLException;
    public ArrayList<OVChipkaart> findAll() throws SQLException;
}
