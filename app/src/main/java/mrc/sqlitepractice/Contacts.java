package mrc.sqlitepractice;

/**
 * Created by HP on 05-01-2018.
 */

public class Contacts {

    int _id;

    String _name;

    public Contacts() {

    }

    public Contacts(String name) {

        this._name = name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }



    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_id() {

        return _id;
    }



    public String get_name() {
        return _name;
    }
}
