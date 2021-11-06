package nurture;

import nurture.models.File;
import nurture.models.Memory;
import nurture.service.FileManagement;
import nurture.strategy.ContiguousMemoryAllocation;
import nurture.strategy.IMemoryAllocationStrategy;
import nurture.strategy.IMemorySegmentStrategy;
import nurture.strategy.MemorySegmentStrategy;

public class DriverClass {
    public static void main(String args[]) {
        IMemorySegmentStrategy iMemorySegmentStrategy = new MemorySegmentStrategy();
        Memory memory = new Memory(256, 3, iMemorySegmentStrategy);

        IMemoryAllocationStrategy iMemoryAllocationStrategy = new ContiguousMemoryAllocation();

        FileManagement fileManagement =  FileManagement.getInstance(iMemoryAllocationStrategy);

        fileManagement.addMemory(memory);
        File file1 = fileManagement.createFile("first.txt");
        File file2 = fileManagement.createFile("second.txt");
        File file3 = fileManagement.createFile("second.txt");

        fileManagement.writeFile(file1.getName(), "Hello this is my text in file");
        System.out.println("file1 : " + file1);
        fileManagement.writeFile(file2.getName(), "this content is for second file");
        System.out.println("file2 : " + file2);

        fileManagement.updateFile(file1.getName(), "Hello updated");
        fileManagement.writeFile(file3.getName(), "third");
        System.out.println("File3 " + file3);
        System.out.println("after updated file1 : " + file1);
        String content = fileManagement.readFile(file2.getName());


//        fileManagement.deleteFile(file.getName());


//        fileManagement.writeFile(file.getName(), "Hello this is my text in file");
        System.out.println("File content :  " + content);
    }
}
