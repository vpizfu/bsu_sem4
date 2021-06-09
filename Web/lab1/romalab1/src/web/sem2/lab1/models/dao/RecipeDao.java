package web.sem2.lab1.models.dao;

import web.sem2.lab1.exceptions.DaoException;
import web.sem2.lab1.models.entities.BillElement;
import web.sem2.lab1.models.entities.RecipeElement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RecipeDao extends Dao<RecipeElement, Integer> {
    private static final String RECIPE_CREATE = "INSERT INTO recipes (drink_id, ingredient_id, ingredient_amount) VALUES (?, ?, ?);";
    private static final String RECIPE_UPDATE = "UPDATE recipes SET ingredient_amount = ? WHERE drink_id = ? AND ingredient_id = ?;";

    private final PreparedStatement RECIPE_CREATE_STMT;
    private final PreparedStatement RECIPE_UPDATE_STMT;

    public RecipeDao() throws DaoException {
        super("recipes", "drink_id");

        try {
            RECIPE_CREATE_STMT = conn.prepareStatement(RECIPE_CREATE);
            RECIPE_UPDATE_STMT = conn.prepareStatement(RECIPE_UPDATE);
        } catch (SQLException e) {
            throw new DaoException("can't compile statements", e);
        }
    }

    @Override
    protected RecipeElement mapRowToObj(ResultSet set) throws SQLException {
        RecipeElement billElement = new RecipeElement();
        billElement.setDrinkId(set.getInt("drink_id"));
        billElement.setIngredientId(set.getInt("ingredient_id"));
        billElement.setIngredientAmount(set.getInt("ingredient_amount"));
        return billElement;
    }

    @Override
    protected Integer getKeyFromObj(RecipeElement obj) {
        return obj.getDrinkId();
    }

    @Override
    protected PreparedStatement createPreparedStmtForObj(RecipeElement obj) throws SQLException {
        RECIPE_CREATE_STMT.setInt(1, obj.getDrinkId());
        RECIPE_CREATE_STMT.setInt(2, obj.getIngredientId());
        RECIPE_CREATE_STMT.setDouble(3, obj.getIngredientAmount());
        return RECIPE_CREATE_STMT;
    }

    @Override
    protected PreparedStatement updatePreparedStmtForObj(RecipeElement obj) throws SQLException {
        RECIPE_UPDATE_STMT.setInt(2, obj.getDrinkId());
        RECIPE_UPDATE_STMT.setInt(3, obj.getIngredientId());
        RECIPE_UPDATE_STMT.setDouble(1, obj.getIngredientAmount());
        return RECIPE_UPDATE_STMT;
    }

    @Override
    public Optional<RecipeElement> get(Integer key) throws DaoException {
        throw new DaoException("can't get a single recipe because of composite primary key");
    }
    
    public List<RecipeElement> getByDrink(Integer drinkId) throws DaoException {
        try {
            GET_BY_PK_STMT.setInt(1, drinkId);
            return getSome(GET_BY_PK_STMT);
        } catch (SQLException | DaoException e) {
            throw new DaoException("can't get all recipe elements with the specified drink", e);
        }
    }
}
