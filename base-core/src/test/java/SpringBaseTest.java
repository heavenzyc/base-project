import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@ContextConfiguration(locations={"classpath:/spring/*.xml"})
@TransactionConfiguration(defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class SpringBaseTest extends
        AbstractTransactionalJUnit4SpringContextTests {
}
