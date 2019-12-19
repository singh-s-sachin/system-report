import java.io.*;
import java.lang.management.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import com.mysql.jdbc.*;
class muni
{
    public static void main(String... a)
    {
        int i=0,m=0;
        long um=0,ud=0;
        float cu=0.0f;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
        try{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sysReport","root","#Copperwire7"); 
        Statement stmt=con.createStatement();
        while(true){
            i+=1;
            m+=1;
            long freespace = new File("/").getFreeSpace();
            long diskSize=new File("/").getTotalSpace();
            diskSize-=freespace;
            ud+=diskSize;
            long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            long memorSize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
            memorySize-=memorSize;
            um+=memorSize;
            double CPU=((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad();
            cu+=CPU;
            System.out.println("Disk Used:="+diskSize/(1024*1024)+" Bytes");
            System.out.println("RAM Used="+memorySize+" Bytes");
            System.out.println("CPU used="+CPU);
            stmt.executeUpdate("insert into report(usedMemory,usedDisk,cpuUtilization)values("+memorySize+","+diskSize+","+CPU+")");
            try{
            TimeUnit.SECONDS.sleep(4);
            }
            catch(InterruptedException E){
                System.out.println("bhosriwala");
            }
            if(i==5)
            {
                i=0;
                stmt.executeUpdate("insert into meanReport(usedMemory,usedDisk,cpuUtilization)values("+um/m+","+ud/m+","+cu/m+")");
            }
            }
    }
        catch(Exception e){
            System.out.println(e);
        }  
    }
    catch(Exception e){
        System.out.println("Cutiya");
    }
   }
}