import com.base.PrintUtils;
import com.base.common.OrderType;
import com.base.user.dao.UserDao;
import com.base.user.domain.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 14-8-19.
 */
public class UserDaoTest extends SpringBaseTest {

    @Resource
    private UserDao userDao;

    @Test
    public void getTest(){
        List<User> list = userDao.findAll("id", OrderType.DESC);
        System.out.println(list.get(0).getName());
    }

    @Test
    public void findTest(){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","xxx");
    //    params.put("age",28);
        List<User> users = userDao.find("",params);
        for(User user : users){
            PrintUtils.print(user.getNickname());
        }
    }

}
