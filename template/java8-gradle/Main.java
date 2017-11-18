import java.util.Scanner;
import demo.Handler;

public class Main {
  public static void main(String[] args) {
    Scanner scanIn = new Scanner(System.in);
    new Handler().handle(scanIn.nextLine());
  }
}
