public class Person {
    private String name;
    private String matricNo;
    private String category;

    public Person(String n, String m, String c) {
        this.name = n;
        this.matricNo = m;
        this.category = c;
    }

    // Setter method
    public void setName(String name) {
        this.name = name;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter method
    public String getName() {
        return name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public String getCategory() {
        return category;
    }
}
