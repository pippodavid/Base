package ExecutorsTest;

import com.david.ExecutorService.ForkJoinSumCalculate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ForkJoinPoolTest {

    @Test
    public void testForkJoinPool() {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 100000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }
}
