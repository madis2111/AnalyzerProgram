import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static BlockingQueue<String> symbolA = new ArrayBlockingQueue<>(100);
    private static BlockingQueue<String> symbolB = new ArrayBlockingQueue<>(100);
    private static BlockingQueue<String> symbolC = new ArrayBlockingQueue<>(100);

    private static String longestA;
    private static String longestB;
    private static String longestC;

    private static int longestAnumber;
    private static int longestBnumber;
    private static int longestCnumber;

    private static final int stringsNumber = 10_000;

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < stringsNumber; i++) {
                try {
                    symbolA.put(generateText("abc", 100_000));
                    symbolB.put(generateText("abc", 100_000));
                    symbolC.put(generateText("abc", 100_000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        Thread aThread = new Thread(() -> {
            for (int i = 0; i < stringsNumber; i++) {
                System.out.println("самое большое количество букв а в строке: " + longestAnumber);
                System.out.println("выполняется цикл №" + i);
                String s = null;
                while (s == null) {
                    s = symbolA.poll();
                }

                int counter = 0;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == 'a') {
                        counter++;
                    }
                }

                if (counter > longestAnumber) {
                    longestAnumber = counter;
                    longestA = s;
                }
            }
            System.out.println("Больше всего букв а: " + longestAnumber); // todo move
        });
        aThread.start();

        Thread bThread = new Thread(() -> {
            for (int i = 0; i < stringsNumber; i++) {
                System.out.println("самое большое количество букв b в строке: " + longestBnumber);
                System.out.println("выполняется цикл №" + i);
                String s = null;
                while (s == null) {
                    s = symbolB.poll();
                }
                int counter = 0;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == 'b') {
                        counter++;
                    }
                }

                if (counter > longestBnumber) {
                    longestBnumber = counter;
                    longestB = s;
                }
            }
            System.out.println("Больше всего букв b: " + longestBnumber);

        });
        bThread.start();

        Thread cThread = new Thread(() -> {
            for (int i = 0; i < stringsNumber; i++) {
                System.out.println("самое большое количество букв c в строке: " + longestCnumber);
                System.out.println("выполняется цикл №" + i);
                String s = null;
                while (s == null) {
                    s = symbolC.poll();
                }
                int counter = 0;
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == 'c') {
                        counter++;
                    }
                }

                if (counter > longestCnumber) {
                    longestCnumber = counter;
                    longestC = s;
                }
            }
            System.out.println("Больше всего букв c: " + longestCnumber); // todo move

        });
        cThread.start();
    }
}