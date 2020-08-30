package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoimpl;
import dao.impl.jedisutil;
import page.category;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//优化查询数据
public class CategoryServlceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoimpl();

    @Override
    public List<category> finall() {
//  Redis对查询数据 缓存优化

        //1.从Redis里面查询
        //1.1获取jedis客户端
        Jedis jedis = jedisutil.getJedis();

        //Set<String> category = jedis.zrange("category", 0, -1);  查值
        //2.查询sortedset有序集合  中的分数（cid）和值（name）
        Set<Tuple> category = jedis.zrangeByScoreWithScores("category", 0, -1);

        List<category> cs =null;

        //2.判断查询的集合是否为空
        if(category == null || category.size() == 0){
            //3.Redis如果为空，需要从数据库查询，再将数据库存入Redis
            cs = categoryDao.finall();

            //3.1将集合数据存储到Redsi中的 category 的key
            for (int i = 0; i < cs.size(); i++) {      //每遍历一次  存一次      zadd有序存
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            System.out.println("在redis中查询分类条........");
            //如果redis不为空  将set数据 存入list
            cs = new ArrayList<category>();
            for (Tuple tuple : category) {
                category category1 = new category();
                category1.setCname(tuple.getElement());
                category1.setCid((int)tuple.getScore());   //传递cid
                cs.add(category1);

            }
        }

        //4.Redis如果不为空，直接返回数据到客户端
        System.out.println("分类条数据返回到客户端."+cs);
        return cs;
    }
}
