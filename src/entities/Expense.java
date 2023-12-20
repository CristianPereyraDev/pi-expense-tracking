package entities;

public class Expense {
    public static Integer counter = 1;
    private Integer id;
    private String date;
    private Double amount;
    private String description;
    private Integer categoryId;
    public Expense() {
        this.id = counter;
        this.amount = 0.0;
        counter++;
    }
    public Expense(Integer id, String date, Double amount, String description, Integer categoryId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", category=" + categoryId +
                '}';
    }
}
