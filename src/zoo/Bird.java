package zoo;

public class Bird extends Animal {

    private boolean canFly;

    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public String makeSound() {
        return "The bird chirps.";
    }

    @Override
    public String getInfo() {
        return "Name: " + getName()
                + ", Age: " + getAge()
                + ", Can Fly: " + (canFly ? "Yes" : "No");
    }
}