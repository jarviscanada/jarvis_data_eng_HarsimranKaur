package ca.jrvs.apps.practice;

import java.util.regex.Pattern;

public class RegexExcImp implements RegexImp{
    @Override
    public boolean matchJpeg(String filename){
        String regex="^(.+(\\.jpg))|(.+(\\.jpeg))$";
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(filename);
        return matcher.find();
    }
    @Override
    public boolean matchIp(String ip){
        String regex="^([0-9]{1,3}\\.){3}([0-9]{1,3})$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(ip);
        return matcher.find();
    }
    @Override
    public boolean isEmptyLine(String line){
        String regex="^$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(line);
        return matcher.find();
    }
}
