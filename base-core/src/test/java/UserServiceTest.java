import com.base.user.domain.User;
import com.base.user.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public class UserServiceTest extends SpringBaseTest{
    @Resource
    private UserService userService;

    @Test
    public void userTest(){
        User user = userService.get(1);
        List<User> list = userService.findAll();
        System.out.println(user.getName());
    }

    public static void main(String[] args){
        System.out.print(User.class.getName());
    }
}
