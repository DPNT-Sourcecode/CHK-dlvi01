package befaster.solutions.HLO;

import befaster.solutions.SUM.SumSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HelloSolutionTest {

    private HelloSolution hello;

    @BeforeEach
    public void setUp() {
        hello = new HelloSolution();
    }

    @Test
    public void print_hello() {
        assertThat(hello.hello("Frank"), equalTo("Hello, Frank!"));
    }
}
