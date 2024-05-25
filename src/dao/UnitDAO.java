package dao;

import dto.Unit;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitDAO {

    public void createUnit(Unit unit) throws SQLException {
        String query = "INSERT INTO units (unit) VALUES (?)";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, unit.getUnit().toLowerCase());
        statement.executeUpdate();
    }

    public List<Unit> readUnits() throws SQLException {
        List<Unit> units = new ArrayList<>();
        String query = "SELECT * FROM units";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            units.add(Unit.fromResultSet(results));

        }
        return units;
    }

    public void updateUnit(Unit unit) throws SQLException {
        String query = "UPDATE units SET unit = ? WHERE unit_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, unit.getUnit().toLowerCase());
        statement.setInt(2, unit.getId());
        statement.executeUpdate();
    }

    public Unit getUnitByID(int id) throws SQLException {
        String query = "SELECT * FROM units WHERE unit_id = ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        try (ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                return Unit.fromResultSet(result);
            } else {
                throw new IllegalArgumentException("Unit not found");
            }
        }
    }

    public Unit getUnitByName(String name) throws SQLException {
        String query = "SELECT * FROM units WHERE unit = ?";
                Connection conn = Database.getInstance().getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name.toLowerCase());
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Unit.fromResultSet(result);
                } else {
                    throw new IllegalArgumentException("Unit not found");
                }
            
        }
    }

    public List<Unit> searchUnitsByName(String name) throws SQLException {
        List<Unit> units = new ArrayList<>();
        String query = "SELECT * FROM units WHERE unit LIKE ?";
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name.toLowerCase() + "%");
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    units.add(Unit.fromResultSet(results));
                }
        }
        return units;
    }
}
