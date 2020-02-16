package gusakov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class CompareOperatorsBenchMark {
    @State(Scope.Thread)
    public static class MyIntState {
        public int a = 1;
        public int b = 2;
    }

    @State(Scope.Thread)
    public static class MyDoubleState {
        public double a = 1;
        public double b = 2;
    }

    @State(Scope.Thread)
    public static class MyStringState {
        public String a = "first";
        public String b = "second";
        public Object ob = new Integer(1);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void add(MyIntState myIntState, Blackhole blackhole) {
        int result = myIntState.a + myIntState.b;
        blackhole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void instanceOf(MyStringState myStringState, Blackhole blackhole) {
        boolean res = myStringState.ob instanceof String;
        blackhole.consume(res);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void notEqualsWithDouble(MyDoubleState myDoubleState, Blackhole blackhole) {
        boolean res = myDoubleState.a != myDoubleState.b;
        blackhole.consume(res);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void notEqualsWithInt(MyIntState myIntState, Blackhole blackhole) {
        boolean res = myIntState.a != myIntState.b;
        blackhole.consume(res);
    }
}
