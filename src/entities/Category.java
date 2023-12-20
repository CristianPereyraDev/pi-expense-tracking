package entities;

public class Category {
    public static Integer counter = 1;
    private Integer id;
    private String name;

    public Category() {
        this.id = counter;
        counter++;
    }

    public Category(String name) {
        this.name = name;
        this.id = counter;
        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
