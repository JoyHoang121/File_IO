import java.io.*;
import java.time.LocalDate;

public class ReadFile_Order {
    public static void main(String[] args) {
//        ghiThongTinXuongFile();
//        readInfoFromFile();
//        order();
        //       readInfo();
        //       ghiNhieuHoaDon();
        readThongTinNhieuHoaDon();

    }

    //ghi thông tin xuống 1 file
    public static void ghiThongTinXuongFile() {
        try {
            FileOutputStream fos = new FileOutputStream("data.bin");
            //Bọc lại bằng BufferOutPutStream để nó ghi nhanh hơn
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write("Hello".getBytes());
            bos.write("\n".getBytes());
            bos.write("How are you today".getBytes());
            bos.write("\n".getBytes());

            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //đọc thông tin từ 1 file
    public static void readInfoFromFile() {
        try {
            FileInputStream fis = new FileInputStream("data.bin");
            //bọc lại bằng bufferInputStream để nó đọc nhanh hơn
            BufferedInputStream bis = new BufferedInputStream(fis);
            //read 1 byte
            int ch;
            ch = bis.read();
            while (ch != -1) {
                System.out.print((char) ch);
                ch = bis.read();
            }

            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ghi lớp đối tượng hóa đơn vào 1 file

    /**
     * - Tạo 1 class với 1 đối tượng hóa đơn (Order)
     */
    public static void order() {
        //khởi tạo hóa đơn
        Order ord = new Order(LocalDate.now(), "Ken", 500d);
        try {
            FileOutputStream fos = new FileOutputStream("order.bin");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(ord.getCustomer());
            dos.writeUTF(ord.getDate().toString());
            dos.writeDouble(ord.getTotal());
            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * - Đọc dữ liệu từ 1 đối tượng hóa đơn
     * Muốn đọc dc tập tin nhị phân, phải biết tập tin đó chứa j bên trong, thì chúng ta mới đọc lên được
     */
    public static void readInfo() {
        try {
            FileInputStream fis = new FileInputStream("order.bin");
            DataInputStream dis = new DataInputStream(fis);
            String name = dis.readUTF();
            String date = dis.readUTF();
            double total = dis.readDouble();

            LocalDate ld = LocalDate.parse(date);
            Order order = new Order(ld, name, total);
            dis.close();
            System.out.println(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ghi nhiều đối tượng (nhiều hóa đơn (order) xuống tập tin multiOrder.bin
     */

    public static void ghiNhieuHoaDon() {
        Order[] orders = new Order[]{
                new Order(LocalDate.now(), "Ken", 100d),
                new Order(LocalDate.now(), "Hannie", 200d),
                new Order(LocalDate.now(), "Joy", 300d),
                new Order(LocalDate.now(), "Jasson", 400d),
                new Order(LocalDate.now(), "Mike", 500d),
        };
        try {
            FileOutputStream fos = new FileOutputStream("multiOrder.bin");
            DataOutputStream dos = new DataOutputStream(fos);
            for (Order ord : orders) {
                dos.writeUTF(ord.getCustomer());
                dos.writeUTF(ord.getDate().toString());
                dos.writeDouble(ord.getTotal());
            }
            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc thông tin nhiều đối tượng (nhiều hóa đơn (order) từ tập tin multiOrder.bin
     */
    public static void readThongTinNhieuHoaDon() {
        try {
            FileInputStream fis = new FileInputStream("multiOrder.bin");
            DataInputStream dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                String name = dis.readUTF();
                String date = dis.readUTF();
                double total = dis.readDouble();

                LocalDate ld = LocalDate.parse(date);
                Order order = new Order(ld, name, total);
                System.out.println(order);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
