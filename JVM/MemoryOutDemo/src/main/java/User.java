public class User {
    private String userId;
    private String name;
    private int age;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String userId) {
        this.userId = userId;
        this.name = "";
        this.age = 0;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (o instanceof User) {
            isEqual = ((User) o).userId.equals(this.userId);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
