package dao.impl;

import dao.RouteDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import page.Route;
import service.RouteService;

import java.util.ArrayList;
import java.util.List;


/**
 * 查询总记录数   参数可能是 cid  rname
 */
public class RoutdaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(jdbcUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {

        //组合查询  （两者值搜索时同时存在，分类时存在一个cid）
        //1.定义sql模板
        String sql = "select count(*) from route where 1=1";
          //用于存拼接参数
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList(); //用于存条件s   （可能有可能，没有）

        //2.判断参数是否有值
          if(cid != 0){
              sb.append("  and cid = ? ");
              params.add(cid);  //添加？对应的值 cid
          }

          if (rname != null && rname.length() > 0 && !"null".equals(rname)){   //不传rname 值为"null"
              sb.append(" and rname like ?");
              params.add("%"+rname+"%");
          }

          //3，执行拼接好的sql
          sql = sb.toString();
        System.out.println("findTotalCount 查总记录数sql-----------------------"+sql);
//     String sql = "select count(*) from route where cid=?";
       return template.queryForObject(sql,Integer.class,params.toArray());   //params.toArray()遍历会是地址


    }

    /**
     *  分页查询  参数可能是 cid  rname
     */

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
//        String sql = "select * from route where cid =? limit ?,?";
        //1.定义sql模板
        String sql = "select * from route where 1 = 1";

        //用于存拼接参数
        StringBuilder sb = new StringBuilder(sql); //*传sql拼接
        List params = new ArrayList(); //用于存条件s   （可能有可能，没有）

        //2.判断参数是否有值
        if(cid != 0){
            sb.append("  and cid = ? ");
            params.add(cid);  //添加？对应的值 cid
        }

        if (rname != null && rname.length() > 0 && !"null".equals(rname)){  //******!"null".equals(rname******切记
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }

        sb.append(" limit ? , ? ");  //分页条件
        //3，执行拼接好的sql
        sql = sb.toString();
        System.out.println("findByPage分页查询sql-----------------------"+sql);

        params.add(start);
        params.add(pageSize);


        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }


    /**接口实现类
     *    根据id查询 route表中 所有信息
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {

        String sql = "select * from route where rid =?";
        //一条内容
        System.out.println("传递的rid"+rid);
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }


}
