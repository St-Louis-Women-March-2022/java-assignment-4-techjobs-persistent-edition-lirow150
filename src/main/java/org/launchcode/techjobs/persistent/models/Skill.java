package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

//In the model class Skill, add a field for a longer description of the skill, named description,
// with public accessor methods. Some hiring managers like to have more information available
// about the nature of a given programming language or framework.
@Entity
public class Skill extends AbstractEntity {

    @Size(max = 500, message = "Description must be less than 500 characters")
    public String description;

    @ManyToMany(mappedBy = "skills")
    @NotNull
    private List<Job> jobs = new ArrayList<>();

    public Skill() {}


    @Size(max = 100, message = "Description must be less than 100 characters")
    public Skill(String description) {
        super();
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}