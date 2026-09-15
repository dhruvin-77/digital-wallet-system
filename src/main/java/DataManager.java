import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String FILE_NAME = "users.dat";

    private DataManager() {
    }

    public static List<User> loadUsers() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            return (List<User>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Could not load saved users."
            );

            return new ArrayList<>();
        }
    }

    public static void saveUsers(List<User> users) {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            output.writeObject(users);

        } catch (IOException e) {

            System.out.println(
                    "Could not save user data."
            );
        }
    }
}