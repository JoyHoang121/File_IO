import java.io.*;

public class Read_Person {
    public static void main(String[] args) {
        ghiThongTin();
        ReadThongTin();
    }

    /**
     * Ghi thông tin xuống tập tin bằng Object
     */
    public static void ghiThongTin() {
        Person person = new Person("Joy", 32);
        try {
            FileOutputStream fos = new FileOutputStream("person.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(person);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc thông tin từ tập tin bằng Object
     */
    public static void ReadThongTin() {
        try {
            FileInputStream fis = new FileInputStream("person.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Person p = (Person) ois.readObject();
            System.out.println(p);
            ois.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
