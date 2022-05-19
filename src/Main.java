import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to TheBestCalc! Please, input parameters to calculate. You are allowed to input Latin or Arabic characters from 1 to 10.");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();
        calc(inputValue);
    }

    static void calc(String input) throws Exception {
        String[] token = input.split(" ");
        if (token.length < 2) {
            throw new Exception("Input less than 2 arguments. Could you, please, input no more than 2 number");
        }
        if (token.length > 3) {
            throw new Exception("Input more than 2 arguments. Could you, please, input no more than 2 number");
        }
        String a = token[0];
        String b = token[2];
        if ((isLatin(a, LatinNum.class) && (isLatin(b, LatinNum.class)))) {
            int x = convertFromLatinToArabic(a);
            int y = convertFromLatinToArabic(b);
            if (checkLegalBoard(x) && checkLegalBoard(y)){
                switch (token[1]) {
                    case ("+"):
                    System.out.println("Result is " + convertFromArabicToLatin(x + y));
                    break;
                    case ("*"):
                    System.out.println("Result is " + convertFromArabicToLatin(x * y));
                    break;
                    case ("-"):
                        int z1 = x - y;
                        if (z1 < 1) {
                            throw new Exception("Input illegal arguments. Result can't be less 1.");
                        }
                    System.out.println("Result is " + convertFromArabicToLatin(z1));
                    break;
                    case ("/"):
                        int z2 = x / y;
                        if (z2 < 1) {
                            throw new Exception("Input illegal arguments. Result can't be less 1.");
                        }
                    System.out.println("Result is " + convertFromArabicToLatin(z2));
                    break;
                    default:
                    throw new Exception("Input illegal arithmetic operator.");
                }
            } else {
                throw new Exception("Input illegal arguments. Could you, please, input numbers from I to X.");
            }
            return;
        }
        if (isArabian(a) && isArabian(b)) {
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            if (checkLegalBoard(x) && checkLegalBoard(y)){
                switch (token[1]) {
                case ("+"):
                    System.out.println("Result is " + (x + y));
                    break;
                case ("*"):
                    System.out.println("Result is " + (x * y));
                    break;
                case ("-"):
                    int z1 = x - y;
                    System.out.println("Result is " + z1);
                    break;
                case ("/"):
                    int z2 = x / y;
                    System.out.println("Result is " + z2);
                    break;
                default:
                    throw new Exception("Input illegal arithmetic operator.");
                }
            }
        } else {
            throw new Exception("Input illegal arguments. Could you, please, input numbers Arabic numbers from 1 to 10 in format (1 + 4) or Latin numbers from I to X in format (I + IV).");
        }
    }

    static Integer convertFromLatinToArabic (String argument) {
        TreeMap<String, Integer> latinNum = new TreeMap<String, Integer>();

        latinNum.put("X", 10);
        latinNum.put("IX", 9);
        latinNum.put("VIII", 8);
        latinNum.put("VII", 7);
        latinNum.put("VI", 6);
        latinNum.put("V", 5);
        latinNum.put("IV", 4);
        latinNum.put("III", 3);
        latinNum.put("II", 2);
        latinNum.put("I", 1);

        Integer x = latinNum.get(argument);
        return x;
    }

    final static String convertFromArabicToLatin (int num){
        final TreeMap<Integer, String> numMap = new TreeMap<Integer, String>();

        numMap.put(100, "C");
        numMap.put(90, "XC");
        numMap.put(50, "L0");
        numMap.put(40, "XL");
        numMap.put(10, "X");
        numMap.put(9, "IX");
        numMap.put(5, "V");
        numMap.put(4, "IV");
        numMap.put(1, "I");

        int y = numMap.floorKey(num);
        if (num == y) {
            return numMap.get(num);
        }
        return numMap.get(y) + convertFromArabicToLatin(num - y);
    }

    static <E extends Enum<E>> boolean isLatin (String symbol, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    static boolean isArabian (String symbol) {
        try {
            int c = Integer.parseInt(symbol);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    static boolean checkLegalBoard (Integer arabicNum) {
        if (arabicNum >= 1 || arabicNum <= 10) {
            return true;
        }
        return false;
    }

}
