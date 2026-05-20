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
public class MessageTest {
    
    public MessageTest() {
    }

    /**
     * Test of setMaxMessages method, of class Message.
     */
    @Test
    public void testSetMaxMessages() {
        Message.setMaxMessages(10);
    }
        @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
        String result = msg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "a".repeat(260); // 260 chars
        Message msg = new Message("+27718693002", longMsg, 1);
        String result = msg.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }

    @Test
    public void testRecipientValid() {
        Message msg = new Message("+27718693002", "Test message", 1);
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientInvalid() {
        Message msg = new Message("08575975889", "Test message", 1);
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashCorrect() {
        // Force ID to start with 00 so hash = 00:0:HITONIGHT
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
 
        String hash = msg.createMessageHash();
        assertEquals("Hash should end with HITONIGHT", hash.endsWith("HITONIGHT"));
        assertEquals("Hash should start with 00:0:", hash.startsWith("00:0:"));
    }

    @Test
    public void testSendMessageOption() {
        Message msg = new Message("+27718693002", "Test", 1);
        String result = msg.sentMessage("send");
        assertEquals("Message successfully sent.", result);
        assertEquals(1, msg.returnTotalMessages());
    }

    @Test
    public void testDiscardMessageOption() {
        Message msg = new Message("+27718693002", "Test", 1);
        String result = msg.sentMessage("discard");
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testStoreMessageOption() {
        Message msg = new Message("+27718693002", "Test", 1);
        String result = msg.sentMessage("store");
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Message ID should be 10 digits", msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }
}
