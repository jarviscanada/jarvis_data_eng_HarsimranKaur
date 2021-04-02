package ca.jrvs.apps.grep;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImpLambda implements JavaGrep {

    final static Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRootPath(){ return rootPath; }
    @Override
    public void setRootPath(String rootPath){
        this.rootPath=rootPath;
    }
    @Override
    public String getRegex(){
        return regex;
    }
    @Override
    public void setRegex(String regex){
        this.regex=regex;
    }
    @Override
    public String getOutFile(){
        return outFile;
    }
    @Override
    public void setOutFile(String outFile){
        this.outFile=outFile;
    }
    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();
        String rootDir=getRootPath();
        listFiles(rootDir).stream().forEach(
                file->{
                try {
                    writeToFile(readLines(file).stream()
                            .filter(line -> containsPattern(line))
                                    .collect(Collectors.toList()));
                }
                catch(IOException e){
                    logger.error("Exception in process()",e);
                }
                }
        );
    }
    @Override
    public List<File> listFiles(String rootDir) {
        try(Stream<Path> walk= Files.walk(Paths.get(rootDir))){
            return walk.filter(Files::isRegularFile).map(Path::toString)
                    .map(File::new)
                    .collect(Collectors.toList());
        }catch(IOException e){
            logger.error("Exception found in listFiles()",e);
        }
        return null;
    }
    @Override
    public List<String> readLines(File inputFile) {
        try(Stream<String> stream = Files.lines(inputFile.toPath())){
                return stream.collect(Collectors.toList());
        } catch(IOException e){
            logger.error("Exception found in readLines()",e);
        }
        return null;
    }
    @Override
    public boolean containsPattern(String line) {
        String regex = getRegex();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
    @Override
    public void writeToFile(List<String> lines) throws IOException {
            String outputFile=getOutFile();
            FileWriter out = new FileWriter(outputFile);
            lines.stream().forEach(l -> {
                try {
                    out.write(l + '\n');
                } catch (IOException e) {
                    logger.error("Exception found in writeToFile()", e);
                }
            });
            out.close();
    }

    public static void main(String args[]) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootpath outFile");
        }
        BasicConfigurator.configure();
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        }catch (Exception e){
            logger.error("Exception found in process()",e);
        }
    }

}

