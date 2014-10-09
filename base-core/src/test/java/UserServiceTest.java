import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public class UserServiceTest extends SpringBaseTest{
    @Resource
    private ManagerService userService;

    @Test
    public void userTest(){
        Manager user = userService.get(1);
        List<Manager> list = userService.findAll();
        System.out.println(user.getName());
    }

    public static void main(String[] args){
        System.out.print(Manager.class.getName());
    }
}
