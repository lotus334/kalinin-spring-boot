package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.Objects;

public class App {
    public static void main(String[] args) {

        try {
            String fileName = "item.iml";

            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos); // Можно не использовать буферизацию
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            Item initialItem = new Item("login", "password", "transientName");
            oos.writeObject(initialItem);

            oos.close();

            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Item resultItem = (Item) ois.readObject();

            System.out.println(resultItem);

            if (!Objects.equals(resultItem, initialItem)) {
                throw new RuntimeException();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @AllArgsConstructor
    private static class Item implements Serializable {
        private String login;
        private String password;
        private transient String name;

        @Serial
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();

            String encryptedPassword = password + "_encrypted";
            out.writeObject(encryptedPassword);
        }


        @Serial
        private void readObject(ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            in.defaultReadObject();

            String encryptedPassword = (String) in.readObject();
            this.password = encryptedPassword.replace("_encrypted", "");
        }

    }
}
