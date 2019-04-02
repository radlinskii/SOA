package main;

import client.HelloWorld;
import client.HelloWorldService;

import javax.imageio.ImageIO;
import javax.xml.ws.BindingProvider;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static void setCred(HelloWorld service) {
        BindingProvider provider = (BindingProvider) service;
        provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "igi");
        provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "igi");
    }

    private static byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }

    public static void main(String[] args) throws IOException {
        HelloWorldService service = new HelloWorldService();
        HelloWorld hello = service.getHelloWorldPort();
        setCred(hello);

        byte[] student1Avatar = extractBytes("./soap-connector/src/main/java/main/avatar_student1.bmp");
        System.out.println(Arrays.toString(student1Avatar));

        List<String> l1= new ArrayList<>();
        l1.add("SOA");
        l1.add("BAZY");

        List<String> l2= new ArrayList<>();
        l2.add("SOA");
        l2.add("HD");

        List<String> l3= new ArrayList<>();
        l3.add("BAZY");
        l3.add("HD");

        System.out.println("Dodawanie studenta1:\n" + hello.addStudent("ignacy", 321, "EAIIB", 6, l1, "avatar ignacego"));
        System.out.println("Dodawanie studenta2:\n" + hello.addStudent("agnieszka", 123, "WIET", 5, l2, "avatar agnieszki"));
        System.out.println("Dodawanie studenta3:\n" + hello.addStudent("kuba", 832, "EAIIB", 7, l3, "avatar kuby"));
        System.out.println();

        System.out.println("wyciaganie studenta przez studentCardId");
        System.out.println("getStudentById(123):\n" + hello.getStudentById(123));
        System.out.println();
        System.out.println("getStudentById(321):\n" + hello.getStudentById(321));
        System.out.println();

        System.out.println("listowanie wszysktich studentow:\n" + hello.list("", "") +"\n");
        System.out.println("listowanie studentow z wydzialu EAIIB:\n" + hello.list("EAIIB", "") +"\n");
        System.out.println("listowanie studentow ktorzy chodza na przedmiot SOA:\n" + hello.list("", "SOA") +"\n");
        System.out.println();

        System.out.println("edytowanie studenta");
        System.out.println("zmiana wydzialu studenta o id 832 na WIET");
        System.out.println("getstudentById przed zmiana: " + hello.getStudentById(832));
        System.out.println("response editStudent:        " + hello.editStudent(832, null, null, "WIET", null, null, null));
        System.out.println("getstudentById po zmianie:   " + hello.getStudentById(832));
        System.out.println();

    }
}
