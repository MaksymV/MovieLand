package com.volomak.movieland.entity;

public class Movie {
  private int id;
  private String name;
  private String originalName;
  private Long year;

  private String description;
  private Double rate;
  private Double price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOriginalName() {
    return originalName;
  }

  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }

  public Long getYear() {
    return year;
  }

  public void setYear(Long year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "MovieDao{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", originalName='" + originalName + '\'' +
            ", year=" + year +
            ", description='" + description + '\'' +
            ", rate=" + rate +
            ", price=" + price +
            '}';
  }
}
