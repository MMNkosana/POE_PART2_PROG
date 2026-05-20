/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class LoginTest {
    
    public LoginTest() {
    }

    /**
     * Test of CheckUsername method, of class Login.
     */
    
    Login LoginTest = new Login ();
    
    @Test
    public void testCheckUsername() {
        
        assertEquals(true, LoginTest.CheckUserName("hv_ko"));
    }
    @Test
    public void testCheckUsernameWithoutUnderscore(){
        
        assertEquals(false, LoginTest.CheckUserName("hvko"));
    }
        @Test
    public void TestPasswordLength(){
        
        assertEquals(true, LoginTest.CheckPasswordComplexity("R0r0telo!"));
    }
    @Test
    public void CheckPasswordCharacters(){
        
        assertEquals(false, LoginTest.CheckPasswordComplexity("Rootcause1"));
    }
    @Test
    public void CheckPhoneNumberChar1(){
        
        assertEquals(false, LoginTest.CheckCellPhoneNumber("08966553"));
    }


    
}