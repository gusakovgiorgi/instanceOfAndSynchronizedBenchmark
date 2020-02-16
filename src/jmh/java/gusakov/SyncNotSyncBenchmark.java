package gusakov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class SyncNotSyncBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        public int loopSize = 100;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void nonSyncTest(MyState myState, Blackhole blackhole) {
        for (int i = 0; i < myState.loopSize; i++) {
            blackhole.consume(result(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void syncTes(MyState myState, Blackhole blackhole) {
        for (int i = 0; i < myState.loopSize; i++) {
            blackhole.consume(syncResult(i));
        }
    }

    public static synchronized int syncResult(int i) {
        int a = i;
        int b = a * 2;
        int sum = (int) (a * b * 10 / 10.0);
        return sum;
    }

    public static int result(int i) {
        int a = i;
        int b = a * 2;
        int sum = (int) (a * b * 10 / 10.0);
        return sum;
    }
}
