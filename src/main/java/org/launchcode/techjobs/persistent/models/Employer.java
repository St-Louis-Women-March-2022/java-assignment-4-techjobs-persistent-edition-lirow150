package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity //annotation defines that a class can be mapped to a table
public class Employer extends AbstractEntity {

    //Add the field for location with validation that ensures it is not empty and has a reasonable length
    @NotBlank (message = "Name is required")
    @Size (max = 100, message = "Location must be less tha 100 characters")
    public String location;


 //3-1 Within Employer, add a private property jobs of type List<Job> and initialize it to an empty ArrayList.
 //Use the @OneToMany and @JoinColumn annotations on the jobs list in Employer to declare the relationship between data tables.
    @OneToMany
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();



    //add public accessor methods to Employer.
    public Employer()  {

    }

    public Employer(String location) {
        super();;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
