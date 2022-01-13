import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.lang.*;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("№1"+" "+Arrays.toString(encrypt("Hello")));
        System.out.println("№1"+" "+decrypt(new int[] {72,29,7,0,3}));
        System.out.println("№2"+" "+canMove("Bishop", "A7", "G1"));
        System.out.println("№3"+" "+canComplete("butl", "beaulifut"));
        System.out.println("№4"+" "+sumDigitProd(new int[] {16,21,10}));
        System.out.println("№5"+" "+sameVowelGroup(new String[] {"vae","eav", "value"}));
        System.out.println("№6"+" "+validateCard(1234567890123451L));
        System.out.println("№7"+" "+numToEng(129));
        System.out.println("№7"+" "+numToRus(129));
        System.out.println("№8"+" "+getSha256Hash("Fluffy@home"));
        System.out.println("№9"+" "+correctTitle("jOn Snow, King IN thE noRth."));
        System.out.println("№10 \n"+" "+hexLattice(19));
    }

    /*№1Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение.
Первая буква строки или первый элемент массива представляет собой символьный код этой буквы.
Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y.
 */
    public static int[] encrypt(String str){
        int[] arr = new int[str.length()];
        arr[0]=str.charAt(0);
        for (int i = 0; i < str.length()-1; i++) {
            arr[i+1]= str.charAt(i+1)-str.charAt(i);
        }
        return arr;
    }
    public static String decrypt(int[] arr){
        String str = ""+(char)arr[0];
        int crpt = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            crpt+=arr[i+1];
            str+=(char)(crpt);
        }
        return str;
    }
/*№2.Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого сделать.
Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король".
*/
    public static boolean canMove(String fig, String stP, String endP){
        stP=stP.toUpperCase();
        endP=endP.toUpperCase();
        if (!(stP.matches("[A-H][1-8]") && endP.matches("[A-H][1-8]"))){
            System.out.println("exit");
            return false;
        }
        switch (fig){
            case "Pawn": //пешка ходит по вертикали на 1 клетку(по 2 клетки в начале партии)
                if (stP.charAt(0)==endP.charAt(0) && (Math.abs(stP.charAt(1)-endP.charAt(1))<=2)) {
                    return true;
                }
                break;
            case "Knight": //конь по траектории "Г"
                if ((Math.abs(stP.charAt(0)-endP.charAt(0)) == 1 && Math.abs(stP.charAt(1)-endP.charAt(1)) == 2) ||
                        (Math.abs(stP.charAt(0)-endP.charAt(0)) == 2 && Math.abs(stP.charAt(1)-endP.charAt(1)) == 1)){
                    return true;
                }
                break;
            case "Rook": //ладья может двигаться по вертикали и горизонтали
                if (((stP.charAt(0) == endP.charAt(0)) && (stP.charAt(1) != endP.charAt(1))) ||
                        ((stP.charAt(0) != endP.charAt(0)) && (stP.charAt(1) == endP.charAt(1)))){
                    return true;
                }
                break;
            case "Bishop": //слон может двигаться только по даигонали
                if (Math.abs(stP.charAt(0)-endP.charAt(0)) == Math.abs(stP.charAt(1) - endP.charAt(1))){
                    return true;
                }
                break;
            case "Queen": //Ферзь ходит на любое число полей по вертикали,горизонтали или диагонали-соединяет в себе ходы ладьи и слона
                if((Math.abs(stP.charAt(0)-endP.charAt(0)) == Math.abs(stP.charAt(1) - endP.charAt(1))) ||
                        ((stP.charAt(0) == endP.charAt(0)) && (stP.charAt(1) != endP.charAt(1))) ||
                        ((stP.charAt(0) != endP.charAt(0)) && (stP.charAt(1) == endP.charAt(1)))){
                    return true;
                }
                break;
            case "King": // король может ходить на соседнее поле в любом направлении
                if (Math.abs(stP.charAt(0)-endP.charAt(0))<=1 &&  Math.abs(stP.charAt(1) - endP.charAt(1))<=1){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
/*№3.Входная строка может быть завершена, если можно добавить дополнительные буквы,
и никакие буквы не должны быть удалены, чтобы соответствовать слову.
Кроме того, порядок букв во входной строке должен быть таким же, как и порядок букв в последнем слове.
Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено.
*/
    public static boolean canComplete(String input, String output){
        char[] chars = input.toCharArray();
        int startOfSearch = 0;
        for (char c : chars) {
            if (output.indexOf(String.valueOf(c), startOfSearch) != -1)
                startOfSearch = output.indexOf(String.valueOf(c), startOfSearch) + 1;
            else
                return false;
        }
        return true;
    }
/*№4.Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе и возвращает
произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру. */
    public static int sumDigitProd(int[] arr){
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        while (sum > 9){
            int m =1;
            while (sum >0){
                m*=sum%10;
                sum/=10;
            }
            sum=m;
        }
        return sum;
    }
/*№5.Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в любом порядке и / или количестве),
 что и первое слово, включая первое слово.*/
    public static ArrayList<String> sameVowelGroup(String[] strs) {
        String[] Leters = new String[]{"a", "e", "u", "i", "o"};
        String Vowels = "";
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Leters.length; i++) {
            if (strs[0].contains(Leters[i]) && !Vowels.contains(Leters[i])) {
                Vowels += Leters[i];
            }
        }

        if (Vowels.length() > 0) {
            result.add(strs[0]);
        } else {
            return result;
        }

        for (int i = 1; i < strs.length; i++) {
            boolean check = true;
            for (int j = 0; j < Vowels.length(); j++) {
                if (!strs[i].contains(String.valueOf(Vowels.charAt(j)))) {
                    check = false;
                    break;
                }
            }
            if (check) result.add(strs[i]);
        }
        return result;
    }
/*6.	Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если это число является действительным номером кредитной карты, а в противном случае-false.
Номера кредитных карт должны быть длиной от 14 до 19 цифр и проходить тест Луна, описанный ниже:
– Удалите последнюю цифру (это"контрольная цифра").
– Переверните число.
– Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
– Добавьте все цифры.
– Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен контрольной цифре из Шага 1.
*/
public static boolean validateCard(long init){
    String c =  Long.toString(init);
    if (c.length() < 14 && c.length()>19){
        return false;
    }
    char checkDigit = c.charAt(c.length()-1);
    int[] card = new int[c.length()-1];
    int sum=0;
    for (int i = 0; i <c.length()-1; i++) {
        card[i]=c.charAt(c.length()-2-i)-48;
        if (i%2==1){
            card[i]*=2;
            if (card[i]>9){
                int tmp = 0;
                while (card[i]>0){
                    tmp+=card[i]%10;
                    card[i]/=10;
                }
                card[i]=tmp;
            }
        }
        sum+=card[i];
    }

    if (10-sum%10 == (checkDigit-48)){
        return true;
    }
    return false;
}
/*№7.Напишите функцию, которая принимает положительное целое число от 0 до 999 включительно
и возвращает строковое представление этого целого числа, написанное на английском языке. */
    public static String numToEng(int num) {
        String str = "";
        if (num == 0) return "zero";
        switch (num / 100) {
            case 1: {
                str += "one hundred ";
                break;
            }
            case 2: {
                str += "two hundred ";
                break;
            }
            case 3: {
                str += "three hundred ";
                break;
            }
            case 4: {
                str += "four hundred ";
                break;
            }
            case 5: {
                str += "five hundred ";
                break;
            }
            case 6: {
                str += "six hundred ";
                break;
            }
            case 7: {
                str += "seven hundred ";
                break;
            }
            case 8: {
                str += "eight hundred ";
                break;
            }
            case 9: {
                str += "nine hundred ";
                break;
            }
        }
        switch (num / 10 % 10) {
            case 1: {
                switch (num % 10) {
                    case 0: {
                        str += "ten";
                        return str;
                    }
                    case 1: {
                        str += "eleven";
                        return str;
                    }
                    case 2: {
                        str += "twelve";
                        return str;
                    }
                    case 3: {
                        str += "thirteen";
                        return str;
                    }
                    case 4: {
                        str += "fourteen";
                        return str;
                    }
                    case 5: {
                        str += "fifteen";
                        return str;
                    }
                    case 6: {
                        str += "sixteen";
                        return str;
                    }
                    case 7: {
                        str += "seventeen";
                        return str;
                    }
                    case 8: {
                        str += "eighteen";
                        return str;
                    }
                    case 9: {
                        str += "nineteen";
                        return str;
                    }
                }
            }
            case 2: {
                str += "twenty ";
                break;
            }
            case 3: {
                str += "thirty ";
                break;
            }
            case 4: {
                str += "forty ";
                break;
            }
            case 5: {
                str += "fifty ";
                break;
            }
            case 6: {
                str += "sixty ";
                break;
            }
            case 7: {
                str += "seventy ";
                break;
            }
            case 8: {
                str += "eighty ";
                break;
            }
            case 9: {
                str += "ninety ";
                break;
            }
        }
        switch (num % 10) {
            case 1: {
                str += "one";
                break;
            }
            case 2: {
                str += "two";
                break;
            }
            case 3: {
                str += "three";
                break;
            }
            case 4: {
                str += "four";
                break;
            }
            case 5: {
                str += "five";
                break;
            }
            case 6: {
                str += "six";
                break;
            }
            case 7: {
                str += "seven";
                break;
            }
            case 8: {
                str += "eight";
                break;
            }
            case 9: {
                str += "nine";
                break;
            }
        }
        return str;
    }
/*Для русского*/
    public static String numToRus(int num) {
        String str = "";
        if (num == 0) return "ноль";
        switch (num / 100) {
            case 1: {
                str += "сто ";
                break;
            }
            case 2: {
                str += "двести ";
                break;
            }
            case 3: {
                str += "триста ";
                break;
            }
            case 4: {
                str += "четыреста ";
                break;
            }
            case 5: {
                str += "пятьсот ";
                break;
            }
            case 6: {
                str += "шестьсот ";
                break;
            }
            case 7: {
                str += "семьсот ";
                break;
            }
            case 8: {
                str += "восемьсот ";
                break;
            }
            case 9: {
                str += "девятьсот ";
                break;
            }
        }
        switch (num / 10 % 10) {
            case 1: {
                switch (num % 10) {
                    case 0: {
                        str += "десять";
                        return str;
                    }
                    case 1: {
                        str += "одиннадцать";
                        return str;
                    }
                    case 2: {
                        str += "двенадцать";
                        return str;
                    }
                    case 3: {
                        str += "тринадцать";
                        return str;
                    }
                    case 4: {
                        str += "четырнадцать";
                        return str;
                    }
                    case 5: {
                        str += "пятнадцать";
                        return str;
                    }
                    case 6: {
                        str += "шестнадцать";
                        return str;
                    }
                    case 7: {
                        str += "семнадцать";
                        return str;
                    }
                    case 8: {
                        str += "восемьнадцать";
                        return str;
                    }
                    case 9: {
                        str += "двадцать";
                        return str;
                    }
                }
            }
            case 2: {
                str += "двадцать ";
                break;
            }
            case 3: {
                str += "тридцать ";
                break;
            }
            case 4: {
                str += "сорок ";
                break;
            }
            case 5: {
                str += "пятьдесят ";
                break;
            }
            case 6: {
                str += "шестьдесят ";
                break;
            }
            case 7: {
                str += "семьдесят ";
                break;
            }
            case 8: {
                str += "восемьдесят ";
                break;
            }
            case 9: {
                str += "девяносто ";
                break;
            }
        }
        switch (num % 10) {
            case 1: {
                str += "один";
                break;
            }
            case 2: {
                str += "два";
                break;
            }
            case 3: {
                str += "три";
                break;
            }
            case 4: {
                str += "четыре";
                break;
            }
            case 5: {
                str += "пять";
                break;
            }
            case 6: {
                str += "шесть";
                break;
            }
            case 7: {
                str += "семь";
                break;
            }
            case 8: {
                str += "восемь";
                break;
            }
            case 9: {
                str += "девять";
                break;
            }
        }
        return str;
    }
/*№8.Хеш-алгоритмы легко сделать одним способом, но по существу невозможно сделать наоборот.
Например, если вы хешируете что-то простое, например, password123, это даст вам длинный код, уникальный
для этого слова или фразы. В идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и
вернуться к слову или фразе, с которых вы начали.
Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки. Хеш должен быть отформатирован
в виде шестнадцатеричной цифры.
*/
    public static String getSha256Hash(String s) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return convertToString(hash);
    }

    public static String convertToString(byte[] hash) {
        String hexString = "";
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString += '0';
            }
            hexString += hex;
        }
        return hexString;
    }
/*№9.Напишите функцию, которая принимает строку и возвращает строку с правильным регистром для заголовков
символов в серии "Игра престолов".
Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый символ
в верхнем регистре, а остальные-в Нижнем.
*/
    public static StringBuilder correctTitle(String string){
        String[] strings=string.split(" ");
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            strings[i]=strings[i].toLowerCase();
            if (strings[i].equals("and") || strings[i].equals("the") || strings[i].equals("of") || strings[i].equals("in")){
                tmp.append(strings[i]+" ");
            }
            else {
                tmp.append(strings[i].toUpperCase().charAt(0) + strings[i].substring(1) + " ");
            }
        }
        return tmp;
    }
/*10.	Как указано в онлайн-энциклопедии целочисленных последовательностей:
Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка имеет 6 соседей.
Центрированное шестиугольное число - это центрированное фигурное число, представляющее шестиугольник
с точкой в центре и всеми другими точками, окружающими центральную точку в шестиугольной решетке.
Напишите функцию, которая принимает целое число n и возвращает "недопустимое",
если n не является центрированным шестиугольным числом или его иллюстрацией в виде многострочной
прямоугольной строки в противном случае.
*/
    public static String hexLattice(int n) {
        int num = 1;
        int i = 1;
        String res = "";

        for(; n > num; num = 3 * i * (i - 1) + 1) {
            ++i;
        }

        int l = i;
        if (n != num) {
            res = "invalid";
            return res;
        } else {
            int b;
            while(l < i * 2 - 1) {
                for(b = 0; b < i * 2 - 1 - l; ++b) {
                    res = res + "  ";
                }

                for(b = 0; b < l; ++b) {
                    res = res + " o  ";
                }

                res = res + "\n";в
                ++l;
            }
            while(l >= i) {
                for(b = 0; b < i * 2 - 1 - l; ++b) {
                    res = res + "  ";
                }
                for(b = l; b > 0; --b) {
                    res = res + " o  ";
                }
                res = res + "\n";
                --l;
            }
            return res;
        }

    }
}

