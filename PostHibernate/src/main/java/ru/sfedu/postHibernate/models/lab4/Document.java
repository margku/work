package ru.sfedu.postHibernate.models.lab4;

import org.hibernate.annotations.Parent;

import javax.persistence.*;


@Embeddable
public class Document {

    @Parent
    protected ClientInfo clientInfo;

    private long docNum;

    public Document(long doc) {
        this.docNum = doc;
    }

    public Document() {
    }

    public long getDocNum() {
        return docNum;
    }

    public void setDocNum(long docNum) {
        this.docNum = docNum;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNum=" + docNum +
                '}';
    }
}
