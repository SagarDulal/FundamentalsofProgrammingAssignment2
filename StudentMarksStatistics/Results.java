 

public class Results {
    private String lastName;
    private String firstName;
    private String studentId;
    private double asn1;
    private double asn2;
    private double asn3;
    private double totalMarks;

    public Results() {
    }
    
    public Results(String lastName, String firstName, String studentId, double asn1, double asn2, double asn3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.asn1 = asn1;
        this.asn2 = asn2;
        this.asn3 = asn3;
        this.totalMarks = asn1 + asn2 + asn3;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAsn1() {
        return asn1;
    }

    public void setAsn1(double asn1) {
        this.asn1 = asn1;
    }

    public double getAsn2() {
        return asn2;
    }

    public void setAsn2(double asn2) {
        this.asn2 = asn2;
    }

    public double getAsn3() {
        return asn3;
    }

    public void setAsn3(double asn3) {
        this.asn3 = asn3;
    }
}

