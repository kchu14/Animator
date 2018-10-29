package cs3500.animator.model;

import java.awt.Color;

public class AShape implements IShape {

  private String name;
  private Color color;

  public AShape (String name, Color color) {
    this.name = name;
    this.color = color;
  }

  @Override
  public void move(int x, int y) {

  }

  @Override
  public void changeColor(Color color) {

  }

  @Override
  public void changeSize(int hShift, int vShift) {

  }

  @Override
  public void delete() {

  }
}
