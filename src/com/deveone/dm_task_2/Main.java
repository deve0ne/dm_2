package com.deveone.dm_task_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int comparisons = 0;

    public static void main(String[] args) {
        int[] numbers = readArrFromFile("input.txt");

        if (numbers.length == 0) {
            System.err.println("\nNumber array is empty :|");
            return;
        }

        insertionSort(numbers);

        writeAnswerToFile("output.txt", numbers, comparisons);
    }

    private static void insertionSort(int[] array) {
        for (int left = 0; left < array.length; left++) {
            int value = array[left];

            int i = left - 1;

            for (; i >= 0; i--) {
                comparisons++;
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
            }

            array[i + 1] = value;
        }
    }

    private static int[] readArrFromFile(String filepath) {
        File file = new File(filepath);
        List<Integer> numbers = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);

            while (scanner.hasNext()) {
                if (scanner.hasNextInt())
                    numbers.add(scanner.nextInt());
                else
                    scanner.next();
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Error on reading from file :(");
            e.printStackTrace();
            return new int[0];
        }

        return numbers.stream().mapToInt(i -> i).toArray();
    }

    private static void writeAnswerToFile(String filepath, int[] sortedNumbers, int comparisons) {
        File file = new File(filepath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error on creating file :(");
            e.printStackTrace();
            return;
        }

        try {
            FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);

            StringBuilder text = new StringBuilder();

            for (int num : sortedNumbers)
                text.append(num).append(" ");

            text.append('\n').append(comparisons);

            writer.write(text.toString());

            writer.close();
        } catch (IOException e) {
            System.err.println("Error on writing to file :(");
            e.printStackTrace();
        }
    }
}