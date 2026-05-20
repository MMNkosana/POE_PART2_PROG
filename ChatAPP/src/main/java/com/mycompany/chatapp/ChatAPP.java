/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

/**
 *
 * @author lab_services_student
 */

import java.util.*;   //importing scanner class
public class ChatAPP {
    
    

    public static void main(String[] args) {
        
        String name;
        String surname;
        String PhoneNumber = null;
        String Password = null;
        
        Scanner input1 = new Scanner(System.in);
        
        System.out.println("Please enter your name");
        name = input1.nextLine();
        System.out.println("Please enter your surname");
        surname = input1.nextLine();
        
        Login UserLogin = new Login(); 
        
        int userAttempts = 3;
        boolean isValid = false;
        String UserName = null; //creating variable to store username
        
        while (userAttempts > 0 && !isValid){
        System.out.println("Please enter username: ");
        UserName = input1.nextLine(); 
       
         if (UserLogin.CheckUserName(UserName)){
               isValid = true; //if username is correctly formatted then the loop will end
               System.out.println("Username accepted");
               UserLogin.UserName = UserName;
         }
         else {
             userAttempts--;
             System.out.println("Username is not correctly formated \n Username should contain underscore with a maximum of 5 characters");
             
             if (userAttempts > 0){
                 System.out.println("You have " + userAttempts + " attempts left");
                 System.out.println("please re-enter your username");
             }
             else {
                 System.out.println("Username not accepted");
             }
         }
        }
        boolean passValid = false;
        int passAttempts = 3;
         
        if (isValid){
//         String Password; //creating variable to store password
         
         while (passAttempts > 0 && !passValid){
         System.out.println("Please enter Password: ");
         Password = input1.nextLine(); 
       
         if (UserLogin.CheckPasswordComplexity(Password)){
               passValid = true; //if password is correctly formatted then the loop will end
               System.out.println("Password accepted");
               UserLogin.Password = Password;
         }
          else {
             passAttempts--;
             System.out.println("Password is not correctly formated \n Password should contain a digit, "
                     + "a capital letter and a special character, with more then 8 characters");
             
             if (passAttempts > 0){
                 System.out.println("You have " + passAttempts + " attempts left");
                 System.out.println("please re-enter your password");
             }
             else {
                 System.out.println("Password not accepted");
             }
          }
         }
        }
        
        boolean cellValid = false;
        int cellAttempts = 3;
        
        if (isValid && passValid){
//         String PhoneNumber;
         
          while (cellAttempts > 0 && !cellValid){
              System.out.println("Please enter your cellphone number");
              PhoneNumber = input1.nextLine();
              
              if (UserLogin.CheckCellPhoneNumber(PhoneNumber)){
               cellValid = true; //if cellphone number is correctly formatted then the loop will end
               System.out.println("Cellphone number accepted");
         }
          else {
             cellAttempts--;
             System.out.println("Cellphone number should contain country code (+27), with 11 digits");
             
             if (cellAttempts > 0){
                 System.out.println("You have " + cellAttempts + " attempts left");
                 System.out.println("please re-enter your cellphone number");
             }
             else {
                 System.out.println("Cellphone number not accepted");
             }
          }
        }
      }
        if (UserLogin.registerUser(UserName, Password, PhoneNumber)){
            System.out.println("Succesfully registered");
        }
            
            System.out.println("===LOGIN===");
            
            int LoginAttempt = 3;
            boolean loggedIn = false;
            
            String loginUserName;
            String loginPassword;
             
        do {
                System.out.println("Please enter your username");
                loginUserName = input1.nextLine();
                
                System.out.println("Please enter your password");
                loginPassword = input1.nextLine();
                
                if (UserLogin.loginUser(loginUserName, loginPassword)){
                    System.out.println(UserLogin.returnLoginStatus(true));
                    loggedIn = true;
                    break;
                }else{
                LoginAttempt--;
                System.out.println("Login failed. Attempts left" + LoginAttempt);
                }
        }
        while (LoginAttempt > 0);
        if (!loggedIn){
            System.out.println(UserLogin.returnLoginStatus(false));
        }
                System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you want to send? ");
        int numMessages = input1.nextInt();
        input1.nextLine();

        Message.setMaxMessages(numMessages);
        
                int menuChoice;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose: ");
            menuChoice = input1.nextInt();

            Message temp = new Message("", "", 0);

            switch(menuChoice) {
                case 1:
                    System.out.println("Use the main flow to send more messages.");
                    break;
                case 2:
                    System.out.println(temp.printMessages());
                    break;
                case 3:
                    System.out.println("Total messages sent: " + temp.returnTotalMessages());
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Coming Soon.");
            }

         int i = 1; // manual counter
         while (i <= numMessages) {
            System.out.println("\n--- Message " + i + " ---");
            
            String recipient;
            String recipientCheck;
           
            // Loop until recipient is valid
            do {
                System.out.print("Enter recipient number: ");
                recipient = input1.nextLine();
               
                Message tempCheck = new Message(recipient, "", 0);
                recipientCheck = tempCheck.checkRecipientCell();
                System.out.println(recipientCheck);
               
            } while (!recipientCheck.equals("Cell phone number successfully captured."));

            System.out.print("Enter message: ");
            String msgText = input1.nextLine();

            Message msg = new Message(recipient, msgText, i);

            System.out.println(msg.checkRecipientCell());
            System.out.println(msg.checkMessageLength());

            if (msg.checkMessageLength().equals("Message ready to send.")) {
                System.out.println("Message Hash: " + msg.createMessageHash());

                System.out.println("\n1. Send Message\n2. Disregard Message\n3. Store Message");
                System.out.print("Choose: ");
                int choice = input1.nextInt();
                input1.nextLine();

                String action = choice == 1? "send" : choice == 2? "discard" : "store";
                String result = msg.sentMessage(action);
                System.out.println(result);

                if (choice == 1) {
                    // Only increment if message was actually sent
                    System.out.println("\n--- Message Details ---");
                    System.out.println("Message ID: " + msg.getMessageID());
                    System.out.println("Message Hash: " + msg.getMessageHash());
                    System.out.println("Recipient: " + msg.getRecipient());
                    System.out.println("Message: " + msg.getMessage());
                    i++; // count this message
                } else {
                    // Don't increment for discard or store
                    System.out.println("Message not sent. Re-enter details for message " + i);
                }
            } else {
                // Message too long, don't increment either
                System.out.println("Message not sent. Re-enter details for message " + i);
            }
          }
        } while (menuChoice!= 3);

        input1.close();
    }
}

