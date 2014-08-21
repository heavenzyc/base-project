import com.base.PrintUtils;
import com.base.common.OrderType;
import com.base.pagination.Pagination;
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
    }

    @Test
    public void findAllTest(){
        List<User> users = userDao.findAll();
        PrintUtils.print(users);
    }

    @Test
    public void findByQueryStrTest(){
        String hql = " from User";
        List<User> users =userDao.find(hql);
        PrintUtils.print(users);
    }

    @Test
    public void findPage(){
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(0);
        pagination.setPageSize(2);
        String hql = "from User";
        List<User> users = userDao.find(hql, pagination);
        PrintUtils.print(users);
    }

}
