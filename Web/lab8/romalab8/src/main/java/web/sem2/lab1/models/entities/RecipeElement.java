package web.sem2.lab1.models.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "recipes")
@NamedQueries({
        @NamedQuery(name = "Recipe.getAll",
                query = "SELECT R FROM RecipeElement R"),
        @NamedQuery(name = "Recipe.getByPK",
                query = "SELECT R FROM RecipeElement R WHERE R.drinkId = :id")
})
@XmlRootElement
public class RecipeElement implements Serializable {
    @Id
    @Column(name = "drink_id")
    private int drinkId;
    @Id
    @Column(name = "ingredient_id")
    private int ingredientId;
    @Column(name = "ingredient_amount")
    private double ingredientAmount;

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    @Override
    public String toString() {
        return "RecipeElement{" +
                "drinkId=" + drinkId +
                ", ingredientId=" + ingredientId +
                ", ingredientAmount=" + ingredientAmount +
                '}';
    }
}
