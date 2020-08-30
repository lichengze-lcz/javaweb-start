package domain;

public class Resultinfo {
  private boolean flag;
  private Object data;
  private String errormag;

    public Resultinfo() {
    }

    public Resultinfo(boolean flag, Object data, String errormag) {
        this.flag = flag;
        this.data = data;
        this.errormag = errormag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrormag() {
        return errormag;
    }

    public void setErrormag(String errormag) {
        this.errormag = errormag;
    }

    @Override
    public String toString() {
        return "Resultinfo{" +
                "flag=" + flag +
                ", data=" + data +
                ", errormag='" + errormag + '\'' +
                '}';
    }
}
