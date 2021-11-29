package nurture;

import nurture.models.File;
import nurture.models.Memory;
import nurture.service.FileManagement;
import nurture.strategy.ContiguousMemoryAllocation;
import nurture.strategy.IMemoryAllocationStrategy;
import nurture.strategy.IMemorySegmentStrategy;
import nurture.strategy.MemorySegmentStrategy;

public class DriverClass {
    public static void main(String args[]) throws CloneNotSupportedException {

        IMemorySegmentStrategy iMemorySegmentStrategy = new MemorySegmentStrategy();

        Memory memory1 = new Memory(512, 2, iMemorySegmentStrategy);
        Memory memory2 = new Memory(256, 2, iMemorySegmentStrategy);

        IMemoryAllocationStrategy iMemoryAllocationStrategy = new ContiguousMemoryAllocation();

        FileManagement fileManagement = FileManagement.getInstance(iMemoryAllocationStrategy);

        fileManagement.addMemory(memory1, "slot1");
        fileManagement.addMemory(memory2, "slot2");

        File file1 = fileManagement.createFile("first.txt");
        File file2 = fileManagement.createFile("second.txt");
        File file3 = fileManagement.createFile("third.txt");

        fileManagement.writeFile(file1.getName(), "this is first file content");
        fileManagement.writeFile(file2.getName(), "second file");
        fileManagement.updateFile(file1.getName(), "Hello updated this is content is not avaivdnvkldvhdjkvhdsjkhdjkhdsjfkhjfkhfkjdfhdkjfhkjdgsjkdgsdkjgsdkjsgkdjgsdksdgksjg");

//        fileManagement.writeFile(file3.getName(), "third file content");

//        System.out.println("File1" + file1);
//        System.out.println("File2" + file2);
//        System.out.println("File3" + file3);
//        String content = fileManagement.readFile(file1.getName());

//        System.out.println("File2 after : "+ file2);
//        System.out.println("File3 after : "+ file3);

//        fileManagement.deleteFile(file1.getName());
//        fileManagement.deleteFile(file2.getName());
        fileManagement.deleteFile(file3.getName());


//        fileManagement.writeFile(file.getName(), "Hello this is my text in file");
//        System.out.println("File1 content :  " + content);
//        System.out.println("File2 Content : " + fileManagement.readFile(file2.getName()));
//        System.out.println("File3 Content : " + fileManagement.readFile(file3.getName()));
//        System.out.println("File2 " + file2);
//        System.out.println("File3 " + file3);

    }
}
