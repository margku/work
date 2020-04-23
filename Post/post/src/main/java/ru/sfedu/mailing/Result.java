package ru.sfedu.mailing;

import java.util.List;
public class Result<T> {

        private List<T> bean;
        private Msg status;
        private String answer;
        private Object object;

        public Result(Result bean) {

        }

        public Result(Msg status, String answer) {
            this.status = status;
            this.answer = answer;
        }

        public Result(Msg status) {this.status = status;}

        public Result(List<T> bean){
            this.bean = bean;
        }

        public Result(List<T> bean, Msg status) {
            this.bean = bean;
            this.status = status;
        }

       public Result(Object o) {this.object = o;}


    public List<T> getBean() {
            return bean;
        }

        public void setBean(List<T> bean) {
            this.bean = bean;
        }

        public Msg getStatus() {
            return status;
        }

        public void setStatus(Msg status) {
            this.status = status;
        }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}


