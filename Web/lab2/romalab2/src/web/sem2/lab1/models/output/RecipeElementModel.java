package web.sem2.lab1.models.output;

import web.sem2.lab1.models.entities.Ingredient;
import web.sem2.lab1.models.entities.RecipeElement;

public class RecipeElementModel {
    private RecipeElement recipeElement;
    private Ingredient ingredient;

    public RecipeElement getRecipeElement() {
        return recipeElement;
    }

    public void setRecipeElement(RecipeElement recipeElement) {
        this.recipeElement = recipeElement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "RecipeElementModel{" +
                "recipeElement=" + recipeElement +
                ", ingredient=" + ingredient +
                '}';
    }
}
