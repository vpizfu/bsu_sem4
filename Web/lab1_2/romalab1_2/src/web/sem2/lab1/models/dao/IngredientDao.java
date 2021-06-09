package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientDao extends Dao<Ingredient, Integer> {
    private static final String INGREDIENT_CREATE = "INSERT INTO ingredients (id, name, units) VALUES (?, ?, ?);";
    private static final String INGREDIENT_UPDATE = "UPDATE ingredients SET name = ?, units = ? WHERE id = ?;";

    private final PreparedStatement INGREDIENT_CREATE_STMT;
    private final PreparedStatement INGREDIENT_UPDATE_STMT;

    public IngredientDao() throws DaoException {
        super("ingredients", "id");

        try {
            INGREDIENT_CREATE_STMT = conn.prepareStatement(INGREDIENT_CREATE);
            INGREDIENT_UPDATE_STMT = conn.prepareStatement(INGREDIENT_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }

    @Override
    protected Ingredient mapRowToObj(ResultSet set) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(set.getInt("id"));
        ingredient.setName(set.getString("name"));
        ingredient.setUnits(set.getDouble("units"));
        return ingredient;
    }

    @Override
    protected Integer getKeyFromObj(Ingredient obj) {
        return obj.getId();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(Ingredient obj) throws SQLException {
        INGREDIENT_CREATE_STMT.setInt(1, obj.getId());
        INGREDIENT_CREATE_STMT.setString(2, obj.getName());
        INGREDIENT_CREATE_STMT.setDouble(3, obj.getUnits());
        return INGREDIENT_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(Ingredient obj) throws SQLException {
        INGREDIENT_UPDATE_STMT.setInt(1, obj.getId());
        INGREDIENT_UPDATE_STMT.setString(2, obj.getName());
        INGREDIENT_UPDATE_STMT.setDouble(3, obj.getUnits());
        return INGREDIENT_UPDATE_STMT;
    }
}
