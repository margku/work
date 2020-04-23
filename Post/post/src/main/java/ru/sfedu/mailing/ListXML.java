package ru.sfedu.mailing;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root (name= "ListXML")
public class ListXML<T> {
    @ElementList(inline = true, required = false)
    public List<T> list;


    public ListXML (List<T> list) {
        this.list = list;
    }
    public ListXML () {}
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }



}
