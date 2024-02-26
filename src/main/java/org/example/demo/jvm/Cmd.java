package org.example.demo.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

public class Cmd {

    /**
     * /@Parameter是jcommander库的一个注解，用于定义一个命令行参数
     * 各属性解释：参数名称 参数描述信息 参数的顺序 是否显示帮助信息
     */
    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    /**
     * java -cp 依赖JAR的绝对路径 类的全限定名
     */
    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    /**
     * 存储命令行参数
     */
    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    /**
     * 获取类名
     * @return
     */
    String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty()
                ? mainClassAndArgs.get(0):null;
    }

    /**
     * 获取除类名的命令行参数
     * subList(fromIndex(包含),toIndex(不包含))返回指定范围的子集
     * @return
     */
    List<String> getAppArgs() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1
                ? mainClassAndArgs.subList(1, mainClassAndArgs.size()):null;
    }

    static Cmd parse(String[] argv){
        Cmd args=new Cmd();
        JCommander cmd=JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);//使用JCommander的parse方法解析命令行参数
        args.ok=true;
        return args;
    }
}
