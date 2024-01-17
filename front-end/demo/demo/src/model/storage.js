const storage = {

  /*新添加缓存*/
  set(key, val) {
    localStorage.setItem(key, JSON.stringify(val));
  },

  /*获取缓存*/
  get(key) {
    return JSON.parse(localStorage.getItem(key));
  },

  /*检验某个值是否存在*/
  contains(key) {
    let item = JSON.stringify(localStorage.getItem(key));
    return item !== "null";
  },

  /*删除缓存*/
  remove(key) {
    localStorage.removeItem(key)
  }
};

export default storage;
