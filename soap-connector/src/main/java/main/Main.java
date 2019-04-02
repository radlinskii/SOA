package main;

import client.StudentController;
import client.StudentControllerService;
import org.apache.commons.codec.binary.Base64;

import javax.xml.ws.BindingProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void setCred(StudentController service) {
        BindingProvider provider = (BindingProvider) service;
        provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "igi");
        provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "igi");
    }

    private static byte[] readImage(String imageName) throws IOException {
        File file = new File(imageName);
        FileInputStream imageInFile = new FileInputStream(file);
        byte[] imageData = new byte[(int) file.length()];
        imageInFile.read(imageData);

        return imageData;
    }

    private static void writeImage(String avatar, String filepath) throws IOException {
        byte[] data = Base64.decodeBase64(avatar);

        FileOutputStream imageOutFile = new FileOutputStream(filepath);
        imageOutFile.write(data);

        imageOutFile.close();
    }

    public static void main(String[] args) throws IOException {
        StudentControllerService service = new StudentControllerService();
        StudentController hello = service.getStudentControllerPort();
        setCred(hello);

        byte[] student1Avatar = readImage("./soap-connector/src/main/java/main/avatar_student1.bmp");
        byte[] student2Avatar = readImage("./soap-connector/src/main/java/main/avatar_student2.bmp");

        List<String> l1 = new ArrayList<>();
        l1.add("SOA");
        l1.add("COMPILERS");

        List<String> l2 = new ArrayList<>();
        l2.add("SOA");
        l2.add("MATH");

        List<String> l3 = new ArrayList<>();
        l3.add("COMPILERS");
        l3.add("MATH");


        System.out.println("Adding a student1:\n" + hello.addStudent("ignacy", 321, "EAIIB", 6, l1, student1Avatar));
        System.out.println("Adding a student2:\n" + hello.addStudent("agnieszka", 123, "WIET", 5, l2, student2Avatar));
        System.out.println("Adding a student3:\n" + hello.addStudent("kuba", 832, "EAIIB", 7, l3, null));
        System.out.println();


        System.out.println("getting student with studentCardId");

        System.out.println("getStudentById(123):\n" + hello.getStudentById(123));
        System.out.println();
        System.out.println("getStudentById(321):\n" + hello.getStudentById(321));
        System.out.println();


        System.out.println("listing all the students:\n" + hello.list("", "") + "\n");

        System.out.println("listing students from 'EAIIB' faculty:\n" + hello.list("EAIIB", "") + "\n");
        System.out.println("listing students with 'SOA' course:\n" + hello.list("", "SOA") + "\n");
        System.out.println();


        System.out.println("editiing a student");

        System.out.println("changing student3's faculty to 'WIET'");
        System.out.println("getstudentById before the change " + hello.getStudentById(832));
        System.out.println("editStudent response:        " + hello.editStudent(832, null, null, "WIET", null, null, null));
        System.out.println("getstudentById after the change:   " + hello.getStudentById(832));
        System.out.println();


        System.out.println("writing an image out of student's avatar:");

        String student1AvatarCopyPath = "./soap-connector/src/main/java/main/avatar_student1-copy.bmp";
        writeImage(hello.getStudentById(321).getAvatar(), student1AvatarCopyPath);
        System.out.println("written to: " + student1AvatarCopyPath);

        String student2AvatarCopyPath = "./soap-connector/src/main/java/main/avatar_student2-copy.bmp";
        writeImage(hello.getStudentById(123).getAvatar(), student2AvatarCopyPath);
        System.out.println("written to: " + student2AvatarCopyPath);
    }
}
