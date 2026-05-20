/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author lab_services_student
 */
public class Login { //creating login class outside main method
    
    String UserName;
    String Password;
    String loginUserName;
    String PhoneNumber;
    String loginPassword;
    public static String storedUserName;
    public static String storedPassword;
    public static String storedPhone;
    
    public boolean CheckUserName(String UserName){ //method 1
        
        if (UserName.contains("_") && UserName.length()<=5 ){
            return true;
        
        }
        else {
            return false;
        }
    
    }
    public boolean CheckPasswordComplexity(String Password){ //method 2
        
        boolean HasNUM = false;
        boolean HasCAP = false;
        boolean HasSpecChar = false;
        char i;
        int a;
        
        if (Password.length()<=7){ //checking password length
            return false;
        }
 
        for(a = 0; a < Password.length(); a++){ //checking characters in password
           
            i = Password.charAt(a);
            
            if (Character.isDigit(i)){
                HasNUM = true;
            }
            else if (Character.isUpperCase(i)){
                HasCAP = true;
            }
            else if (!Character.isLetterOrDigit(i)){ //checking for special characters in password
                HasSpecChar = true;
            }
        }
        return HasNUM && HasCAP && HasSpecChar;
    
    }
    
    public boolean CheckCellPhoneNumber(String PhoneNumber){// method 3
        
        if (PhoneNumber.contains("+27") && PhoneNumber.length()==12){
            return true;    
        }
        return false;
        
    }
    
    public boolean registerUser(String UserName, String Password, String PhoneNumber){
        
       if (!CheckUserName(UserName)) return false;
       if (!CheckPasswordComplexity(Password)) return false;
       if (!CheckCellPhoneNumber(PhoneNumber)) return false;
       
         storedPhone = PhoneNumber;     
         storedUserName = UserName;
         storedPassword = Password;
       return true;
    }
    
         
    public boolean loginUser(String UserName,String Password){
        return UserName.equals(storedUserName) && Password.equals(storedPassword);
        
    }
       
    
    public String returnLoginStatus(boolean success){
        return success ? "Login Successful" : "Login Failed";
    }
    
}
