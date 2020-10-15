package demo.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.springframework.web.servlet.view.xslt.XsltView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("李四",20);
        XStream xStream = new XStream();
        //xml序列反序列
        String s = xStream.toXML(student);
        System.out.println(s);
        Student o = (Student) xStream.fromXML(s);
        System.out.println(o.toString());
        System.out.println("--------------------------");

        //json序列反序列
        XStream xStream1 = new XStream(new JettisonMappedXmlDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        String s1 = xStream1.toXML(student);
        System.out.println(s1);
        Student o1 = (Student) xStream1.fromXML(s1);
        System.out.println(o1);
        System.out.println("--------------------------");

        //类混叠
        Phone phone = new Phone("苹果","白色");
        Phone phone1 = new Phone("华为","黑色");
        List<Phone> list = new ArrayList<>();
        list.add(phone);
        list.add(phone1);
        Student student1 = new Student("王五",20,list);
        XStream xStream2 = new XStream();
        //标签全限定名别名
        xStream2.alias("Student",Student.class);
        xStream2.alias("Phone",Phone.class);
        //子标签别名
        xStream2.aliasField("Student-name",Student.class,"name");
        //节点隐藏
        xStream2.addImplicitCollection(Student.class,"phones");
        //属性混叠
        xStream2.useAttributeFor(Student.class,"name");
        String s2 = xStream2.toXML(student1);
        System.out.println(s2);
        System.out.println("--------------------------");


        //自定义转换器
       XStream xStream3 = new XStream();
       //注册转换器
        xStream3.registerConverter(new StudentConverter());
        String s3 = xStream3.toXML(student1);
        System.out.println(s3);

        Student o2 = (Student) xStream3.fromXML(s3);
        System.out.println(o2.toString());
        System.out.println("--------------------------");

        //XStream对象流输出
//        writeObject(new XStream(),"d:\\test.txt");
        //输入
        readObject(new XStream(),"d:\\test.txt");
    }

    public static void writeObject(XStream xStream , String path) throws IOException {
        Student student = new Student("张三",20);
        Student student1 = new Student("李四",21);
        Student student2 = new Student("王五",22);
        ObjectOutputStream objectOutputStream = xStream.createObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(student);
        objectOutputStream.writeObject(student1);
        objectOutputStream.writeObject(student2);
        objectOutputStream.writeObject("sssss");
        objectOutputStream.writeInt(3);
        objectOutputStream.close();
    }

    public static void readObject(XStream xStream ,String path) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = xStream.createObjectInputStream(new FileInputStream(path));
        System.out.println((Student)objectInputStream.readObject());
        System.out.println((Student)objectInputStream.readObject());
        System.out.println((Student)objectInputStream.readObject());
        System.out.println((String) objectInputStream.readObject());
        System.out.println(objectInputStream.readInt());
    }

}
