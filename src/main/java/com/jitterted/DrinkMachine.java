package com.jitterted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrinkMachine {

  private final List<Drink> drinkList = new ArrayList<>(); // TODO: this is primitive obsession, improve.
  private final Ingredients ingredients = new Ingredients();

  public static void main(String[] args) {
    DrinkMachine drinkMachine = new DrinkMachine();
    drinkMachine.displayInventoryAndMenu();
    drinkMachine.startIO();
  }

  public DrinkMachine() {
    createDrinkList();
    updateDrinkCosts();
    updateMakeable();
  }

  private void updateDrinkCosts() {
    for (Drink drink : drinkList) {
      Recipe recipe = drink.getRecipe();
      double cost = ingredients.calculateCostOf(recipe);
      drink.setCost(cost);
    }
  }

  private void createDrinkList() {
    RecipeFactory recipeFactory = new RecipeFactory(ingredients.getIngredientList());
    drinkList.add(new Drink("Coffee", recipeFactory.create("Coffee", "Coffee", "Coffee", "Sugar", "Cream")));
    drinkList.add(new Drink("Decaf Coffee", recipeFactory.create("Decaf Coffee", "Decaf Coffee", "Decaf Coffee", "Sugar", "Cream")));
    drinkList.add(new Drink("Caffe Latte", recipeFactory.create("Espresso", "Espresso", "Steamed Milk")));
    drinkList.add(new Drink("Caffe Americano", recipeFactory.create("Espresso", "Espresso", "Espresso")));
    drinkList.add(new Drink("Caffe Mocha", recipeFactory.create("Espresso", "Cocoa", "Steamed Milk", "Whipped Cream")));
    drinkList.add(new Drink("Cappuccino", recipeFactory.create("Espresso", "Espresso", "Steamed Milk", "Foamed Milk")));
    Collections.sort(drinkList);
  }

  public void startIO() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String input = "";

    while (true) {
      try {
        input = reader.readLine().toLowerCase();
        if (input.isBlank()) {
          continue;
        } else if (input.equals("q")) {
          System.exit(0);
        } else if (input.equals("r")) {
          restockIngredients();
          updateMakeable();
        } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= drinkList.size()) {
          makeDrink(drinkList.get(Integer.parseInt(input) - 1));
        } else {
          System.out.println("'" + input + "' was not valid. Choose from list above, or Q or R.");
        }
      } catch (IOException e) {
        System.out.println("No idea why we got an IOException here." + e);
      }
    }
  }

  public void displayInventoryAndMenu() {
    System.out.println("\nIngredient Inventory:\n");
    for (Ingredient ingredient : ingredients.getIngredientList()) {
      System.out.println(ingredient.getName().displayName() + ", " + ingredient.getStock());
    }

    System.out.println("\nDrink Menu:\n");
    int count = 1;
    for (Drink d : drinkList) {
      System.out.printf("%d,%s,$%.2f," + d.getMakeable() + "\n\n", count, d.getName(), d.getCost());
      count++;
    }
  }

  public void makeDrink(Drink drink) {
    if (drink.getMakeable()) {
      System.out.println("Dispensing: " + drink.getName() + "\n");
      for (Ingredient ingredient : ingredients.getIngredientList()) {
        Recipe recipe = drink.getRecipe();
        if (recipe.hasIngredient(ingredient)) {
          ingredient.setStock(ingredient.getStock() - recipe.quantityNeededFor(ingredient));
        }
      }
    } else {
      System.out.println("Out of stock: " + drink.getName() + "\n");
    }
    updateMakeable();
    displayInventoryAndMenu();
  }

  public void restockIngredients() {
    for (Ingredient ingredient : ingredients.getIngredientList()) {
      ingredient.setStock(10);
    }
    updateMakeable();
    displayInventoryAndMenu();
  }

  private void updateMakeable() {
    for (Drink drink : drinkList) {
      drink.updateDrinkState();
    }
  }

}
