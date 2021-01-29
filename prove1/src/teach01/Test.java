package teach01;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {
        Boolean isContinue = true;
        while(isContinue) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Set Password:");
            String password = scan.nextLine();
            User user = new User(password);
            //try {
                NSALoginController.hashUserPassword(user);
//            } catch (WeakPasswordException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            System.out.println("\nPassword: " + user.getPassword());
            System.out.println("hashedPassword: " + user.getHashedPassword());
            System.out.println("Salt: " + user.getSalt());

            System.out.print("Enter Password: ");
            String pwd = scan.nextLine();
            user.setPassword(pwd);
            if (NSALoginController.verifyPassword(user)) {
                System.out.println("\nverified");
            } else {
                System.out.println("\nnot verified");
            }
            while(true) {
                System.out.print("Do you want to continue to set password?(y/n):");
                String isEnd = scan.nextLine();
                if (isEnd.equals("y") || isEnd.equals("Y")) {
                    isContinue = true;
                    break;
                } else if (isEnd.equals("n") || isEnd.equals("N")) {
                    isContinue = false;
                    scan.close();
                    break;
                } else {
                    System.out.println("Wrong input. Try again");
                    continue;
                }
            }
        }

    }
}
