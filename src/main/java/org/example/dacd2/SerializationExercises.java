package org.example.dacd2;

import com.google.gson.Gson;
import org.example.dacd2.model.Movie;
import org.example.dacd2.model.Session;
import org.example.dacd2.model.Theater;

import java.io.*;

public class SerializationExercises {
    /*
        Should define the class for the concepts Movie, Theater and Session.
        A session is a play of movie in a theater.
        Create 2 instances of each class and relate among them.
        Serialize to Json all objects and save then in different files.
     */
    public static class Exercise1 {

        public static void main(String[] args) {
            Movie movie1 = new Movie("El exorcista", 120);
            Movie movie2 = new Movie("Barbie", 114);

            Theater theater1 = new Theater("Yelmo cines", "7palmas");
            Theater theater2 = new Theater("Yelmo cines", "7palmas");

            Session session1 = new Session(movie1, theater1, "03-10-2023 18:00");
            Session session2 = new Session(movie2, theater2, "03-10-2023 20:30");

            Gson gson = new Gson();

            guardarJSONEnArchivo("movie1.json", gson.toJson(movie1));
            guardarJSONEnArchivo("movie2.json", gson.toJson(movie2));
            guardarJSONEnArchivo("theater1.json", gson.toJson(theater1));
            guardarJSONEnArchivo("theater2.json", gson.toJson(theater2));
            guardarJSONEnArchivo("session1.json", gson.toJson(session1));
            guardarJSONEnArchivo("session2.json", gson.toJson(session2));
        }

        private static void guardarJSONEnArchivo(String fileName, String jsonContent) {
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(jsonContent);
            } catch (IOException e) {
                System.err.println("Error al guardar el archivo " + fileName + ": " + e.getMessage());
            }
        }
    }

    /*
        Deserialize the objects created in exercise 1.
        Now serialize them using ObjectOutputStream
     */
    public static class Exercise2 {

        public static void main(String[] args) {
            try {
                Gson gson = new Gson();

                try (FileReader movieReader = new FileReader("movie1.json");
                     FileReader theaterReader1 = new FileReader("theater1.json");
                     FileReader sessionReader1 = new FileReader("session1.json");
                     ObjectOutputStream movieOutput = new ObjectOutputStream(new FileOutputStream("movie1.dat"));
                     ObjectOutputStream theaterOutput = new ObjectOutputStream(new FileOutputStream("theater1.dat"));
                     ObjectOutputStream sessionOutput = new ObjectOutputStream(new FileOutputStream("session1.dat"))) {

                    Movie movie1 = gson.fromJson(movieReader, Movie.class);
                    Theater theater1 = gson.fromJson(theaterReader1, Theater.class);
                    Session session1 = gson.fromJson(sessionReader1, Session.class);

                    movieOutput.writeObject(movie1);
                    theaterOutput.writeObject(theater1);
                    sessionOutput.writeObject(session1);

                } catch (IOException e) {
                    System.err.println("Error al guardar el archivo ");
                }
            } catch (Exception e) {
                System.err.println("Error al guardar el archivo ");
            }
        }
    }

    /*
       Deserialize the objects from the binary files created in exercise 2.
    */
    public static class Exercise3 {

        public static void main(String[] args) {
            try {
                try (FileInputStream movieInput = new FileInputStream("movie1.dat");
                     ObjectInputStream movieObjectInput = new ObjectInputStream(movieInput);
                     FileInputStream theaterInput = new FileInputStream("theater1.dat");
                     ObjectInputStream theaterObjectInput = new ObjectInputStream(theaterInput);
                     FileInputStream sessionInput = new FileInputStream("session1.dat");
                     ObjectInputStream sessionObjectInput = new ObjectInputStream(sessionInput)) {

                    Movie movieFromFile = (Movie) movieObjectInput.readObject();
                    Theater theaterFromFile = (Theater) theaterObjectInput.readObject();
                    Session sessionFromFile = (Session) sessionObjectInput.readObject();

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error: " + e.getMessage());
                    System.err.println("Error al guardar el archivo ");
                }
            } catch (Exception e) {
                System.err.println("Error al guardar el archivo ");
            }
        }
    }
}