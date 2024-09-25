import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {
    @DisplayName("1+2는 3이다.")
    @Test // 테스트 메소드, 메소드를 호출할 떄마다 새 인스턴스를 생성, 독립 테스트 가능
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        // Assertions, 예상 결과를 검증하는 assert 메소드 제공
        Assertions.assertEquals(sum,a+b);
        assertThat(a+b).isEqualTo(sum);
    }

    @DisplayName("1+3은 3이다.")
    @Test
    public void junitFailedTest(){
        int a = 1;
        int b = 3;
        int sum = 3;

        // Assertions.assertEquals(sum,a+b);
        assertThat(a+b).isEqualTo(sum);
    }
}



