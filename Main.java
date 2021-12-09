package com.company;

import java.util.ArrayList;
//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(makeEssay(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println(split("((()))"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(overTime(new double[]{16, 18, 30, 1.8}));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println(bugger(999));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(trouble(766677, 123477766));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
    }
/**Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк
 довольно плох, она решает напечатать эссе с помощью текстового процессора.
 Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет
 длину от 1 до 15 символов включительно и состоит только из прописных или
 строчных букв. Согласно инструкции к заданию, эссе должно быть
 отформатировано очень специфическим образом: каждая строка должна содержать
 не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый
 процессор Бесси может справиться с этим требованием, используя следующую
 стратегию:
 – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите
 его в эту строку. В противном случае поместите слово на следующую строку и
 продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке
 все равно должны быть разделены одним пробелом. В конце любой строки не должно
 быть места.
 – К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
 помогите ей правильно оформить свое эссе!
 Вам будут даны n, k и строка **/
    public static String makeEssay(int n, int k, String str){
        String ess = "";
        String[] words_arr = str.split(" ");
        int line_am = 0;
        for(int i=0; i<n; i++){
            String word = words_arr[i];
            int len = word.length();
            if(line_am == 0){
                line_am += len;
                ess += word;
            }
            else if(line_am + len <= k){
                line_am += len;
                ess += " " + word;
            }
            else {
                line_am = len;
                ess += "\n" + word;
            }
        }
        return ess;
    }
/** Напишите функцию, которая группирует строку в кластер скобок.
 * Каждый кластер
 должен быть сбалансирован. **/
public static ArrayList<String> split(String str){
    ArrayList<String> str_arr = new ArrayList<>();
    int am = 0;
    int subam = 0;
    String subch = "";
    String substr = "";
    for(int i=0; i<str.length(); i++){
        subch = str.substring(i, i+1);
        if(subch.equals("(")){
            if(am == subam && subam != 0){
                str_arr.add(substr);
                am = 1;
                subam = 0;
                substr = "";
            }
            else am++;
        }
        else if(subch.equals(")")) subam++;
        substr += subch;
    }
    str_arr.add(substr);
    return str_arr;
}
/**Создайте две функции toCamelCase () и toSnakeCase (),
 * каждая из которых берет
 одну строку и преобразует ее либо в camelCase, либо в snake_case. **/
    public static String toSnakeCase(String str){
        String newstr = "";
        char ch = ' ';
        for(int i=0; i<str.length(); i++) {
            ch = str.charAt(i);
            if(ch >= 'A' && ch <= 'Z'){
                newstr += "_";
                ch = Character.toLowerCase(ch);
            }
            newstr += ch;
        }
        return newstr;
    }

    public static String toCamelCase(String str){
        String newstr = "";
        char ch = ' ';
        int i = 0;
        while(i<str.length()) {
            ch = str.charAt(i);
            if(str.charAt(i) == '_'){
                i++;
                ch = Character.toUpperCase(str.charAt(i));
            }
            newstr += ch;
            i++;
        }
        return newstr;
    }
/**Напишите функцию, которая вычисляет сверхурочную работу и оплату,
 * связанную
 с сверхурочной работой.
 Работа с 9 до 5: обычные часы работы
 После 5 вечера это сверхурочная работа
 Ваша функция получает массив с 4 значениями:
 – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
 – Конец рабочего дня. (Тот же формат)
 – Почасовая ставка
 – Множитель сверхурочных работ
 Ваша функция должна возвращать:
 $ + заработанные в тот день (округлены до ближайшей сотой) **/
    public static String overTime(double[] arr){
        double sum = 0;
        if(arr[1]>17 || arr[0]<9){
            sum = (17-arr[0])*arr[2] + (arr[1]-17)*arr[2]*arr[3];
        }
        else sum = (arr[1]-arr[0])*arr[2];
        return String.format("$%.2f", sum);
    }
/**Индекс массы тела (ИМТ) определяется путем измерения вашего веса в
 килограммах и деления на квадрат вашего роста в метрах. Категории ИМТ таковы:
 Недостаточный вес: <18,5
 Нормальный вес: 18.5-24.9
 Избыточный вес: 25 и более
 Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах,
 метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите
 ИМТ до ближайшей десятой. **/
public static String BMI(String weightStr, String heightStr) {
    double weight = Double.parseDouble(weightStr.split(" ")[0]);
    double height = Double.parseDouble(heightStr.split(" ")[0]);
    if (weightStr.contains("pounds"))
        weight *= 0.453592;
    if (heightStr.contains("inches"))
        height *= 0.0254;
    double BMI = Math.round((weight / (height * height)) * 10.0) / 10.0;
    if (BMI < 18.5)
        return BMI + " Underweight";
    if (BMI >= 18.5 && BMI <= 24.9)
        return BMI + " Normal weight";
    else
        return BMI + " Overweight";
}
/**Создайте функцию, которая принимает число и возвращает его мультипликативное
 постоянство, которое представляет собой количество раз, которое вы должны
 умножать цифры в num, пока не достигнете одной цифры. **/
public static int bugger(int num) {
    int count = 0;
    while (num > 9) {
        int chnum = 1;
        while (num > 0) {
            chnum *= num % 10;
            num /= 10;
        }
        num = chnum;
        count++;
    }
    return count;
}
/**Напишите функцию, которая преобразует строку в звездную стенографию. Если
 символ повторяется n раз, преобразуйте его в символ *n.**/
    public static String toStarShorthand(String str){
        if(str.length() < 1) return "";
        String newstr = "";
        String ch = "";
        String oldch = str.substring(0, 1);
        int am = 0;
        for(int i=0; i<str.length(); i++) {
            ch = str.substring(i, i + 1);
            if(ch.equals(oldch)) am++;
            else if(am > 1){
                newstr += oldch + "*" + am;
                am = 1;
            }
            else newstr += oldch;
            oldch = ch;
        }
        newstr += oldch;
        if(am>1) newstr += "*" + am;
        return newstr;
    }
/**Создайте функцию, которая возвращает true, если две строки рифмуются,
 *  и false в
 противном случае. Для целей этого упражнения две строки рифмуются, если
 последнее слово из каждого предложения содержит одни и те же гласные.**/
    public static boolean doesRhyme(String str1, String str2){
        String[] arr1 = str1.toLowerCase().split(" ");
        String[] arr2 = str2.toLowerCase().split(" ");
        str1 = arr1[arr1.length-1];
        str2 = arr2[arr2.length-1];
        String check_str = "aeiou";
        String substr1 = "";
        String substr2 = "";
        String ch = "";
        for(int i=0; i<str1.length(); i++) {
            ch = str1.substring(i, i + 1);
            if(check_str.contains(ch)) substr1 += ch;
        }
        for(int i=0; i<str2.length(); i++) {
            ch = str2.substring(i, i + 1);
            if(check_str.contains(ch)) substr2 += ch;
        }
        return substr1.equals(substr2);
    }
/**. Создайте функцию, которая принимает два целых числа и возвращает true, если
 число повторяется три раза подряд в любом месте в num1 и то же самое число
 повторяется два раза подряд в num2.**/
    public static boolean trouble(int num1, int num2){
        String a = String.valueOf(num1);
        String b = String.valueOf(num2);
        String ch = "";
        String oldch = a.substring(0, 1);
        int am = 1;
        for(int i=1; i<a.length(); i++) {
            ch = a.substring(i, i + 1);
            if (ch.equals(oldch)) am++;
            else am = 1;
            if (am > 3) continue;
            if (am > 2) {
                String sub_ch = "";
                String sub_oldch = b.substring(0, 1);
                int sub_am = 1;
                for (int j = 1; j < b.length(); j++) {
                    sub_ch = b.substring(j, j+1);
                    if (ch.equals(sub_ch) && sub_ch.equals(sub_oldch)) sub_am++;
                    else sub_am = 1;
                    if (sub_am > 1) return true;
                    sub_oldch = sub_ch;
                }

            }
            oldch = ch;
        }
        return false;
    }
/**Предположим, что пара одинаковых символов служит концами книги для всех
 символов между ними. Напишите функцию, которая возвращает общее количество
 уникальных символов (книг, так сказать) между всеми парами концов книги.
**/
 public static int countUniqueBooks(String str, char ch_end){
        String end = "" + ch_end;
        int am = 0;
        String uni_list = "";
        String ch = "";
        boolean flag = false;
        for(int i=0; i<str.length(); i++) {
            ch = str.substring(i, i + 1);
            if(ch.equals(end)){
                flag = !flag;
            }
            else if(flag){
                if(!uni_list.contains(ch.toUpperCase())){
                    uni_list += ch.toUpperCase();
                    am++;
                }
            }
        }
        return am;
    }
}
