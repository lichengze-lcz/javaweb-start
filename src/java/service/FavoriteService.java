package service;

public interface FavoriteService {

  /**
   * 判断用户是否收藏
   */
  public boolean isFavorite(String rid,int uid);


  public void add(String rid, int uid);
}
