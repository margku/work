package ru.sfedu.postHibernate;

import java.util.List;
public class Result<T> {

        private List<T> bean;
        private Msg status;
        private String answer;
        private Object object;
        private Long id;

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


    public Result(Msg status, Long id) {
        this.status = status;
        this.id = id;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


