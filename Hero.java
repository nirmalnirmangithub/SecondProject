package Model;

public class Hero
{
    private String id;
    private String name;
    private String mobile_no;
    private String address;
    Hero()
    {

    }

    public Hero(String id, String name, String mobile_no, String address) {
        this.id = id;
        this.name = name;
        this.mobile_no = mobile_no;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getAddress() {
        return address;
    }
}
