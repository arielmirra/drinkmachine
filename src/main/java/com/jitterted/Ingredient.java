package com.jitterted;

public class Ingredient implements Comparable<Ingredient> {
  private final IngredientName name;

  // currency? what about decimals?
  private final double cost; // TODO: this is primitive obsession, improve.

  // how does it count? what units uses?
  private int stock = 0; // TODO: this is primitive obsession, improve.

  public Ingredient(IngredientName name, double cost) {
    this.name = name;
    this.cost = cost;
    this.stock = 10;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int compareTo(Ingredient ingredient) {
    return name.compareTo(ingredient.name);
  }

  public double getCost() {
    return cost;
  }

  public IngredientName getName() {
    return name;
  }

}
