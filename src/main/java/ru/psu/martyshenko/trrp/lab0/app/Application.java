package ru.psu.martyshenko.trrp.lab0.app;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Выберите опицю:");
        System.out.println("1. Импорт данных из ненормализованной структуры таблиц базы данных  в нормализованную");
        System.out.println("2. Выгрузка данных в элетронную таблицу (.xsl)");
        System.out.println("3. Выход");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        while (option != 3) {
            switch (option) {
                case 1:
                    importOption();
                    option = 3;
                    break;
                case 2:
                    exportOption();
                    option = 3;
                    break;
                default:
                    System.out.println("Неверный ввод! Пожалуйста, ведите 1, 2 или 3.");
                    option = in.nextInt();
            }
        }
    }

    private static void importOption() {
        TablesConverter tablesConverter = new TablesConverter();
        tablesConverter.convert();
    }

    private static void exportOption() {
        Export export = new Export();
        try {
            export.runExport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
