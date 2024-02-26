package org.example.demo.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

public class Cmd {

    /**
     * /@Parameter��jcommander���һ��ע�⣬���ڶ���һ�������в���
     * �����Խ��ͣ��������� ����������Ϣ ������˳�� �Ƿ���ʾ������Ϣ
     */
    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    /**
     * java -cp ����JAR�ľ���·�� ���ȫ�޶���
     */
    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    /**
     * �洢�����в���
     */
    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    /**
     * ��ȡ����
     * @return
     */
    String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty()
                ? mainClassAndArgs.get(0):null;
    }

    /**
     * ��ȡ�������������в���
     * subList(fromIndex(����),toIndex(������))����ָ����Χ���Ӽ�
     * @return
     */
    List<String> getAppArgs() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1
                ? mainClassAndArgs.subList(1, mainClassAndArgs.size()):null;
    }

    static Cmd parse(String[] argv){
        Cmd args=new Cmd();
        JCommander cmd=JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);//ʹ��JCommander��parse�������������в���
        args.ok=true;
        return args;
    }
}
