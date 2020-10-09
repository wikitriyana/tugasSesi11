package com.wiki.databasesqlsesi11.Model;

public class Data {
    private int _id;
    private String _name;
    private String _phone_number;



public Data(int id, String name) {
        this.set_id(id);
        this.set_name(name);
        this.get_phone_number();
    }


    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return this._phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}


