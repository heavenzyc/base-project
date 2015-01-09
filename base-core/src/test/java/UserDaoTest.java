import com.base.PrintUtils;
import com.base.common.OrderType;
import com.base.pagination.Pagination;
import com.base.sys.manager.dao.ManagerDao;
import com.base.sys.manager.domain.Manager;
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
    private ManagerDao userDao;

    @Test
    public void getByIdTest(){
        Manager manager = userDao.get(1);
        System.out.print(manager.getName());
    }

    @Test
    public void getTest(){
        List<Manager> list = userDao.findAll("id", OrderType.DESC);
        System.out.println(list.get(0).getName());
    }

    @Test
    public void findTest(){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","xxx");
    }

    @Test
    public void findAllTest(){
        List<Manager> users = userDao.findAll();
        PrintUtils.print(users);
    }

    @Test
    public void findByQueryStrTest(){
        String hql = " from Manager";
        List<Manager> users =userDao.find(hql);
        PrintUtils.print(users);
    }

    @Test
    public void findPage(){
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(2);
        pagination.setPageSize(2);
        String hql = "from Manager";
        Pagination<Manager> page = userDao.getPage(hql, null, pagination);
        PrintUtils.print(pagination.isLastPage());
        PrintUtils.print(pagination.hasNextPage());
        PrintUtils.print(page.getRecord());
    }

    @Test
    public void findPage1(){
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(2);
        String hql = "from Manager where name=:name";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","zyc");
        Pagination<Manager> page = userDao.getPage(hql, params, pagination);
        PrintUtils.print(pagination.isLastPage());
        PrintUtils.print(pagination.hasNextPage());
        PrintUtils.print(page.getRecord());
    }

    @Test
    public void countTest(){
        String hql = "from Manager where name=:name";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","zyc");
        long count = userDao.count(hql, params);
        PrintUtils.print(count);
    }

    @Test
    public void sumTest(){
        String hql = "from Manager where age=:age";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("age",28);
        long count = userDao.sum(hql,"age", params);
        PrintUtils.print(count);
    }

    @Test
    public void getAllTest(){
    }

}
