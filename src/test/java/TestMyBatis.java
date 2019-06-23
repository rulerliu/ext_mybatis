import com.liuwq.entity.UserEntity;
import com.liuwq.mappers.UserMapper;
import com.liuwq.session.SqlSession;
import com.liuwq.session.SqlSessionFactory;
import com.liuwq.session.SqlSessionFactoryBuilder;

public class TestMyBatis {
    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1.获取默认sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build("my_config.properties");

        // 2.开启DefaultSqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.jdk动态代理 生成UserMapper代理类 $Proxy0
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4.执行MapperProxy的invoke方法
        System.out.println(">>>第一次查询");
        UserEntity userEntity = userMapper.getUser(1L);
        System.out.println(userEntity.toString());

        System.out.println(">>>第二次查询");
        UserEntity userEntity2 = userMapper.getUser(1L);
        System.out.println(userEntity2.toString());

    }
}
