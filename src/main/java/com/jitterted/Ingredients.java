package com.jitterted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jitterted.IngredientName.*;

public class Ingredients {
  private final List<Ingredient> ingredientList = new ArrayList<>();


  // scaffolding, not final bc it exposes the list
  public List<Ingredient> getIngredientList() {
    return ingredientList;
  }

  public Ingredients() {
    ingredientList.add(new Ingredient(COFFEE, 0.75));
    ingredientList.add(new Ingredient(DECAF_COFFEE, 0.75));
    ingredientList.add(new Ingredient(SUGAR, 0.25));
    ingredientList.add(new Ingredient(CREAM, 0.25));
    ingredientList.add(new Ingredient(STEAMED_MILK, 0.35));
    ingredientList.add(new Ingredient(FOAMED_MILK, 0.35));
    ingredientList.add(new Ingredient(ESPRESSO, 1.10));
    ingredientList.add(new Ingredient(COCOA, 0.90));
    ingredientList.add(new Ingredient(WHIPPED_CREAM, 1.00));
    Collections.sort(ingredientList);
  }
}
