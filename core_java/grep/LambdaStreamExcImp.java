package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp {

    Stream<String> createStrStream(String ... strings){
        return Stream.of(strings);
    }

    Stream<String> toUpperCase(String ... strings){
        return createStrStream().map(String::toUpperCase);
    }

    Stream<String> filter(Stream<String> stringStream, String pattern){
        Pattern p = Pattern.compile(pattern);
        return stringStream.filter(str -> !p.matcher(str).find());
    }

    IntStream createIntStream(int[] arr){

        return IntStream.of(arr);
    }

    <E> List<E> toList(Stream<E> stream){

        return stream.collect(Collectors.toList());
    }

    List<Integer> toList(IntStream intStream){
    return intStream.boxed().collect(Collectors.toList());
    }

    IntStream createIntStream(int start, int end){
        return IntStream.rangeClosed(start,end);
    }

    DoubleStream squareRootIntStream(IntStream intStream){
        return intStream.asDoubleStream().map(Math::sqrt);
    }

    IntStream getOdd(IntStream intStream){
        return intStream.filter(x -> x%2==1);
    }

    Consumer<String> getLambdaPrinter(String prefix, String suffix){
        Consumer<String> printer= (String body) -> System.out.println(prefix+body+suffix);
        return printer;
    }

    void printMessages(String[] messages, Consumer<String> printer){
        Stream.of(messages).forEach(m -> printer.accept(m));
    }

    void printOdd(IntStream intStream, Consumer<String> printer){
        getOdd(intStream).mapToObj(Integer::toString).forEach(s -> printer.accept(s));
    }

    Stream<Integer> flatNestedInt(Stream<List<Integer>> ints){
        return ints.flatMap(x -> x.stream().map(y -> y * y));
    }


}