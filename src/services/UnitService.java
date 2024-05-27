package services;

import dao.UnitDAO;
import dto.Unit;

import java.sql.SQLException;
import java.util.List;

public class UnitService {

    private final UnitDAO unitDAO;

    public UnitService() {
        unitDAO = new UnitDAO();
    }

    public void createUnit(Unit unit) throws SQLException, IllegalArgumentException {
        try {
            if (unit.isValidate()) {
                boolean isUnitAlreadyExist = false;
                try {
                    Unit existingUnit = unitDAO.getUnitByName(unit.getUnit());
                    if (existingUnit != null) {
                        isUnitAlreadyExist = true;
                    }
                } catch (Exception ex) {
                    // Log the exception if needed
                }
                if (!isUnitAlreadyExist) {
                    unitDAO.createUnit(unit);
                } else {
                    throw new IllegalArgumentException("Unit already exists!");
                }
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    public List<Unit> getAllUnits() throws SQLException {
        return unitDAO.readUnits();
    }

    public void updateUnit(Unit unit) throws SQLException, IllegalArgumentException {
        if (unit.getId() <= 0) {
            throw new IllegalArgumentException("Invalid unit id");
        }
        if (unit.isValidate()) {
            Unit existUnit = getUnitByID(unit.getId());
            if (existUnit != null) {
                unitDAO.updateUnit(unit);
            } else {
                throw new IllegalArgumentException("unit not found to update");
            }

        }
    }

    public Unit getUnitByID(int id) throws SQLException, IllegalArgumentException {
        if (id == 0) {
            throw new IllegalArgumentException("Unit id cannot be 0");
        }
        return unitDAO.getUnitByID(id);
    }

    public Unit getUnitByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Unit name cannot be empty");
        }
        return unitDAO.getUnitByName(name);
    }

    public List<Unit> searchUnitsByName(String name) throws SQLException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            return List.of();
        }
        return unitDAO.searchUnitsByName(name);
    }

//    public static void main(String[] args) {
//        UnitService unitService = new UnitService();

        // Example for adding a new Unit
//        Unit unitToAdd = new Unit();
//        unitToAdd.setUnit("New Unit");
//        try {
//            unitService.createUnit(unitToAdd);
//            System.out.println("Unit added successfully!");
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

        // Example for getting all Units
//        try {
//            List<Unit> allUnits = unitService.getAllUnits();
//            System.out.println("All Units: " + allUnits);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

// Example for updating a Unit
//        Unit unitToUpdate = new Unit();
//        unitToUpdate.setId(2); // Assuming this is the id of the Unit you want to update
//        unitToUpdate.setUnit("");
//        try {
//            unitService.updateUnit(unitToUpdate);
//            System.out.println("Unit updated successfully!");
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

        // Example for getting a Unit by its id
//        try {
//            Unit foundUnit = unitService.getUnitByID(100); // Assuming this is the id of the Unit you want to find
//            System.out.println("Found Unit: " + foundUnit);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }

        // Example for searching Units by name
//        String unitName = "Up";
//        try {
//            List<Unit> searchResults = unitService.searchUnitsByName(unitName);
//            System.out.println("Search results for '" + unitName + "': " + searchResults);
//        } catch (SQLException | IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//    }
}
