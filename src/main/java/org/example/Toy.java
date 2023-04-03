package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;
    private List<Toy> prizeToys;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {
        List<Toy> toys = new ArrayList<>();

        // Добавление новых игрушек
        toys.add(new Toy(1, "Медведь", 10, 20.0));
        toys.add(new Toy(2, "Кукла", 15, 30.0));
        toys.add(new Toy(3, "Машинка", 20, 50.0));
        toys.add(new Toy(4, "Солдатик", 12, 10.0));
        toys.add(new Toy(5, "Фонарь", 9, 15.0));

        // Розыгрыш игрушек
        List<Toy> prizeToys = new ArrayList<>();
        Set<Integer> usedIds = new HashSet<>();
        for (Toy toy : toys) {
            int num = (int) Math.round(toy.getWeight() / 100 * 10); // Количество игрушек на 10 попыток
            for (int i = 0; i < num; i++) {
                if (!usedIds.contains(toy.getId())) { // Проверяем, был ли уже добавлен игрушке с таким ID
                    prizeToys.add(toy);
                    usedIds.add(toy.getId()); // Добавляем ID в множество
                }
            }
        }
        Collections.shuffle(prizeToys); // Перемешивание призовых игрушек

        // Получение призовой игрушки
        Toy prizeToy = prizeToys.get(0);
        prizeToy.setQuantity(prizeToy.getQuantity() - 1);

        // Запись в текстовый файл
        try {
            FileWriter writer = new FileWriter("prize_toys.txt", true);
            writer.write(prizeToy.getId() + " " + prizeToy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toy toy = new Toy(0, "", 0, 0.0);
        toy.prizeToys = prizeToys;
        toy.printPrizeToys();
    }

    public void printPrizeToys() {
        System.out.println("Список призовых игрушек:");
        for (Toy prizeToy : prizeToys) {
            System.out.println("ID: " + prizeToy.getId() + ", Название: " + prizeToy.getName() +
                    ", Количество: " + prizeToy.getQuantity() + ", Вес: " + prizeToy.getWeight() + "%");
        }
    }

}