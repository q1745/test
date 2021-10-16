package com.example.test;

import java.util.List;

import kotlin.text.UStringsKt;

public class MyFood {

    private String msg;
    private int code;
    private List<Dat> data;

    public MyFood() {
    }

    public MyFood(String msg, int code, List<Dat> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyFood{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public List<Dat> getData() {
        return data;
    }

    public void setData(List<Dat> data) {
        this.data = data;
    }

    class Dat {
        private String cover;

        public Dat() {
        }

        public Dat(String cover) {
            this.cover = cover;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        @Override
        public String toString() {
            return "Dat{" +
                    "cover='" + cover + '\'' +
                    '}';
        }
    }
}
