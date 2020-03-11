package hw.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/*
 * Задание 1: Подсчитайте количество различных слов в файле.
 * Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).
 * Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
 * Задание 4: Выведите на экран все строки файла в обратном порядке.
 * Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
 * Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
 */


public class Lesson3 {
    public static void main(String[] args) throws FileNotFoundException {
        String src = "src/hw/lesson3/les3text.txt";
        List<String> arrli = new ArrayList<String>();//Arrays.asList("Effective Java", "Algorithms", "Refactoring" )
        arrli = countNoRepeatWord(src);//ArrayList<String> arrli = countNoRepeatWord(src);
        int res = arrli.size();
        System.out.println("1. Количество уникальных слов: " + res);

        /*
        //более короткий вариант сортировки, но либо по длинне, либо по алфавиту
        Comparator<String> byLength = new Comparator<String>(){
            @Override
            public int compare (String s1, String s2) {
                return s1.length () - s2.length ();
            }
        };
        Collections.sort(arrli, byLength);
        System.out.println(arrli);
        */
        ArrLi[] arrli2 = new ArrLi[arrli.size()];
        int i = 0;
        for(String el : arrli){
            arrli2[i++] = new ArrLi(el);
        }
        //Collections.sort(arrli2);
        Arrays.sort(arrli2);
        System.out.println("2. Сортировка длинна + алфавит: ");
        System.out.print("    ");
        System.out.println(arrli);
        System.out.print("    ");
        printItemList(arrli2);

    }

    //для печати списка
    private static void printItemList(ArrLi[] list) {
        int i = 0;
        String res = "[ ";
        for(ArrLi el : list){
            res += el.getName().toString() + ", ";
        }
        res += "]";
        System.out.println(res);
    }

    //Сортировка по длинне, потом по алфавиту (Задание 2)
    public static ArrayList arLsortLength(ArrayList arrli) {
        return arrli;
    }

    //создаем список уникальных слов из файла (Задание 1)
    public static ArrayList countNoRepeatWord(String src) throws FileNotFoundException {
        Scanner in = new Scanner(new File(src));
        List<String> string_list = new ArrayList<String>();
        String[] string_array;

        //пока есть еще строки
        while (in.hasNextLine()) {
            String strtext = in.nextLine(); // считываем очередную строку
            strtext = delZnak(strtext); //удаляем знаки пунктуации
            strtext.trim(); //удаляем пробелы вначале и конце строки
            string_array = strtext.split(" "); //режем строку в массив

            //создаем список уникальных слов
            for (int i = 0; i < string_array.length; i++) {
                String word = string_array[i];
                //проеряем существует ли такое слово в списке. если нет то добавляем его
                if (string_list.contains(word) == false && word.length() != 0) {
                    string_list.add(word);
                }
            }
        }
        //System.out.println(string_list.toString());
        return (ArrayList) string_list;
    }

    private static final Set<Character> PUNCT_SET = new HashSet<>(Arrays.asList(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-',
            '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
            '_', '`', '{', '|', '}', '~'
    ));

    public static String delZnak(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!PUNCT_SET.contains(Character.valueOf(c))) {
                result.append(c);
            }
        }
        return result.toString();
    }
}


class ArrLi implements Comparable<ArrLi> {

    private int length;

    private String name;

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public ArrLi(String name) {
        this.length = name.length();
        this.name = name;
    }

    @Override
    public int compareTo(ArrLi el) {
        //метод сортировки по длинне
        int res = (this.length - el.length);
        //если длины равны, то по алфавиту
        if (res == 0){
            res = this.getName().compareTo(el.getName());
        }
        return res;
    }
}