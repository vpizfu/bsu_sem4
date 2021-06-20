package web.sem2.lab1.models.entities;

public class RecipeElement {
    private int drinkId;
    private int ingredientId;
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
