package demo.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class StudentConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Student student = (Student) o;
        hierarchicalStreamWriter.startNode("姓名");
        hierarchicalStreamWriter.setValue(student.getName());
        hierarchicalStreamWriter.endNode();
        hierarchicalStreamWriter.startNode("年龄");
        hierarchicalStreamWriter.setValue(student.getAge()+"");
        hierarchicalStreamWriter.endNode();

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        Student student = new Student();
        hierarchicalStreamReader.moveDown();
        student.setName(hierarchicalStreamReader.getValue());
        hierarchicalStreamReader.moveUp();
        hierarchicalStreamReader.moveDown();
        student.setAge(Integer.parseInt(hierarchicalStreamReader.getValue()));
        hierarchicalStreamReader.moveUp();
        return student;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Student.class);
    }
}
