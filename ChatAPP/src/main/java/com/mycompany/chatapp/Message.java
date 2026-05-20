/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lab_services_student
 */
public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageNumber;

    // Use array instead of ArrayList
    private static Message[] sentMessages;
    private static int totalSent = 0;
    private static int arraySize = 0;

    // Call this once from main to set array size
    public static void setMaxMessages(int max) {
        arraySize = max;
        sentMessages = new Message[max];
    }

    public Message(String recipient, String message, int messageNumber) {
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generate 10-digit random ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextLong(9000000000L); // ensures 10 digits
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10; 
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12 && recipient.substring(3).matches("\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = message.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }

    public String createMessageHash() {
        String cleanMsg = message.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0? words[0] : "";
        String lastWord = words.length > 1? words[words.length - 1] : firstWord;

        firstWord = firstWord.replaceAll("[^a-zA-Z0-9]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");
        
        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" +
                      firstWord.toUpperCase() + lastWord.toUpperCase();
        return hash.toUpperCase();
    }

    public String sentMessage(String choice) {
        switch(choice.toLowerCase()) {
            case "send":
                if (totalSent < arraySize) {
                    sentMessages[totalSent] = this;
                    totalSent++;
                    return "Message successfully sent.";
                } else {
                    return "Message limit reached.";
                }
            case "discard":
                return "Press 0 to delete the message.";
                
            case "store":
                storeMessagesToJSON();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalSent; i++) {
            Message m = sentMessages[i];
            sb.append("Message ID: ").append(m.messageID).append("\n")
             .append("Message Hash: ").append(m.messageHash).append("\n")
             .append("Recipient: ").append(m.recipient).append("\n")
             .append("Message: ").append(m.message).append("\n")
             .append("--------------------------\n");
        }
        return sb.toString();
    }

    public int returnTotalMessages() {
        return totalSent;
    }

    // Getters for testing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }    
    
        public static void storeMessagesToJSON() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < totalSent; i++) {
            Message m = sentMessages[i];

            JSONObject obj = new JSONObject();
            obj.put("messageID", m.messageID);
            obj.put("messageHash", m.messageHash);
            obj.put("recipient", m.recipient);
            obj.put("message", m.message);
            obj.put("messageNumber", m.messageNumber);

            jsonArray.put(obj);
        }

        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(jsonArray.toString(4)); // 4 = pretty print indent
            System.out.println("Messages saved to messages.json");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
