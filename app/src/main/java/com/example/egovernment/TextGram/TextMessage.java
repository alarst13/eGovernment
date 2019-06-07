package com.example.egovernment.TextGram;

public class TextMessage {

        private String sender;
        private String receiver;
        private String message;
        private int number;

        public TextMessage(String sender, String receiver, String message, int number) {
            this.sender = sender;
            this.receiver = receiver;
            this.message = message;
            this.number = number;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

}
