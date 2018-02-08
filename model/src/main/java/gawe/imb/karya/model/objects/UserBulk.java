package gawe.imb.karya.model.objects;

/**
 * Created by korneliussendy on 1/28/18.
 */

public  class UserBulk {
    User user;
    Employee employee;
    Employer employer;


    public UserBulk(User user, Employee employee, Employer employer) {
        this.user = user;
        this.employee = employee;
        this.employer = employer;
    }

    public UserBulk() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}