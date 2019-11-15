/*Вариант 59.
Таблица содержит данные о туристических маршрутах, предлагаемых турагентством.
Структура записи: шифр маршрута (ключевое поле), страна, стоимость путевки.
Подсчитать: 
1)	Количество маршрутов и минимальную стоимость маршрута в каждую страну; 
2)	Общее количество стран, в которые возможны поездки.
Условие удаления (дополнительное): стоимость маршрута меньше заданной.
Сортировка по убыванию в поле стоимость путевки.
Фильтрация по полю страна.
Интерфейс для базовой таблицы: Map.
*/

//import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Demo {
    private static final int _0 = 0;

    public static void main(String[] args){
        System.out.println("Скопируем данные из файла input.txt в словарь Журнал путевок #1");
        TouristJournal sportsmanJournal = new TouristJournal("Журнал путевок #1");
        int j = 0;
        String line;
        BufferedReader inp = null;
        String[] data = new String[5];
        Set<String> quantity = new TreeSet<String>();
        int i = 0;
        int t = 0;
        try{
            inp = new BufferedReader(new FileReader("input.txt"));
            while ((line = inp.readLine()) != null){
                line = line.trim();
                if(line.equals("")) continue;
                data = line.split("\\s+");
                sportsmanJournal.addTourist(new TouristKey(Integer.parseInt(data[0]),data[1], 0),Integer.parseInt(data[2]));
                quantity.add(data[1]);
            }
            
        } catch (Exception e){
            System.out.println(e);
        }
       
        System.out.println("Всего маршрутов: " + sportsmanJournal.size());
        System.out.println("Количество доступных стран: " + quantity.size());
        sportsmanJournal.putTouristJournal();
        System.out.println("\n 1.       Запрос на добавление записи:");
        TouristKey tempKey = new TouristKey(32, "Япония",700);
        System.out.println(String.format("%s", tempKey));
        sportsmanJournal.addTourist(tempKey,700);
        sportsmanJournal.putTouristJournal();
        System.out.println("\n2.       Запрос на обновление");
        System.out.println(String.format("%s", tempKey));
        sportsmanJournal.updateTourist(tempKey, 800);
        sportsmanJournal.putTouristJournal();
        System.out.println("\n3.       Запрос на выборку пути");
        sportsmanJournal.selectTouristData(44).putTouristJournal();
        sportsmanJournal.selectTouristData(100).sortScoreDesc().putTouristJournal();
        sportsmanJournal.selectTouristMonthData(55, 500).putTouristJournal();
        sportsmanJournal.selectTouristMonthData(55, 500).sortIdAscTeamCodeDesc().putTouristJournal();
        sportsmanJournal.selectTeamCodeData("Япония").putTouristJournal();
        sportsmanJournal.selectTeamCodeData("Чехия").sortTeamAscMonthDesc().putTouristJournal();
        System.out.println("Сортировки на исходном журнале:");
        sportsmanJournal.sortTeamAscMonthDesc().putTouristJournal();
        sportsmanJournal.sortIdAscTeamCodeDesc().putTouristJournal();
        sportsmanJournal.sortScoreDesc().putTouristJournal();
        System.out.println("\n 4.       Запрос на удаление");
        System.out.println("Удалим путь с ключем - "+tempKey);
        sportsmanJournal.delTourist(tempKey);
        sportsmanJournal.putTouristJournal();
        System.out.println("Удалим все пути с id = 55");
        sportsmanJournal.delTourist(55);
        sportsmanJournal.putTouristJournal();
        System.out.println("Удалим все пути с id < 50");
        sportsmanJournal.delSmallerTourist(50);
        sportsmanJournal.putTouristJournal();
    }
}
