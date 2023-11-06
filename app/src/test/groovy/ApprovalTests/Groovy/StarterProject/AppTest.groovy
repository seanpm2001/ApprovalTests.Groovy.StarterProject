package ApprovalTests.Groovy.StarterProject

import org.approvaltests.Approvals
import org.approvaltests.JsonApprovals
import org.approvaltests.combinations.CombinationApprovals
import org.approvaltests.core.Options
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import spock.lang.Specification

class AppTest extends Specification {

    @Test
    void testNormalJUnit() {
        Assertions.assertEquals(5, 5)
    }

    @Test
    void testWithApprovalTests() {
        Approvals.verify("Hello World")
    }

    @Test
    void testCombinations() {
        Integer[] numbers = [10, 20, 30, 40, 50]
        CombinationApprovals.verifyAllCombinations({ a, b -> "${a} + ${b}" }, numbers, numbers)
    }
    /**
     * note: this requires GSON which is currently added in the build.gradle file.
     * This is only required if you want to use the VerifyAsJson.
     */
    @Test
    void testJson() {
        def hero = new Person("jayne", "cobb", true, 38)
        JsonApprovals.verifyAsJson(hero)
    }

    def Approvalsdoesnotsupportspock() {
        setup:

        def app = new App()

        when:
        def result = app.greeting

        then:
        result == "Hello Approvals!"
//        Approvals.verify("Hello World", new Options().forFile().withNamer(SpockNamer.createNamer(this)))
        Approvals.verify("Hello World", SpockNamer.use(this))
    }
}
