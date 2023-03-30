package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static String[] OPTIONS =  {"rock", "fire", "scissors", "snake", "human", "tree", "wolf", "sponge", "paper", "air", "water", "dragon", "devil", "lightning", "gun"};
    static List<String> OPTIONLIST = Arrays.asList(OPTIONS);
    static List<String> choosen;
    public static void main(String[] args) {
        System.out.println(OPTIONLIST);
        Scanner s = new Scanner(System.in);
        String[] stored = {"scissors", "rock", "paper"};
        System.out.println("Enter your name: ");
        String name = s.nextLine();
        System.out.printf("Hello, %s%n", name);
        File rating = new File("D:\\Programming\\Java\\Rock-Paper-Scissors (Java)\\Rock-Paper-Scissors (Java)\\task\\src\\rockpaperscissors\\rating.txt");
        Scanner fileScanner = null;
        try {
             fileScanner= new Scanner(rating);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        String[] ratings = null;
        while(fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (line.contains(name)) {
                ratings = line.split(" ");
            }
        }
        if (ratings == null)  {
            ratings = new String[2];
            ratings[0] = name;
            ratings[1] = "350";
        }
        String level =  s.nextLine();
        if (level.length() != 0)
            choosen = Arrays.asList(level.split(","));
        else
            choosen = Arrays.asList(stored);
        while (true) {
            System.out.println("Okay, let's start");
            String input = s.next().toLowerCase();
            String computerChoice = chooseRandom(stored);
            if (input.contains("!rating"))
                System.out.printf("Your rating: %s\n", ratings[1]);
            else if (!input.contains("!exit"))
                gameplay(input, computerChoice, ratings);
            else{
                System.out.println("Bye!");
                break;
            }

        }
    }

    private static void gameplay(String input, String computerChoice, String[] ratings) {
        if (!choosen.contains(input)) {
            System.out.println("Invalid input");
            return;
        }
        if (input.equals(computerChoice)) {
            System.out.printf("There is a draw (%s)%n",input);
            ratings[1] = String.valueOf(Integer.valueOf(ratings[1]) + 50);
            return;
        }
        int indexInput = OPTIONLIST.indexOf(input);
        int indexComputerChoice = OPTIONLIST.indexOf(computerChoice);
        if (indexInput <= 7) {
            if ( indexComputerChoice > indexInput && indexComputerChoice <= (indexInput + 7)) {
                System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
                ratings[1] = String.valueOf(Integer.valueOf(ratings[1]) + 100);

            }
            else {
                System.out.printf("Sorry, but the computer chose %s%n", computerChoice);
            }
        }
        else {
            if ((indexComputerChoice > indexInput && indexComputerChoice <= OPTIONLIST.size()) || (indexComputerChoice <indexInput && indexComputerChoice <= (indexInput + 7) % OPTIONLIST.size())) {
                System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
                ratings[1] = String.valueOf(Integer.valueOf(ratings[1]) + 100);
            }else {
                System.out.printf("Sorry, but the computer chose %s%n", computerChoice);
            }
        }
    }

    private static String chooseRandom(String[] stored) {
        Random random =new Random();
        int index = Math.abs(random.nextInt(choosen.size())) % choosen.size();
        return choosen.get(index);
    }
}
