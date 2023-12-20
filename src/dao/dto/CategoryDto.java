package dao.dto;

public class CategoryDto {
    public static Integer counter = 1;
    private Integer id;
    private String name;

    public CategoryDto() {
        this.id = counter;
        counter++;
    }

    public CategoryDto(String name) {
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
